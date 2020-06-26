package com.bjsxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Students;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public int insUsers(Users users) {
		return usersMapper.insUsers(users);
	}

	@Override
	public Users selUser(Users users) {
		// TODO Auto-generated method stub
		return usersMapper.selUser(users);
	}

	@Override
	public Students selStudents(Students students) {
		// TODO Auto-generated method stub
		return usersMapper.selStudents(students);
	}
	
	
}
