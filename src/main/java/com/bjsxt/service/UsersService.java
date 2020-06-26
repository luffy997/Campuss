package com.bjsxt.service;



import com.bjsxt.pojo.Students;
import com.bjsxt.pojo.Users;

public interface UsersService {
	

	/*
	 * 用户注册
	 */
	int insUsers(Users users);
	
	/*
	 * 用户登陆
	 */
	 Users selUser(Users users);
	 
		/*
		 * 校园认证
		 */
		Students selStudents(Students students);
} 
