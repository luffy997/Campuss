package com.bjsxt.utils;

import java.security.SecureRandom;
import java.util.Random;

public class OrderBidGenerateUtil {
	   private static final String SYMBOLS = "0123456789";
	   private static final Random RANDOM = new SecureRandom();

	    /**
	     * 生成20位bid
	     * @return 返回20位bid
	     */
	    public static String generateBidCode() {
	        char[] nonceChars = new char[20];
	        for (int index = 0; index < nonceChars.length; ++index) {
	            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
	        }
	        return new String(nonceChars);
	    }
	}