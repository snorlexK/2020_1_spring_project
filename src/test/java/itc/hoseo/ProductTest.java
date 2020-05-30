package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {
	
	@Autowired
	private ProductRepository repo;
	
	@Test
	public void test() throws Exception {
		assertEquals(1, repo.list().size());		
	}
}
