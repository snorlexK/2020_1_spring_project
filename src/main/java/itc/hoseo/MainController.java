package itc.hoseo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping(path = "/", method = RequestMethod.GET) 
	public String main(ModelMap mm) {
		mm.put("list", "");
		return "index";
	}
}
