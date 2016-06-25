package net.zhuoyue.dao;

import java.util.ArrayList;
import java.util.List;

import net.zhuoyue.helper.DBHelper;
import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoHelper {
	
	private static Logger logger = LogManager.getLogger(DaoHelper.class.getName());
	
	public static void saveSku(Sku data) {
		List<Sku> list = new ArrayList<Sku>();
		list.add(data);
		DBHelper.saveSku(list);
	}
	
	public static void saveSku(List<Sku> data) {
		DBHelper.saveSku(data);
	}
	
	public static void saveReviewer(List<Reviewer> data) {
		DBHelper.saveReviewer(data);
	}

	public static void saveSkuReviewer(List<SkuReviewer> data) {
		DBHelper.saveSkuReview(data);
	}
	
	public static void main(String[] args) {
//		Sku data = new Sku("123", "222", "23", "23223", 33);
//		DaoHelper.saveSku(data);
		Reviewer rev = new Reviewer("A2EBDTDH4X8ZAO","raphael mizrahi",	"9",9,"#41,485,184",41485184,"","https://www.amazon.com/gp/pdp/profile/A2EBDTDH4X8ZAO/ref=cm_cr_arp_d_pdp?ie=UTF8");
		List<Reviewer> list = new ArrayList<Reviewer>();
		DaoHelper.saveReviewer(list);
	
	}
}
