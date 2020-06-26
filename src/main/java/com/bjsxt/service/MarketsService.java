package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;

public interface MarketsService {
	//发布到订单
	int insOrder(Markets markets);
	
	//生成状态码，插入状态码
	int insStatus(Orders orders);
	
	//显示订单列表
	List<Markets> showOrdersList();
	
	//显示订单详情
	Markets showMarkets(Markets markets);
	
	//根据uid 查出nickName，avatarUrl  
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间和状态
	Orders selStartTimeStatus(Markets markets);
}
