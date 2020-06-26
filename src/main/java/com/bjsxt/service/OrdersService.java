package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;

public interface OrdersService {
	// 接单功能的实现
	int insOrders(Orders orders);
	
	//根据bid 查询openId(发推送信息)
	Errand selOpenId_one(String bid);
	
	Markets selOpenId_two(String bid);
	
	Skillserv selOpenId_three(String bid);
	
	Share selOpenId_four(String bid);
	
	//接单成功返回接单时间
	Orders selReceiveTime(String bid);

	// 拼车相关的 根据bid查询peopleNum
	Share selPeopleNum(Orders orders);

	// 查询receiver
	Orders selOrders(Orders orders);

	// 更新receiver
	int updReceiver(Orders orders);

	// 用户显示自己的订单 根据uid errand
	List<Orders> selErrand(String uid);

	// 查询用户发布的订单(market)
	List<Orders> selMarket(String uid);

	// 查询用户发布的订单(share)
	List<Orders> selShare(String uid);

	// 查询用户发布的订单(skillserv)
	List<Orders> selSkillServ(String uid);

	// 根据uid 查到用户的信息
	Users selUsers(String uid);
	
	// 用户撤销订单（删除订单） 前端返回bid
	int delErrand(String bid);

	/**
	 * 用户撤销订单（删除订单） 前端返回bid
	 * @param bid
	 * @return
	 */
	int delMarket(String bid);
	int delShare(String bid);
	int delSkillServ(String bid);
	int delOrders(String bid);
	
	//用户确认订单完成 前端传回bid uid 判断bid是否匹配  匹配这修改orders里的fettle 加上finishTime
	
	Users selWeatherMatchShare(Share share);
	Users selWeatherMatchMarket(Share share);
	Users selWeatherMatchErrand(Share share);
	Users selWeatherMatchSkillServ(Share share);
	
	//若匹配 则修改orders里的fettle 加上finishTime
	int updOrders(Orders orders);

	//查询我的接单 前端传回receiver 根据bid 查uid 
	List<Orders> selMyReceiveErand(String receiver);
	List<Orders> selMyReceiveMarket(String receiver);
	List<Orders> selMyReceiveShare(String receiverStr);
	List<Orders> selMyReceiveSkillserv(String receiver);
}
