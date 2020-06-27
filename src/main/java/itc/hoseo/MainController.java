package itc.hoseo;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CommentRepository commentRepo;
	
	@PostConstruct
	public void init() {
		User u1 = User.builder().id("test").password("1234").nickname("테스트").registeredDate(new Date()).build();
		User u2 = User.builder().id("asdf1234").password("789").nickname("닉네임").registeredDate(new Date()).build();
		User u3 = User.builder().id("rlacjswo").password("12345").nickname("김천재").registeredDate(new Date()).build();
		userRepo.save(u1);
		userRepo.save(u2);
		userRepo.save(u3);
		
		Product p1 = new Product(null, "스위치 네온 구형", "게임", 200000, "옛날에 사두고 두어번 쓰고 안 쓴 제품입니다.",
				new Date(), null, userRepo.findByNickname("김천재"), "서울",  null, null, null);
		Product p2 = new Product(null, "파이썬 책", "서적", 7000, "중고감 약간 있는 파이썬 책 팝니다",
				new Date(), null, userRepo.findByNickname("테스트"), "서울",  null, null, null);
		Product p3 = new Product(null, "닌텐도 스위치 포켓몬스터 소드", "게임", 25000, "별로 안썼구요 케이스 포함입니다",
				new Date(), null, userRepo.findByNickname("테스트"), "경기", null, null, null);
		Product p4 = new Product(null, "링피트", "게임", 100000, "링, 게임팩만 팝니다. 본체 X",
				new Date(), null, userRepo.findByNickname("테스트"), "경기", null, null, null);
		productRepo.save(p1);
		productRepo.save(p2);
		productRepo.save(p3);
		productRepo.save(p4);
		
		Comment c1 = Comment.builder().content("혹시 양천구에서는 거래 안될까요?").user(u1).product(p1).uploadDate(new Date()).build();
		commentRepo.save(c1);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET) 
	public String main(ModelMap mm) {
		return "index";
	}
	@RequestMapping(path = "/list", method = RequestMethod.GET) 
	public String list(ModelMap mm) {
		mm.put("products", productRepo.findAll(PageRequest.of(0, 7)));
		return "list";
	}
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public String search(ModelMap mm, String name, String location, int priceMin, int priceMax) {
		
		return "list";
	}
	@RequestMapping(path = "/mypage", method = RequestMethod.GET) 
	public String myPage(ModelMap mm) {
		return "mypage";
	}
	@RequestMapping(path = "/post", method = RequestMethod.GET) 
	public String post(ModelMap mm) {
		return "post";
	}
	@RequestMapping(path = "/login", method = RequestMethod.GET) 
	public String login(ModelMap mm) {
		return "login";
	}
	@RequestMapping(path = "/content", method = RequestMethod.GET) 
	public String content(ModelMap mm, Long product_id) {
		mm.put("product", productRepo.findById(product_id).get());
		return "content";
	}
}
