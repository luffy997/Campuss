package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.SkillservMapper;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Skillserv;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.SkillservService;
@Service
@Transactional
public class SkillservServiceImpl implements SkillservService {

	@Autowired
	private SkillservMapper skillservMapper;

	@Override
	public int insSkillserv(Skillserv skillserv) {
		// TODO Auto-generated method stub
		return skillservMapper.insSkillserv(skillserv);
	}

	@Override
	public int insStatus(Orders orders) {
		// TODO Auto-generated method stub
		return skillservMapper.insStatus(orders);
	}

	@Override
	public List<Skillserv> shwoSkillservsList() {
		// TODO Auto-generated method stub
		return skillservMapper.shwoSkillservsList();
	}

	@Override
	public Skillserv showSkillserv(Skillserv skillserv) {
		// TODO Auto-generated method stub
		return skillservMapper.showSkillserv(skillserv);
	}

	@Override
	public Users selNickNameAva(String uid) {
		// TODO Auto-generated method stub
		return skillservMapper.selNickNameAva(uid);
	}

	@Override
	public Orders selStartTimeStatus(Skillserv skillserv) {
		// TODO Auto-generated method stub
		return skillservMapper.selStartTimeStatus(skillserv);
	}
	
	
}
