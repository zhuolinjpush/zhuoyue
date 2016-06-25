package net.zhuoyue.service;

import java.util.regex.Pattern;

public class Test {

    static Pattern UKEY_PATTERN = Pattern.compile("^[A-Za-z0-9|-]{6,64}$");
    
    public static void main(String[] args) {
//        System.out.println(UKEY_PATTERN.matcher("-100931255457401").matches());
    	String url = "https://www.amazon.com/gp/pdp/profile/A2I2V9MNT3Y545/ref=cm_cr_arp_d_pdp?ie=UTF8";
    	String[] arr = url.split("profile/");
    	System.out.println(arr[1].split("/")[0]);
    }

}
