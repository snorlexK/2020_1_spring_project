package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	@Test
	public void test() throws Exception {
		ProductRepository repo = new ProductRepository();
		assertEquals("로지텍 무선 마우스", repo.list().get(0).getName());
	}
}
