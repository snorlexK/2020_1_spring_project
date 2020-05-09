package itc.hoseo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductRepository {
	public ProductRepository() {
		String sql = "create table if not exists product("
				+ "id int primary key identity, "
				+ "name varchar(50) not null, "
				+ "category varchar(50) not null, "
				+ "price int not null, "
				+ "description varchar(255) not null, "
				+ "uploadDate datetime not null, "
				+ "userId varchar(30) not null, "
				+ "location1 varchar(10) not null, "
				+ "location2 varchar(10) not null, "
				+ "soldDate datetime, "
				+ "image varchar(255), "
				+ ")";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("테이블 생성 오류", e);
		}
		sql = "insert into product(name, category, price, description, uploadDate, userId, "
				+ "location1, location2) values(?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "로지텍 무선 마우스");
			pstmt.setString(2, "디지털/가전");
			pstmt.setInt(3, 18000);
			pstmt.setString(4, "구매 후 한번 쓰고 안쓴 거의 새 마우스입니다");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setString(6, "sample");
			pstmt.setString(7, "서울시");
			pstmt.setString(8, "강서구");
			pstmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("샘플 데이터 삽입 오류", e);
		}
	}
	
	public Connection dbConnect() throws Exception {
		return DriverManager.getConnection("jdbc:hsqldb:mem:myDb", "sa", "sa");
	}
	
	/**
	 * 물품 추가
	 * 
	 * @param user 저장할 물품 정보
	 * @return 저장 성공하면 true, 실패하면 false
	 */
	public boolean add(Product product) {
		int cnt = 0;
		String sql = "insert into product(name, category, price, description, uploadDate, userId, "
				+ "location1, location2, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			java.sql.Date date = new java.sql.Date(product.getUploadDate().getTime()); 
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getCategory());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setDate(5, date);
			pstmt.setString(6, product.getUserId());
			pstmt.setString(7, product.getLocation1());
			pstmt.setString(8, product.getLocation2());
			pstmt.setString(9, product.getImage());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			log.error("저장 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
	
	/** 
	 * 물품 정보 모두 불러오기
	 * 
	 * @return 물품 전체 목록
	 */
	public List<Product> list() {
		List<Product> products = new ArrayList<Product>();
		String sql = "select * from product";
		try (Connection con = dbConnect(); PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery();) {
			while(rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.category(rs.getString("category"))
						.price(rs.getInt("price"))
						.description(rs.getString("description"))
						.uploadDate(rs.getDate("uploadDate"))
						.userId(rs.getString("userId"))
						.location1(rs.getString("location1"))
						.location2(rs.getString("location2"))
						.soldDate(rs.getDate("soldDate"))
						.image(rs.getString("image"))
						.build();
				products.add(product);
			}
		} catch(Exception e) {
			log.error("회원 정보 로드 오류", e);
		}
		return products;
	}
	
	/**
	 * 물품 삭제
	 * 
	 * @param id 삭제할 물품의 id
	 * @return 삭제 성공하면 true, 실패하면 false
	 */
	public boolean delete(String id) {
		int cnt = 0;
		String sql = "delete from products where id=?";
		try (Connection con = dbConnect(); PreparedStatement pstmt =  con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			cnt =pstmt.executeUpdate();
		} catch(Exception e) {
			log.error("삭제 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
}
