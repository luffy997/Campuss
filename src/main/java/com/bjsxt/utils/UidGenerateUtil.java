package com.bjsxt.utils;

import java.security.SecureRandom;
import java.util.Random;

public class UidGenerateUtil {
	   private static final String SYMBOLS = "0123456789";
	   private static final Random RANDOM = new SecureRandom();

	    /**
	     * 生成10位uid
	     * @return 返回10位uid
	     */
	    public static String generateUidCode() {
	        char[] nonceChars = new char[10];
	        for (int index = 0; index < nonceChars.length; ++index) {
	            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
	        }
	        return new String(nonceChars);
	    }
	}