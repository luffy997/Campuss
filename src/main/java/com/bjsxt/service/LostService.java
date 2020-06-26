package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Lost;

public interface LostService {
	//失物招领 寻物启事信息发布
	int insLostMes(Lost lost);
	
	//显示所有失物招领 寻物启事信息
	List<Lost> selAllLost();
}
