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
public interface UserRepository extends CrudRepository<User, String> {

	List<User> findByNickname(String nickname);
	//페이징
	List<User> findAll(Pageable page);
	//Like 검색
	List<User> findByNicknameContains(String nickname, Pageable page);
}

