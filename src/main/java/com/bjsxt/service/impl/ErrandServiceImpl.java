package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.ErrandMapper;
import com.bjsxt.pojo.Errand;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.ErrandService;
@Service
@Transactional
public class ErrandServiceImpl implements ErrandService {
	
	@Autowired
	private ErrandMapper errandMapper;

	@Override
	public int insErrand(Errand errand) {
		// TODO Auto-generated method stub
		return errandMapper.insErrand(errand);
	}

	@Override
	public int insStatus(Orders orders) {
		// TODO Auto-generated method stub
		return errandMapper.insStatus(orders);
	}

	@Override
	public List<Errand> showErrandList() {
		// TODO Auto-generated method stub
		return errandMapper.showErrandList();
	}

	@Override
	public Errand showErrand(Errand errand) {
		// TODO Auto-generated method stub
		return errandMapper.showErrand(errand);
	}

	@Override
	public Users selNickNameAva(String uid) {
		// TODO Auto-generated method stub
		return errandMapper.selNickNameAva(uid);
	}

	@Override
	public Orders selStartTimeStatus(Errand errand) {
		// TODO Auto-generated method stub
		return errandMapper.selStartTimeStatus(errand);
	};
	
}
