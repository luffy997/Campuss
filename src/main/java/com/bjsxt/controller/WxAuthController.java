package com.bjsxt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import com.bjsxt.utils.AesCbcUtil;
import com.bjsxt.utils.HttpRequest;

import net.sf.json.JSONObject;

@RestController
public class WxAuthController {
	
	@Autowired
	private UsersService usersService;
	
	/**
     * 解密用户敏感数据
     *
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return
     */
    @ResponseBody
    @PostMapping("/api/decodeUserInfo")
    public Map decodeUserInfo(String encryptedData, String iv, String code,Users users) {
        Map map = new HashMap();
        //code="023z8Lx20QpW5H1EP0w20sdmx20z8Lxs";
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx8909f21184aee9c0";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "6b411e219903ec5a0f9c14bacce44dc9";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        System.out.println("session_key===="+session_key);
        //用户的唯一标识（openid）
        String openId = (String) json.get("openId");
        System.out.println("openid======"+openId);
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.fromObject(result);
                Map wxuserInfo = new HashMap();
                wxuserInfo.put("openId", userInfoJSON.get("openId"));
                wxuserInfo.put("nickName", userInfoJSON.get("nickName"));
                wxuserInfo.put("gender", userInfoJSON.get("gender"));
                wxuserInfo.put("city", userInfoJSON.get("city"));
                wxuserInfo.put("province", userInfoJSON.get("province"));
                wxuserInfo.put("country", userInfoJSON.get("country"));
                wxuserInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                wxuserInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("wxuserInfo", wxuserInfo);
              
                //根据openid 查询是否在uid (登陆操作)
                String res = (String) userInfoJSON.get("openId");
                users.setOpenid(res);
                Users user = usersService.selUser(users);
                if (user!=null) {
                	 map.put("uid",user.getUid());
                	 Map userInfo = new HashMap();
                	 userInfo.put("nickName", user.getNickName());
                	 userInfo.put("gender", user.getGender());
                	 userInfo.put("avatarUrl", user.getAvatarUrl());
                	 userInfo.put("email", user.getEmail());
                	 map.put("userInfo", userInfo);
                     map.put("msg", "uid存在，该用户已注册可登录");
    			}else {
                     map.put("msg", "uid不存在，该用户未注册");
    			}
                return map;
            } 
            
            //前端传回openId 查询是否在uid (登陆操作)
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
}
