package net.zhuoyue.controller;

import java.util.Map;

import net.zhuoyue.dao.IZoyareDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    
    private static Logger logger = LogManager.getLogger(MainController.class.getName());
    
    @Autowired
    private IZoyareDao zoyDao;
	
	@RequestMapping("/")
	public String index(ModelMap model) {
	    Map<String, String> map = zoyDao.findAllSettingsMap();
	    model.addAttribute("index_main_title", map.get("index_main_title"));
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
	
	// ------------zoyare stats-------------
	
	@RequestMapping("/stats")
    public String stats() {
        return "statindex";
    }
	
	@RequestMapping("/stats/zoysetting")
    public String settingPage() {
        return "zoysetting";
    }
}
