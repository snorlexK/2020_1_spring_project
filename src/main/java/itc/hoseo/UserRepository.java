package itc.hoseo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRepository {
	public UserRepository() {
		String sql = "create table if not exists user("
				+ "id varchar(30) primary key, "
				+ "password varchar(30) not null, "
				+ "nickname varchar(30) not null unique, "
				+ "registeredDate date not null"
				+ ")";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("테이블 생성 오류", e);
		}
		sql = "insert into user values(?, ?, ?, ?)";
		try(Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "sample");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "샘플");
			pstmt.setDate(4, new java.sql.Date(new Date().getTime()));
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("샘플 데이터 삽입 오류", e);
		}
	}
	
	public Connection dbConnect() throws Exception {
		return DriverManager.getConnection("jdbc:hsqldb:mem:myDb", "sa", "sa");
	}
	
	/**
	 * 회원 추가
	 * 
	 * @param user 저장할 회원 정보
	 * @return 저장 성공하면 true, 실패하면 false
	 */
	public boolean add(User user) {
		int cnt = 0;
		String sql = "insert into user values(?, ?, ?, ?)";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			java.sql.Date date = new java.sql.Date(user.getRegisteredDate().getTime()); 
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());
			pstmt.setDate(4, date);
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			log.error("저장 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
	
	/** 
	 * 회원 정보 모두 불러오기
	 * 
	 * @return 회원 전체 목록
	 */
	public List<User> list() {
		List<User> users = new ArrayList<User>();
		String sql = "select * from user";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery();) {
			while(rs.next()) {
				User user = User.builder()
						.id(rs.getString("id"))
						.password(rs.getString("password"))
						.nickname(rs.getString("nickname"))
						.registeredDate(rs.getDate("registeredDate"))
						.build();
				users.add(user);
			}
		} catch(Exception e) {
			log.error("회원 정보 로드 오류", e);
		}
		return users;
	}
	
	/**
	 * 회원 삭제
	 * 
	 * @param id 삭제할 회원의 id
	 * @return 삭제 성공하면 true, 실패하면 false
	 */
	public boolean delete(String id) {
		int cnt = 0;
		String sql = "delete from user where id=?";
		try (Connection con = dbConnect(); PreparedStatement pstmt =  con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			cnt =pstmt.executeUpdate();
		} catch(Exception e) {
			log.error("삭제 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
}
