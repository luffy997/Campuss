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

import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.SkillservService;

@RestController
@RequestMapping("/api/skillServ")
public class SkillservController {
	
	@Autowired
	private SkillservService skillservService;
	
	/*
	 * 发布订单
	 */
	@GetMapping("/skillServIssue")
	@ResponseBody
	public Map generatkillServ(Skillserv skillserv) {
		//测试
//		skillserv.setUid("0417180122");
//		skillserv.setContent("测试内容");
//		skillserv.setRemark("测试信息");
//		skillserv.setPrice(99);
//		skillserv.setServType(1);
//		skillserv.setContact("123");
//		skillserv.setQQ("389783961");
		
		
		String bidRes=generateBidCode();
		skillserv.setBid(bidRes);
		
		Orders orders=new Orders();
		orders.setBid(bidRes);
		orders.setStartTime(getCurrentTime());
		Map map=new HashMap();
		int indexSkill=skillservService.insSkillserv(skillserv);
		int indexOrder=skillservService.insStatus(orders);
		if (indexSkill>0 && indexOrder>0) {
			map.put("status","200");
			return map;
		}else {
			map.put("status","100");
			return map;
		}
	}
	
	/*
	 * 技能服务列表显示 前端不传值
	 */
	@ResponseBody
	@GetMapping("/showSkillServ")
	public List<Skillserv> showSkillservsList(){
		List<Skillserv> list=skillservService.shwoSkillservsList();
		return list;
	}
	
	/**
	 * 显示技能服务详情页面
	 * @param skillserv 前端传回bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showDetailSkillServ")
    public Map showDetailSkillServ(Skillserv skillserv){
		//测试
		//skillserv.setBid("32313425272552840325");
		Map map=new HashMap();
		Skillserv resSkillserv=skillservService.showSkillserv(skillserv);
		
		Users users=skillservService.selNickNameAva(resSkillserv.getUid());
		
		Orders orders=skillservService.selStartTimeStatus(skillserv);
		
		map.put("nickName", users.getNickName());
		map.put("avatarUrl", users.getAvatarUrl());
		map.put("openId", users.getOpenId());
		map.put("startTime", orders.getStartTime());
		map.put("fettle", orders.getFettle());
		map.put("receiver",orders.getReceiver());
		map.put("uid", resSkillserv.getUid());
		map.put("content", resSkillserv.getContent());
		map.put("remark", resSkillserv.getRemark());
		map.put("price", resSkillserv.getPrice());
		map.put("servType", resSkillserv.getServType());
		map.put("contact", resSkillserv.getContact());
		map.put("QQ", resSkillserv.getQQ());
		map.put("bid", resSkillserv.getBid());
		return map;


		
	}
}
