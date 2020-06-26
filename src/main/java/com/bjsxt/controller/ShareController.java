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
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.ShareService;

@RestController
@RequestMapping("/api/share")
public class ShareController {

	@Autowired
	private ShareService shareService;
	
	/*
	 * 发布订单
	 */
	@GetMapping("/shareIssue")
	@ResponseBody
	public Map generatSkillServ(Share share) {
		//测试
//		share.setUid("102");
//		share.setMapStart("一食堂");
//		share.setMapEnd("谭鑫培");
//		share.setPeopleNum(10);
//		share.setQQ("3110236940");
//		share.setRemark("带女生");
//		share.setGetUpTime("2020/05/13");
	
		
		
		String bidRes=generateBidCode();
		share.setBid(bidRes);
		
		Orders orders=new Orders();
		orders.setBid(bidRes);
		orders.setStartTime(getCurrentTime());
		Map map=new HashMap();
		int indexShare=shareService.insShare(share);
		int indexOrder=shareService.insStatus(orders);
		if (indexShare>0 && indexOrder>0) {
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
	@GetMapping("/showShare")
	public List<Share> showShareList(){
		List<Share> list=shareService.showSharesList();
		return list;
	}
	
	/**
	 * 显示订单详细列表
	 * @param errand 前端传回bid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showDetailShare")
    public Map showDetailShare(Share share){
		//测试
		//share.setBid("11309422268031759823");
		Map map=new HashMap();
		
		Share resShare=shareService.showShare(share);
		
		Users users=shareService.selNickNameAva(resShare.getUid());
		
		Orders orders=shareService.selStartTimeStatus(share);
		
		map.put("nickName", users.getNickName());
		map.put("avatarUrl", users.getAvatarUrl());
		map.put("openId", users.getOpenId());
		map.put("startTime", orders.getStartTime());
		map.put("fettle", orders.getFettle());
		map.put("receiver",orders.getReceiver());
		map.put("uid", resShare.getUid());
		map.put("mapStart", resShare.getMapStart());
		map.put("mapEnd", resShare.getMapEnd());
		map.put("peopleNum", resShare.getPeopleNum());
		map.put("QQ", resShare.getQQ());
		map.put("remark", resShare.getRemark());
		map.put("bid", resShare.getBid());
		map.put("getUpTime", resShare.getGetUpTime());
		return map;

		
	}
}
