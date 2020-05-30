package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void test() throws Exception {
		assertEquals(1, repo.list().size());
		 User user = User.builder() 
				 .id("sample12") 
				 .password("1234")
				 .nickname("샘플12") 
				 .registeredDate(new Date()) 
				 .build(); 
		 repo.save(user);
		 assertEquals("sample12", repo.list().get(1).getId()); 
		 repo.delete("sample12");
		 assertEquals(1, repo.list().size());		
	}
}
