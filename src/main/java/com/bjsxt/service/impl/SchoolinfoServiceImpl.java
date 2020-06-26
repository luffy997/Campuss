package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.SchoolinfoMapper;
import com.bjsxt.pojo.Schoolinfo;
import com.bjsxt.service.SchoolinfoService;

@Service
@Transactional
public class SchoolinfoServiceImpl implements SchoolinfoService {
	
	@Autowired
	private SchoolinfoMapper schoolinfoMapper;

	@Override
	public List<Schoolinfo> selAllSchoolinfo() {
		// TODO Auto-generated method stub
		return schoolinfoMapper.selAllSchoolinfo();
	}

	

	
}
