package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class SecondHandApplicationTests {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	public void test() {
		assertEquals(3, userRepo.findByNickname("테스트").getProducts().size());
		assertEquals("12345", productRepo.findByCategory("게임", PageRequest.of(0, 3)).get(0).getUser().getPassword());
		assertEquals("스위치 네온 구형", productRepo.findById((long) 1).get().getName());
	}

}
