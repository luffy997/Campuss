package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.MarketsMapper;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.MarketsService;

@Service
@Transactional
public class MarkesServiceImpl implements MarketsService{

	@Autowired 
	MarketsMapper marketsMapper;

	@Override
	public int insOrder(Markets markets) {
		return marketsMapper.insOrder(markets);
	}

	@Override
	public int insStatus(Orders orders) {
		return marketsMapper.insStatus(orders);
	}

	@Override
	public List<Markets> showOrdersList() {
		// TODO Auto-generated method stub
		return marketsMapper.showOrdersList();
	}

	@Override
	public Markets showMarkets(Markets markets) {
		// TODO Auto-generated method stub
		return marketsMapper.showMarkets(markets);
	}

	@Override
	public Users selNickNameAva(String uid) {
		// TODO Auto-generated method stub
		return marketsMapper.selNickNameAva(uid);
	}

	@Override
	public Orders selStartTimeStatus(Markets markets) {
		// TODO Auto-generated method stub
		return marketsMapper.selStartTimeStatus(markets);
	}



	
}
