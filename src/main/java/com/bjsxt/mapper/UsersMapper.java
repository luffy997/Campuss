package com.bjsxt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Students;
import com.bjsxt.pojo.Users;

public interface UsersMapper {
		
	//用户注册
	@Insert("INSERT INTO users VALUES (#{uid}, #{nickName},#{gender}, #{avatarUrl}, #{email}, #{openId}, #{sno})")
	int insUsers(Users users);
	
	//用户登陆
	//前端返回openId，根据openId去查是否存在uid，存在则注册过
	@Select("SELECT * FROM users where openId = #{openId}")
	 Users selUser(Users users);	
	
	//校园认证
	@Select("SELECT id,sno,sname FROM students  where sno=#{sno} and sname=#{sname}")
	Students selStudents(Students students);
}
