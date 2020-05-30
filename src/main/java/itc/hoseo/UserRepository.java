package itc.hoseo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {
	
	@Autowired
	private JdbcTemplate template;
	
	/**
	 * 회원 추가
	 * 
	 * @param user 저장할 회원 정보
	 * @return 저장 성공하면 true, 실패하면 false
	 */
	public boolean save(User user) {
		int cnt = 0;
		
		try {
			java.sql.Date date = new java.sql.Date(user.getRegisteredDate().getTime());
			cnt = template.update("insert into user values(?, ?, ?, ?)",
				user.getId(), user.getPassword(), user.getNickname(), date);
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
		try {
			users = template.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
		} catch(Exception e) {
			log.error("회원 정보 로드 오류", e);
		}
		return users;
	}
	
	/**
	 * 회원 수정
	 * 
	 * @param 수정된 회원 정보
	 * @return 수정 성공하면 true, 실패하면 false
	 */
	public boolean update(User user) {
		int cnt = 0;		
		try {
			cnt = template.update("update user set password = ?, nickname = ? where id = ?", 
				user.getPassword(), user.getNickname(), user.getId());
		} catch(Exception e) {
			log.error("수정 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
	
	/**
	 * 회원 삭제
	 * 
	 * @param id 삭제할 회원의 id
	 * @return 삭제 성공하면 true, 실패하면 false
	 */
	public boolean delete(String id) {
		int cnt = 0;
		try {
			cnt = template.update("delete from user where id = ?", id);
		} catch(Exception e) {
			log.error("삭제 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
}
