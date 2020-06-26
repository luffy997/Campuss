package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;

public interface SkillservService {
	
	//发布仅能服务
	int insSkillserv(Skillserv skillserv);
	
	//订单发布 生成状态码 插入order表个信息
	int insStatus(Orders orders);
	
	//列表显示
	List<Skillserv> shwoSkillservsList();
	
	//显示详细订单
	Skillserv showSkillserv(Skillserv skillserv);

	//根据uid 查出nickName，avatarUrl  
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	Orders selStartTimeStatus(Skillserv skillserv);
}
