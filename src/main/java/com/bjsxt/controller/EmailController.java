package com.bjsxt.controller;


import static com.bjsxt.utils.VerCodeGenerateUtil.generateVerCode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.service.EmailService;


/**
 * @author: Kellen-PC
 * @date: 2019/5/5
 * @description:
 */
@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    
    //前端传回，前端传回用户邮箱，后端发送验证码
    @PostMapping("/api/email")
    @ResponseBody
    public Object sendEmail(String emailAddress) {
    	//emailAddress="3110236942@qq.com";
    	String code= generateVerCode();
    	Map<String, String> hashMap=new HashMap<String, String>();
        try {
            emailService.sendEmailVerCode(emailAddress,code);
            hashMap.put("maillCode", code);
            hashMap.put("msg", "success");
            System.out.println("hashmap===="+hashMap);
            return hashMap;
        } catch (Exception e) {
        	hashMap.put("errorSession", e.toString());
            return hashMap;
        }
    }
//
//    //前端传回String mailCode，后端验证与发送的验证码是否一致
//    @GetMapping("/api/verification")
//    @ResponseBody
//    public Object mailCode(String mailCode,String mailCoding) {
//    	//mailcoding 用户输入的邮箱验证码
//    	//mailCode   邮箱发送到验证码
//    	//测试
////    	mailCode="123";
////    	mailCoding="123";
//    	Map<String, String> hashMap=new HashMap<String, String>();
//    	
//        try {
//        	System.out.println("mailCoding==="+mailCoding);
//        	System.out.println("mailCode==="+mailCode);
//            if (mailCode!=mailCoding) {
//            	hashMap.put("status", "0");
//				return hashMap;   //不正确的
//			}
//            hashMap.put("status", "1"); //正确的
//            return hashMap;
//        } catch (Exception e) {
//        	hashMap.put("status", "0");  //不正确的
//            return hashMap;
//        }
//    }
}

