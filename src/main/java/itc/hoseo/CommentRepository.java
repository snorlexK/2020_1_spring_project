package itc.hoseo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Sample JPA Repository
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html
 * @author pjh04
 *
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByUser(User user, Pageable page);
	List<Comment> findByProduct(User user, Pageable page);
}

