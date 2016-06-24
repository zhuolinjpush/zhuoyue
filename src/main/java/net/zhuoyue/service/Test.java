package net.zhuoyue.service;

import java.util.regex.Pattern;

public class Test {

    static Pattern UKEY_PATTERN = Pattern.compile("^[A-Za-z0-9|-]{6,64}$");
    
    public static void main(String[] args) {
        System.out.println(UKEY_PATTERN.matcher("-100931255457401").matches());

    }

}
