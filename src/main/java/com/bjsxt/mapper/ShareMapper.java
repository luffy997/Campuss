package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Users;


public interface ShareMapper {
	//发布拼车通知
	@Insert("insert into share VALUES(#{uid},#{mapStart},#{mapEnd},#{peopleNum},#{QQ},#{remark},#{bid},#{getUpTime})")
	int insShare(Share share);
	
	//订单发布 生成状态码 插入order表个信息
	@Insert("insert into orders(bid,fettle,startTime) values(#{bid}, '0', #{startTime})")
	int insStatus(Orders orders);
	 
	//显示拼车订单列表
	@Select("select mapStart,mapEnd,peopleNum,remark,orders.bid,fettle,getUpTime from share,orders where share.bid=orders.bid ")
	List<Share> showSharesList();
	
	//显示详细订单
	@Select("select * from share where bid=#{bid}")
	Share showShare(Share share);

	//根据uid 查出nickName，avatarUrl  
	@Select("SELECT nickName,avatarUrl,openId from users where uid=#{uid}")
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	@Select("SELECT startTime,fettle,receiver from orders WHERE bid= #{bid}")
	Orders selStartTimeStatus(Share share);
}
