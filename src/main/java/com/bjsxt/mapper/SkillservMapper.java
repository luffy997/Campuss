package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;

public interface SkillservMapper {

	//发布技能服务
	@Insert("insert into skillserv VALUES(#{uid},#{content},#{remark},#{price},#{servType},#{contact},#{QQ},#{bid})")
	int insSkillserv(Skillserv skillserv);
	
	//订单发布 生成状态码 插入order表个信息
	@Insert("insert into orders(bid,fettle,startTime) values(#{bid}, '0', #{startTime})")
	int insStatus(Orders orders);
	
	//列表显示
	@Select("select content,remark,price,servType,orders.bid,fettle from skillserv,orders where skillserv.bid=orders.bid")
	List<Skillserv> shwoSkillservsList();
	
	//显示详细订单
	@Select("select * from skillserv where bid=#{bid}")
	Skillserv showSkillserv(Skillserv skillserv);

	//根据uid 查出nickName，avatarUrl  
	@Select("SELECT nickName,avatarUrl,openId from users where uid=#{uid}")
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	@Select("SELECT startTime,fettle,receiver from orders WHERE bid= #{bid}")
	Orders selStartTimeStatus(Skillserv skillserv);
}
