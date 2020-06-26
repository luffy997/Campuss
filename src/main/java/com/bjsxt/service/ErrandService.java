package com.bjsxt.service;



import java.util.List;

import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;

public interface ErrandService {
	//发布跑腿项目
	int insErrand(Errand errand);
	
	//订单发布 生成状态码 插入order表个信息
	int insStatus(Orders orders);
	
	//显示跑腿订单列表
	List<Errand> showErrandList();
	
	//显示详细订单
	Errand showErrand(Errand errand);

	//根据uid 查出nickName，avatarUrl  
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	Orders selStartTimeStatus(Errand errand);
}
