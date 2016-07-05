package net.zhuoyue.impl;

import net.zhuoyue.dao.IZoyareDao;
import net.zhuoyue.vo.ZoyareSetting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
        
//        Reviewer rev = new Reviewer("A2EBDTDH4X8ZAO","raphael mizrahi", "9",9,"#41,485,184",41485184,"","https://www.amazon.com/gp/pdp/profile/A2EBDTDH4X8ZAO/ref=cm_cr_arp_d_pdp?ie=UTF8");
//        List<Reviewer> reviewerList = new ArrayList<Reviewer>();
//        reviewerList.add(rev);
//        ISkuReviewDao dao = (ISkuReviewDao) context.getBean("SkuReviewDao");
//        dao.addReviewer(reviewerList);
        
//        ZoyareSetting zoy = new ZoyareSetting("test1", "test", "11111");
//        IZoyareDao zoyDao = (IZoyareDao) context.getBean("ZoyareSettingDao");
//        zoyDao.addSetting(zoy);
    
    }

}
