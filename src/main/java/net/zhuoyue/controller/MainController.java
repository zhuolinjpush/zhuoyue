package net.zhuoyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/stat")
public class MainController {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");  
        return "hello";
	}

}
