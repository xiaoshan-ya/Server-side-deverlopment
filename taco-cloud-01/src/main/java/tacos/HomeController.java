package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller            // <1>
public class HomeController {

	@GetMapping("/")     // <2>
	public String home() {
		System.out.println("success");
		return "home";     // <3>
	}

}
