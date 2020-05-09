package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	public void test() throws Exception {
		UserRepository repo = new UserRepository();
		User user = User.builder()
				.id("sample12")
				.password("1234")
				.nickname("샘플12")
				.registeredDate(new Date())
				.build();
		repo.add(user);
		assertEquals("sample", repo.list().get(0).getId());
		repo.delete("sample12");
		assertEquals(1, repo.list().size());
	}
}
