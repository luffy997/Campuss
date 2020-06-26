package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;

public interface ErrandMapper {
	//发布跑腿项目
	@Insert("insert into errand VALUES(#{uid},#{errandType},#{remark},#{sendLocation},#{price},#{contact},#{QQ},#{bid},#{weight})")
	int insErrand(Errand errand);
	
	//订单发布 生成状态码 插入order表个信息
	@Insert("insert into orders(bid,fettle,startTime) values(#{bid}, '0', #{startTime})")
	int insStatus(Orders orders);
	
	//显示跑腿订单列表
	@Select("select errandType,remark,sendLocation,price,weight,orders.bid,fettle from errand,orders where errand.bid=orders.bid")
	List<Errand> showErrandList();
	
	//显示详细订单
	@Select("select * from errand where bid=#{bid}")
	Errand showErrand(Errand errand);

	//根据uid 查出nickName，avatarUrl  
	@Select("SELECT nickName,avatarUrl,openId from users where uid=#{uid}")
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	@Select("SELECT startTime,fettle,receiver from orders WHERE bid= #{bid}")
	Orders selStartTimeStatus(Errand errand);
}
