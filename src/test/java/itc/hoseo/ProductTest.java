package itc.hoseo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class ProductTest {
	
	@Autowired
	private ProductRepository repo;
	
	@BeforeEach()
	public void init() {
		repo.save(new Product(null, "스위치 네온 구형", "게임", 200000, "옛날에 사두고 두어번 쓰고 안 쓴 제품입니다.",
				new Date(), null, "test", "서울시 강서구", "마곡동", null, null));
		repo.save(new Product(null, "파이썬 책", "서적", 7000, "중고감 약간 있는 파이썬 책 팝니다",
				new Date(), null, "test", "서울시 강서구", "마곡동", null, null));
		repo.save(new Product(null, "닌텐도 스위치 포켓몬스터 소드", "게임", 25000, "별로 안썼구요 케이스 포함입니다",
				new Date(), null, "ditto", "경기도 안양시", "범계동", null, null));
		repo.save(new Product(null, "링피트", "게임", 100000, "링, 게임팩만 팝니다. 본체 X",
				new Date(), null, "hem22", "경기도 안양시", "호계동", null, null));
	}
	
	@Test
	public void test() {
		assertEquals(4, repo.findAll(PageRequest.of(0, 10)).size());
		assertEquals("경기도 안양시", repo.findAll(PageRequest.of(0, 5)).get(2).getLocation1());
		assertEquals(2, repo.findByLocation1("서울시 강서구", PageRequest.of(0, 5)).size());
		assertEquals(2, repo.findByNameContains("스위치", PageRequest.of(0, 5)).size());
		assertEquals(1, repo.findByUserId("ditto", PageRequest.of(0, 5)).size());
		assertEquals(3, repo.findByCategory("게임", PageRequest.of(0, 3)).size());
	}
}
