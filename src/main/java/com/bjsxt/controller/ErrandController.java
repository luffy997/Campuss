package com.bjsxt.controller;

import static com.bjsxt.utils.DateUtils.getCurrentTime;
import static com.bjsxt.utils.OrderBidGenerateUtil.generateBidCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.ErrandService;

@RestController
@RequestMapping("/api/errand")
public class ErrandController {

	@Autowired
	private ErrandService errandService;
	
	/*
	 * 发布订单
	 */
	@GetMapping("/errandIssue")
	@ResponseBody
	public Map generatErrand(Errand errand) {
		//测试
//		errand.setUid("103");
//		errand.setErrandType(0);
//		errand.setRemark("杯子，轻放");
//		errand.setSendLocation("10A324");
//		errand.setPrice(10);
//		errand.setContact("15623206870");
//		errand.setQQ("389783961");
		
		String bidRes=generateBidCode();
		errand.setBid(bidRes);
		
		Orders orders=new Orders();
		orders.setBid(bidRes);
		orders.setStartTime(getCurrentTime());
		Map map=new HashMap();
		int indexErrand=errandService.insErrand(errand);
		int indexOrder=errandService.insStatus(orders);
		if (indexErrand>0 && indexOrder>0) {
			map.put("status","200");
			return map;
		}else {
			map.put("status","100");
			return map;
		}
	}
	
	/*
	 * 显示跑腿订单列表
	 */
	@ResponseBody
	@GetMapping("/showErrand")
	public List<Errand> showErandList(){
		List<Errand> list = errandService.showErrandList();
		return list;
	}
	
	/**
	 * 显示跑腿订单详情
	 * @param errand 前端传回bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showDetailErrand")
    public Map showDetailErrand(Errand errand){
		//前端传回bid
		//errand.setBid("82657235297434263149");
		Map map=new HashMap();
		Errand resErrand=errandService.showErrand(errand);
		
		Users users=errandService.selNickNameAva(resErrand.getUid());
		
		Orders orders=errandService.selStartTimeStatus(errand);
		
		map.put("nickName", users.getNickName());
		map.put("avatarUrl", users.getAvatarUrl());
		map.put("openId", users.getOpenId());
		map.put("startTime", orders.getStartTime());
		map.put("fettle", orders.getFettle());
		map.put("receiver",orders.getReceiver());
		map.put("uid", resErrand.getUid());
		map.put("errandType", resErrand.getErrandType());
		map.put("remark", resErrand.getRemark());
		map.put("sendLocation", resErrand.getSendLocation());
		map.put("price", resErrand.getPrice());
		map.put("contact", resErrand.getContact());
		map.put("QQ", resErrand.getQQ());
		map.put("bid", resErrand.getBid());
		map.put("weight", resErrand.getWeight());
		return map;
	
	}
}