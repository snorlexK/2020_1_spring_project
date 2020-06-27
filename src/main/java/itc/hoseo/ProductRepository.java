package itc.hoseo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Sample JPA Repository
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html
 * @author pjh04
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
	//페이징
	List<Product> findAll(Pageable page);
	List<Product> findByCategory(String category, Pageable page);
	List<Product> findByUserId(String userId, Pageable page);
	List<Product> findByLocation(String location, Pageable page);
	//Like 검색
	List<Product> findByNameContains(String name, Pageable page);
}
