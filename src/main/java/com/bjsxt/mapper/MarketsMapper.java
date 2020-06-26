package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;

public interface MarketsMapper {
	
 //订单发布
@Insert("insert into market values (#{uid},#{name},#{describes},#{images},#{nowPrice},#{beforePrice},#{contact},#{goodsType},#{orderType},#{bid})")
int insOrder(Markets markets);

//订单发布 生成状态码 插入order表个信息
@Insert("insert into orders(bid,fettle,startTime) values(#{bid}, '0', #{startTime})")
int insStatus(Orders orders);

//显示订单列表
@Select("SELECT name,describes,nowPrice,beforePrice,goodsType,orderType,orders.bid,fettle FROM market,orders where market.bid=orders.bid")
List<Markets> showOrdersList();

//显示详细订单
@Select("select * from market where bid=#{bid}")
Markets showMarkets(Markets markets);

//根据uid 查出nickName，avatarUrl  
@Select("SELECT nickName,avatarUrl,openId from users where uid=#{uid}")
Users selNickNameAva(String uid);

//根据bid 查出订单发布时间 状态
@Select("SELECT startTime,fettle,receiver from orders WHERE bid= #{bid}")
Orders selStartTimeStatus(Markets markets);
}
