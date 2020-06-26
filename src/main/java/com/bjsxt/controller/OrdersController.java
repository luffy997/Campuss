package com.bjsxt.controller;

import static com.bjsxt.utils.DateUtils.getCurrentTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.OrdersService;
import com.bjsxt.utils.HttpRequest;
import com.bjsxt.utils.TemplateData;
import com.bjsxt.utils.WxMssVo;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/receive")
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	/**
	 * 接单功能
	 * 
	 * @param orders 前端传回uid bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/orders")
	public Map receiveOrders(String uid, Orders orders) {
		// 测试
		//uid="0417180122";
		orders.setReceiver(uid);
		//orders.setBid("1024");
		
		 //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx8909f21184aee9c0";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "6b411e219903ec5a0f9c14bacce44dc9";
        //授权（必填）
        String grant_type = "client_credential";
        
        
		orders.setReceiveTime(getCurrentTime());
		Map map = new HashMap();
		int index = ordersService.insOrders(orders);
		if (index > 0) {
			Orders order=ordersService.selReceiveTime(orders.getBid());
			
			//根据bid  找到价格和openId
			int price_temp=0;
			String openId_temp=null;
			String title_temp=null;
			Errand errand=ordersService.selOpenId_one(orders.getBid());
			Markets markets=ordersService.selOpenId_two(orders.getBid());
			Skillserv skillserv=ordersService.selOpenId_three(orders.getBid());
			if (errand !=null) {
				//price_temp=errand.getPrice();
				openId_temp=errand.getOpenId();
				title_temp="接单通知——跑腿代拿";
			}
			if (markets !=null) {
				//price_temp=markets.getNowPrice();
				openId_temp=markets.getOpenId();
				title_temp="接单通知——跳蚤市场";
			}
			if (skillserv !=null) {
				//=skillserv.getPrice();
				openId_temp=skillserv.getOpenId();
				title_temp="接单通知——技能服务";
			}
			
			//String price=String.valueOf(price_temp)+"元";
			
			//创建https请求，获取Access_token
	        String params = "grant_type=" + grant_type + "&appid=" + wxspAppid + "&secret=" + wxspSecret;
	        //发送请求
	        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
	        //解析相应内容（转换成json对象）
	        JSONObject json = JSONObject.fromObject(sr);
			String Access_Token = json.getString("access_token");
			String expires_in = json.getString("expires_in");			
			
			//发送模板信息
			RestTemplate restTemplate = new RestTemplate();
	        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
	        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" +Access_Token;
	        //拼接推送的模版
	        WxMssVo wxMssVo = new WxMssVo();
	        wxMssVo.setTouser(openId_temp);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
	        wxMssVo.setTemplate_id("a4JqS-Qr4xRDs94L94Wz2fiVk7ROJ6_2xFA-pZgE5E4");//订阅模板id
	        wxMssVo.setPage("pages/index/index");
	        
	        //格式时间输出
	    	Date d = new Date();
	    	DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	    	String time = df.format(d);
	    	
	    	//配置推送信息规则
	        Map<String, TemplateData> m = new HashMap<>(3);
	        m.put("thing8", new TemplateData(title_temp));
	        m.put("character_string4", new TemplateData(orders.getBid()));
	        m.put("date3", new TemplateData(time));
	        m.put("thing2", new TemplateData("您发布的该订单已被接手！"));

	        wxMssVo.setData(m);
	        ResponseEntity<String> responseEntity =
	                restTemplate.postForEntity(url, wxMssVo, String.class);
			map.put("msg", responseEntity.getBody());
	        map.put("receiveTime", order.getReceiveTime());
			map.put("status", "200");
			return map;
		} else {
			map.put("status", "100");
			map.put("msg","接单失败");
			return map;
		}
	}

	/**
	 * 拼车服务 前端传回uid bid
	 * 
	 * @param uid
	 * @param orders bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/carpooling")
	public Map carpooling(String uid, Orders orders) {
		// 测试
		 uid="0417180122";
		orders.setReceiver(uid);
		 orders.setBid("101");
		// 查receiver和peopleNum
		Orders order = ordersService.selOrders(orders);
		System.out.println("order====" + order);
		Share share = ordersService.selPeopleNum(orders);
		int receiverNum = 0; // 现接单人数
		int people = share.getPeopleNum();
		System.out.println("拼车人数========="+people);
		String[] uidArray;
		Map map = new HashMap();
		// 填入过程
		if (order == null) {
			StringBuffer sb = new StringBuffer(uid);
			orders.setReceiver(sb.toString());
			// 接单人数为0
			// 追加第一个接单人
			receiverNum += 1;
		} else {
			StringBuffer sb = new StringBuffer(order.getReceiver());
			sb.append("," + uid);
			orders.setReceiver(sb.toString());
			uidArray = sb.toString().split(",");
			receiverNum = uidArray.length;
		}

		int index = 0;
		// 判断是否满员
		if (receiverNum >= people) {
			// 满员
			orders.setReceiveTime(getCurrentTime());
			index = ordersService.insOrders(orders);
			Orders orderTemp =ordersService.selReceiveTime(orders.getBid());
			map.put("receiveTime", orderTemp.getReceiveTime());
			
			// 拼车满员信息发送
			Share shares=ordersService.selOpenId_four(orders.getBid());
			
			 //小程序唯一标识   (在微信小程序管理后台获取)
	        String wxspAppid = "wx8909f21184aee9c0";
	        //小程序的 app secret (在微信小程序管理后台获取)
	        String wxspSecret = "6b411e219903ec5a0f9c14bacce44dc9";
	        //授权（必填）
	        String grant_type = "client_credential";
			//创建https请求，获取Access_token
	        String params = "grant_type=" + grant_type + "&appid=" + wxspAppid + "&secret=" + wxspSecret;
	        //发送请求
	        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
	        //解析相应内容（转换成json对象）
	        JSONObject json = JSONObject.fromObject(sr);
			String Access_Token = json.getString("access_token");
			String expires_in = json.getString("expires_in");			
			
			//发送模板信息
			RestTemplate restTemplate = new RestTemplate();
	        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
	        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" +Access_Token;
	        //拼接推送的模版
	        WxMssVo wxMssVo = new WxMssVo();
	        wxMssVo.setTouser(shares.getOpenId());//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
	        wxMssVo.setTemplate_id("a4JqS-Qr4xRDs94L94Wz2fiVk7ROJ6_2xFA-pZgE5E4");//订阅模板id
	        wxMssVo.setPage("pages/index/index");
	        
	        //格式时间输出
	    	Date d = new Date();
	    	DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	    	String time = df.format(d);
	    	
	    	//配置推送信息规则
	        Map<String, TemplateData> m = new HashMap<>(3);
	        m.put("thing8", new TemplateData("接单通知——拼车滴滴"));
	        m.put("character_string4", new TemplateData(orders.getBid()));
	        m.put("date3", new TemplateData(time));
	        m.put("thing2", new TemplateData("您发布的拼车订单已被满员了，可以出发了！"));

	        wxMssVo.setData(m);
	        ResponseEntity<String> responseEntity =restTemplate.postForEntity(url, wxMssVo, String.class);
	        map.put("msg", responseEntity.getBody());
			
			// receiverNum=uidArray.length;
		} else {
			// 未满，仅更新receiver
			index = ordersService.updReceiver(orders);
		}

		
		if (index > 0) {
			map.put("status", "200");
			map.put("receiverNum", receiverNum);
			return map;
		} else {
			map.put("status", "-1");
			map.put("errMsg", "接单失败！");
			return map;
		}
	}

	/**
	 * 查询用户所有订单
	 * 
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/myIssue")
	public Map selAllOrders(String uid) {
		// 测试
		//uid = "0417180122";
		List<Orders> errandlList = ordersService.selErrand(uid);
		List<Orders> marketlList=ordersService.selMarket(uid);
		List<Orders> sharelList=ordersService.selShare(uid);
		List<Orders> skiiServList=ordersService.selSkillServ(uid);
		Users users = ordersService.selUsers(uid);
		
		// 一维map
		Map map = new HashMap();

		// 二维map
		Map orders = new HashMap();
		Map errand = new HashMap();
		Map market = new HashMap();
		Map share = new HashMap();
		Map skillserv = new HashMap();
		
		if (errandlList !=null || marketlList !=null || sharelList !=null || skiiServList !=null) {
			map.put("errand", errandlList);
			map.put("market", marketlList);
			map.put("share", sharelList);
			map.put("skillserv", skiiServList);
			map.put("users", users);
			map.put("status", "200");
			return map;
		} else {
			map.put("status", "100");
			return map;
		}

	}
	
	/**
	 * 撤销订单 
	 * @param bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/delOrder")
	public Map delOrders(String bid) {
		//测试
		//bid="11111";
		int index_one=ordersService.delErrand(bid);
		int index_two=ordersService.delMarket(bid);
		int index_three=ordersService.delShare(bid);
		int index_four=ordersService.delSkillServ(bid);
		ordersService.delOrders(bid);
		
		Map map=new HashMap();
		if (index_one>0 || index_two>0 || index_three>0 || index_four>0) {
				map.put("status", "200");
				return map;	
		}else {
			map.put("status", "-100");
			map.put("msg","订单删除失败");
			return map;
		}
	}
	
	/**
	 * 完成订单业务
	 * @param share bid uid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/finishOrder")
	public Map finishOrder(Share share) {
		//测试
		//share.setBid("32313425272552840325");
		//share.setUid("0417180122");
		Users users_one =ordersService.selWeatherMatchErrand(share);
		Users users_two =ordersService.selWeatherMatchMarket(share);
		Users users_three =ordersService.selWeatherMatchShare(share);
		Users users_four =ordersService.selWeatherMatchSkillServ(share);
		
		Orders orders =new Orders();
		orders.setFishTime(getCurrentTime());
		orders.setBid(share.getBid());
		int index=ordersService.updOrders(orders);
		
		Map map=new HashMap();
		if (users_one !=null || users_two !=null || users_three !=null ||users_four !=null) {
			if (index >0) {
				map.put("status","200");
				return map;
			} else {
				map.put("status","100");
				map.put("msg","更新orders表失败");
				return map;
			}
			
		}else {
			map.put("status","100");
			map.put("msg","匹配业务表失败");
			return map;
			
		}
	}
	
	/**
	 * 查询用户接收的订单
	 * @param receive 前端传回receiver  即uid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/selMyReceiveOrders")
	public Map selMyReceiveOrders(String receiver) {
		//测试
		//receiver="0517180122";
		Map map=new HashMap();
		//拼接模糊查询
		String receiverStr="%"+receiver+"%";
		
		List<Orders> errandOrdersList=ordersService.selMyReceiveErand(receiver);
		List<Orders> marketOrdersList=ordersService.selMyReceiveMarket(receiver);
		List<Orders> shareOrdersList=ordersService.selMyReceiveShare(receiverStr);
		List<Orders> skillervOrdersList=ordersService.selMyReceiveSkillserv(receiver);
		
		if (errandOrdersList !=null || marketOrdersList != null || shareOrdersList != null || skillervOrdersList != null) {
			map.put("errand", errandOrdersList);
			map.put("market", marketOrdersList);
			map.put("share", shareOrdersList);
			map.put("skillserv", skillervOrdersList);
			return map;
		}
		map.put("msg", "该用户还没有接单");
		return map;
	}
}

