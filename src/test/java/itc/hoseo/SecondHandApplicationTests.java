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
	
	@BeforeEach()
	public void init() {
		User u1 = User.builder().id("test").password("1234").nickname("테스트").registeredDate(new Date()).build();
		User u2 = User.builder().id("asdf1234").password("789").nickname("닉네임").registeredDate(new Date()).build();
		userRepo.saveAll(Lists.list(
				u1, 
				u2,
				User.builder().id("rlaguswo").password("12345").nickname("김현재").registeredDate(new Date()).build()
		));
		
		Product p1 = new Product(null, "스위치 네온 구형", "게임", 200000, "옛날에 사두고 두어번 쓰고 안 쓴 제품입니다.",
				new Date(), null, userRepo.findByNickname("김현재"), "서울시 강서구", "마곡동", null, null, null);
		Product p2 = new Product(null, "파이썬 책", "서적", 7000, "중고감 약간 있는 파이썬 책 팝니다",
				new Date(), null, userRepo.findByNickname("테스트"), "서울시 강서구", "마곡동", null, null, null);
		Product p3 = new Product(null, "닌텐도 스위치 포켓몬스터 소드", "게임", 25000, "별로 안썼구요 케이스 포함입니다",
				new Date(), null, userRepo.findByNickname("테스트"), "경기도 안양시", "범계동", null, null, null);
		Product p4 = new Product(null, "링피트", "게임", 100000, "링, 게임팩만 팝니다. 본체 X",
				new Date(), null, userRepo.findByNickname("테스트"), "경기도 안양시", "호계동", null, null, null);
		productRepo.saveAll(Lists.list(
				p1, p2, p3, p4
		));
		
		Comment c1 = Comment.builder().content("혹시 양천구에서는 거래 안될까요?").user(u1).product(p1).build();
		commentRepo.save(c1);		
	}
	
	@Test
	public void test() {
		assertEquals(3, userRepo.findByNickname("테스트").getProducts().size());
		assertEquals("12345", productRepo.findByCategory("게임", PageRequest.of(0, 3)).get(0).getUser().getPassword());
	}

}
