package net.zhuoyue.impl;

import java.util.GregorianCalendar;
import java.util.List;

import net.zhuoyue.dao.IZoyareDao;
import net.zhuoyue.vo.ZoyareSetting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

    
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
//        
////        Reviewer rev = new Reviewer("A2EBDTDH4X8ZAO","raphael mizrahi", "9",9,"#41,485,184",41485184,"","https://www.amazon.com/gp/pdp/profile/A2EBDTDH4X8ZAO/ref=cm_cr_arp_d_pdp?ie=UTF8");
////        List<Reviewer> reviewerList = new ArrayList<Reviewer>();
////        reviewerList.add(rev);
////        ISkuReviewDao dao = (ISkuReviewDao) context.getBean("SkuReviewDao");
////        dao.addReviewer(reviewerList);
//        
////        ZoyareSetting zoy = new ZoyareSetting("test1", "test", "11111");
//        IZoyareDao zoyDao = (IZoyareDao) context.getBean("ZoyareSettingDao");
////        zoyDao.addSetting(zoy);
//        JSONArray array = new JSONArray();
//        JSONObject object = new JSONObject();
//        List<ZoyareSetting> homeList = zoyDao.findAllSettings("home");
//        for (ZoyareSetting zoy : homeList) {
//            array.add(zoy);
//        }
//        object.put("data", array);
//        String data = object.toJSONString();
//        System.out.println(data);
      
        for (int i=20160701; i<=20160707; i++) {
//            int year = Integer.parseInt(String.valueOf(i).substring(0, 4));
//            int mon = Integer.parseInt(String.valueOf(i).substring(4, 6)) - 1;
//            int day = Integer.parseInt(String.valueOf(i).substring(6, 8));
//            GregorianCalendar calendar = new GregorianCalendar(year, mon, day, 0, 0);
//            long start = calendar.getTimeInMillis()/1000;
//            calendar.add(5, 1);
//            long end = calendar.getTimeInMillis()/1000;
//            System.out.println(start + " " + end);
            System.out.println(i);
        }

        
    }

}
