package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.LostMapper;
import com.bjsxt.pojo.Lost;
import com.bjsxt.service.LostService;

@Service
@Transactional
public class LostServiceImpl implements LostService {
	
	@Autowired
	private LostMapper lostMapper;

	@Override
	public int insLostMes(Lost lost) {
		// TODO Auto-generated method stub
		return lostMapper.insLostMes(lost);
	}

	@Override
	public List<Lost> selAllLost() {
		// TODO Auto-generated method stub
		return lostMapper.selAllLost();
	}
	
}
