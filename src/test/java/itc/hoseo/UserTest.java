package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserRepository repo;
	
	@BeforeEach()
	public void init() {
		repo.save(new User("test", "1234", "테스트", new Date()));
		repo.save(new User("test12", "12331234", "테스트12", new Date()));
		repo.save(new User("hem22", "12345", "기매미", new Date()));
		repo.save(new User("snolex", "987654", "잠만보", new Date()));
		repo.save(new User("ditto", "1234", "메타몽", new Date()));
		repo.save(new User("mong", "1234", "MONG", new Date()));
	}
	
	@Test
	public void test() {
		assertEquals(3, repo.findAll(PageRequest.of(0, 3)).size());		
		assertEquals("test", repo.findByNickname("테스트").get(0).getId());
		assertEquals(2, repo.findByNicknameContains("테스트", PageRequest.of(0, 10)).size());
	}
}
