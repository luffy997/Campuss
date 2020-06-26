package com.bjsxt.service;



import java.util.List;

import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Users;

public interface ShareService {

	//发布拼车通知
	int insShare(Share share);
	
	//订单发布 生成状态码 插入order表个信息
	int insStatus(Orders orders);
	
	//显示拼车订单列表
	List<Share> showSharesList();
	
	//显示详细订单
	Share showShare(Share share);

	//根据uid 查出nickName，avatarUrl  
	Users selNickNameAva(String uid);

	//根据bid 查出订单发布时间 状态
	Orders selStartTimeStatus(Share share);
}
