package net.zhuoyue.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import net.zhuoyue.dao.IZoyareDao;
import net.zhuoyue.vo.ZoyareSetting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/")
public class MainController {
    
    private static Logger logger = LogManager.getLogger(MainController.class.getName());
    private static String SUCCESS = "1";
    private static String FAILD = "0";
    
    @Autowired
    private IZoyareDao zoyDao;
	
    //------official website----
    
	@RequestMapping("/")
	public String index(ModelMap model) {
	    logger.info("view index.jsp");
	    String pageid = "index";
	    Map<String, String> map = zoyDao.findAllSettingsMap(pageid);
	    for (String key : map.keySet()) {
	        model.addAttribute(key, map.get(key));
	    }
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
	
	//*************start home page setting*********//
	@RequestMapping("/stats")
    public String stats() {
        return "statindex";
    }
	
	//home page setting
	@RequestMapping("/zoyhomepage")
    public ModelAndView settingPage() {
	    logger.info("view zoyhomepage");
	    List<ZoyareSetting> homeList = zoyDao.findAllSettings("index");
	    ModelAndView model = new ModelAndView();
	    model.addObject("homeList", homeList);
	    model.setViewName("zoyhomepage");
        return model;
    }
	
	@RequestMapping(value="/getjsondata")
	public void getJsonData(String params, PrintWriter out) {
	    String data = "{data:[]}";
	    try {
	        logger.info("get json params:" + params);
	        if (null != params && !"".equals(params)) {
	            JSONArray array = new JSONArray();
	            JSONObject object = new JSONObject();
	            List<ZoyareSetting> homeList = zoyDao.findAllSettings(params);
	            for (ZoyareSetting zoy : homeList) {
	                array.add(zoy);
	            }
	            object.put("data", array);
	            data = object.toJSONString();

	        }
	        logger.info("data:" + data);
	        out.write(data);
	    } catch (Exception e) {
	        logger.error("get json data error", e);
	    }
	}
	
	@RequestMapping("/addsetting")
	public void addSetting(String setid, String pageid, String note, String content, PrintWriter out) {
	    logger.info(setid + " " + pageid + " " + note + " " + content);
	    boolean flag = zoyDao.addSetting(new ZoyareSetting(setid, pageid, note, content, ""));
	    if (flag) {
	        out.write(SUCCESS);
	    } else {
	        out.write(FAILD);
	    }
	}
	
	@RequestMapping("/delsetting")
	public void delSetting(String setid, String pageid, PrintWriter out) {
	    logger.info(setid + " " + pageid);
	    boolean flag = zoyDao.delSetting(new ZoyareSetting(setid, pageid, "", "", ""));
	    if (flag) {
            out.write(SUCCESS);
        } else {
            out.write(FAILD);
        }
	}
	
	//*************end home page setting*********//
	
	
	
	
	//modify setting action
	@RequestMapping("/zoysetaction")
	public String zoysetaction() {
	    
	    return "success";
	}
}
