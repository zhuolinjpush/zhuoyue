package net.zhuoyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stat")
public class MainController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

}
