package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.OrdersMapper;
import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Share;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.OrdersService;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired 
	OrdersMapper OrdersMapper;
	
	@Override
	public int insOrders(Orders orders) {
		// TODO Auto-generated method stub
		return OrdersMapper.insOrders(orders);
	}

	@Override
	public Share selPeopleNum(Orders orders) {
		// TODO Auto-generated method stub
		return OrdersMapper.selPeopleNum(orders);
	}

	@Override
	public Orders selOrders(Orders orders) {
		// TODO Auto-generated method stub
		return OrdersMapper.selOrders(orders);
	}

	@Override
	public int updReceiver(Orders orders) {
		// TODO Auto-generated method stub
		return OrdersMapper.updReceiver(orders);
	}



	@Override
	public Users selUsers(String uid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selUsers(uid);
	}

	@Override
	public List<Orders> selErrand(String uid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selErrand(uid);
	}

	@Override
	public List<Orders> selMarket(String uid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selMarket(uid);
	}

	@Override
	public List<Orders> selShare(String uid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selShare(uid);
	}

	@Override
	public List<Orders> selSkillServ(String uid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selSkillServ(uid);
	}

	@Override
	public int delErrand(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.delErrand(bid);
	}

	@Override
	public int delMarket(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.delMarket(bid);
	}

	@Override
	public int delShare(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.delShare(bid);
	}

	@Override
	public int delSkillServ(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.delOrders(bid);
	}

	@Override
	public int delOrders(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.delOrders(bid);
	}

	@Override
	public Users selWeatherMatchShare(Share share) {
		// TODO Auto-generated method stub
		return OrdersMapper.selWeatherMatchShare(share);
	}

	@Override
	public Users selWeatherMatchMarket(Share share) {
		// TODO Auto-generated method stub
		return OrdersMapper.selWeatherMatchMarket(share);
	}

	@Override
	public Users selWeatherMatchErrand(Share share) {
		// TODO Auto-generated method stub
		return OrdersMapper.selWeatherMatchErrand(share);
	}

	@Override
	public Users selWeatherMatchSkillServ(Share share) {
		// TODO Auto-generated method stub
		return OrdersMapper.selWeatherMatchSkillServ(share);
	}

	@Override
	public int updOrders(Orders orders) {
		// TODO Auto-generated method stub
		return OrdersMapper.updOrders(orders);
	}

	@Override
	public List<Orders> selMyReceiveErand(String receiver) {
		// TODO Auto-generated method stub
		return OrdersMapper.selMyReceiveErand(receiver);
	}

	@Override
	public List<Orders> selMyReceiveMarket(String receiver) {
		// TODO Auto-generated method stub
		return OrdersMapper.selMyReceiveMarket(receiver);
	}

	@Override
	public List<Orders> selMyReceiveShare(String receiver) {
		// TODO Auto-generated method stub
		return OrdersMapper.selMyReceiveShare(receiver);
	}

	@Override
	public List<Orders> selMyReceiveSkillserv(String receiverStr) {
		// TODO Auto-generated method stub
		return OrdersMapper.selMyReceiveSkillserv(receiverStr);
	}

	@Override
	public Orders selReceiveTime(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selReceiveTime(bid);
	}

	@Override
	public Errand selOpenId_one(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selOpenId_one(bid);
	}

	@Override
	public Markets selOpenId_two(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selOpenId_two(bid);
	}

	@Override
	public Skillserv selOpenId_three(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selOpenId_three(bid);
	}

	@Override
	public Share selOpenId_four(String bid) {
		// TODO Auto-generated method stub
		return OrdersMapper.selOpenId_four(bid);
	}


	

}
