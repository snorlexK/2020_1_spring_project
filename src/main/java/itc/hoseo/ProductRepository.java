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
public class ProductRepository {
	
	@Autowired
	private JdbcTemplate template;
	
	/**
	 * 물품 추가
	 * 
	 * @param user 저장할 물품 정보
	 * @return 저장 성공하면 true, 실패하면 false
	 */
	public boolean save(Product product) {
		int cnt = 0;
		
		try {
			java.sql.Date date = new java.sql.Date(product.getUploadDate().getTime()); 
			cnt = template.update("insert into product(name, category, price, description, uploadDate, "
				+ "userId, location1, location2, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				product.getId(), product.getCategory(), product.getPrice(), product.getDescription(),
				date, product.getUserId(), product.getLocation1(), product.getLocation2(), 
				product.getImage());
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
		try {
			products = template.query("select * from product", 
					new BeanPropertyRowMapper<Product>(Product.class));
		} catch(Exception e) {
			log.error("회원 정보 로드 오류", e);
		}
		return products;
	}
	
	/**
	 * 물품 수정
	 * 
	 * @param 수정된 물품 정보
	 * @return 수정 성공하면 true, 실패하면 false
	 */
	public boolean update(Product product) {
		int cnt = 0;
		
		try {
			java.sql.Date date = null;
			if (product.getUpdateDate() != null) {
				date = new java.sql.Date(product.getUpdateDate().getTime());
			}
			cnt = template.update("update product set name = ?, category = ?, price = ?, description = ?, "
				+ "updateDate = ?, location1 = ?, location2 = ?, image = ? where id = ?", 
				product.getName(), product.getCategory(), product.getPrice(), product.getDescription(),
				date, product.getLocation1(), product.getLocation2(), product.getImage(),
				product.getId());
		} catch(Exception e) {
			log.error("수정 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
	
	
	/**
	 * 물품 삭제
	 * 
	 * @param id 삭제할 물품의 id
	 * @return 삭제 성공하면 true, 실패하면 false
	 */
	public boolean delete(String id) {
		int cnt = 0;
		try {
			cnt = template.update("delete from products where id = ?", id);
		} catch(Exception e) {
			log.error("삭제 오류", e);
		}
		
		return cnt == 1 ? true : false;
	}
}
