package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;

public interface OrdersMapper {
	// 接单功能的实现
	@Insert("update orders set fettle='1',receiver= #{receiver},receiveTime= #{receiveTime} where bid= #{bid}")
	int insOrders(Orders orders);
	
	//根据bid 查询openId(发推送信息)
	@Select("select users.uid,users.openId,price from errand left join users on errand.uid=users.uid where bid= #{bid}")
	Errand selOpenId_one(String bid);
	
	@Select("select users.uid,users.openId,nowPrice from market left join users on market.uid=users.uid where bid= #{bid}")
	Markets selOpenId_two(String bid);
	
	@Select("select users.uid,users.openId,price from skillserv left join users on skillserv.uid=users.uid where bid= #{bid}")
	Skillserv selOpenId_three(String bid);
	
	@Select("select users.uid,users.openId from share left join users on share.uid=users.uid where bid= #{bid}")
	Share selOpenId_four(String bid);
	
	//接单成功返回接单时间
	@Select("select receiveTime from orders where bid= #{bid}")
	Orders selReceiveTime(String bid);
	
	// 拼车相关的 根据bid查询peopleNum
	@Select("select peopleNum from share where bid =#{bid}")
	Share selPeopleNum(Orders orders);

	// 查询receiver
	@Select("select receiver from orders where bid= #{bid} ")
	Orders selOrders(Orders orders);

	// 更新receiver
	@Update("update orders set receiver= #{receiver} where bid= #{bid}")
	int updReceiver(Orders orders);

	// 查询用户发布的订单(errand)
	@Select("SELECT errand.uid,errand.errandType,errand.sendLocation,errand.price,errand.bid,orders.fettle,orders.finishTime,orders.startTime,orders.receiver,orders.receiveTime FROM errand LEFT join orders on errand.bid=orders.bid where uid= #{uid}")
	List<Orders> selErrand(String uid);

	// 查询用户发布的订单(market)
	@Select("SELECT market.uid,market.name,market.describes,market.images,market.nowPrice,market.beforePrice,market.goodsType,market.orderType,orders.bid,orders.fettle,orders.finishTime,orders.startTime,orders.receiver,orders.receiveTime FROM market LEFT join orders on market.bid=orders.bid where uid= #{uid}")
	List<Orders> selMarket(String uid);

	// 查询用户发布的订单(share)
	@Select("SELECT share.uid,share.mapStart,share.mapEnd,share.peopleNum,share.getUpTime,orders.bid,orders.fettle,orders.finishTime,orders.startTime,orders.receiver,orders.receiveTime FROM share LEFT join orders on share.bid=orders.bid where uid= #{uid}")
	List<Orders> selShare(String uid);

	// 查询用户发布的订单(skillserv)
	@Select("SELECT skillserv.uid,skillserv.content,skillserv.servType,orders.bid,orders.fettle,orders.finishTime,orders.startTime,orders.receiver,orders.receiveTime FROM skillserv LEFT join orders on skillserv.bid=orders.bid  where uid= #{uid}")
	List<Orders> selSkillServ(String uid);

	// 根据uid 查到用户的信息
	@Select("SELECT nickname,gender,avatarUrl,email,openId FROM users WHERE uid=#{uid}")
	Users selUsers(String uid);

	// 用户撤销订单（删除订单） 前端返回bid
	@Delete("delete from errand where bid= #{bid}")
	int delErrand(String bid);

	// 用户撤销订单（删除订单） 前端返回bid
	@Delete("delete from market where bid= #{bid}")
	int delMarket(String bid);

	// 用户撤销订单（删除订单） 前端返回bid
	@Delete("delete from share where bid= #{bid}")
	int delShare(String bid);

	// 用户撤销订单（删除订单） 前端返回bid
	@Delete("delete from skillserv where bid= #{bid}")
	int delSkillServ(String bid);
	
	// 用户撤销订单（删除订单） 前端返回bid
	@Delete("delete from orders where bid= #{bid}")
	int delOrders(String bid);
	
	//用户确认订单完成 前端传回bid uid 判断bid是否匹配  匹配这修改orders里的fettle 加上finishTime
	
	@Select("SELECT uid FROM  share WHERE bid= #{bid} and uid=#{uid} ")
	Users selWeatherMatchShare(Share share);
	@Select("SELECT uid FROM  market WHERE bid= #{bid} and uid=#{uid} ")
	Users selWeatherMatchMarket(Share share);
	@Select("SELECT uid FROM  errand WHERE bid= #{bid} and uid=#{uid} ")
	Users selWeatherMatchErrand(Share share);
	@Select("SELECT uid FROM  skillserv WHERE bid= #{bid} and uid=#{uid} ")
	Users selWeatherMatchSkillServ(Share share);
	
	//若匹配 则修改orders里的fettle 加上finishTime
	@Update("UPDATE orders set fettle='9',finishTime= #{finishTime} WHERE bid=#{bid}")
	int updOrders(Orders orders);
	
	//查询我的接单 前端传回receiver 根据bid 查uid 
	@Select("SELECT errand.uid,orders.bid,fettle,orders.startTime,orders.finishTime,receiveTime,errand.errandType,errand.sendLocation,users.nickName,users.gender,users.avatarUrl,users.email from errand LEFT JOIN orders on errand.bid=orders.bid LEFT JOIN users on errand.uid=users.uid  WHERE orders.receiver=#{receiver}")
	List<Orders> selMyReceiveErand(String receiver);
	@Select("SELECT market.uid,orders.bid,fettle,orders.startTime,orders.finishTime,receiveTime,market.name,market.describes,market.images,market.nowPrice,market.beforePrice,market.goodsType,market.orderType,users.nickName,users.gender,users.avatarUrl,users.email from market LEFT JOIN orders on market.bid=orders.bid LEFT JOIN users on market.uid=users.uid  WHERE orders.receiver=#{receiver}")
	List<Orders> selMyReceiveMarket(String receiver);
	@Select("SELECT share.uid,orders.bid,fettle,orders.startTime,orders.finishTime,receiveTime,share.mapStart,share.mapEnd,share.peopleNum,share.getUpTime,users.nickName,users.gender,users.avatarUrl,users.email from share LEFT JOIN orders on share.bid=orders.bid LEFT JOIN users on share.uid=users.uid  WHERE orders.receiver like #{receiverStr}")
	List<Orders> selMyReceiveShare(String receiverStr);
	@Select("SELECT skillserv.uid,orders.bid,fettle,orders.startTime,orders.finishTime,receiveTime,skillserv.content,skillserv.servType,users.nickName,users.gender,users.avatarUrl,users.email from skillserv LEFT JOIN orders on skillserv.bid=orders.bid LEFT JOIN users on skillserv.uid=users.uid  WHERE orders.receiver=#{receiver}")
	List<Orders> selMyReceiveSkillserv(String receiver);
	//根据uid 查出用户信息  复用上面selUsers(String uid);
	
	
}
