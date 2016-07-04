package net.zhuoyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("/")
	public String index() {
        return "index";
	}
	
	@RequestMapping("/index")
	public String indexjsp() {
        return "index";
	}

	@RequestMapping("/company")
    public String company() {
        return "company";
    }
	
	@RequestMapping("/product")
    public String product() {
        return "product";
    }
	
	@RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
	
	@RequestMapping("/stats")
    public String stats() {
        return "statindex";
    }
}
