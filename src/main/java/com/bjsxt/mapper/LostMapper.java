package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Lost;

public interface LostMapper {
	//失物招领 寻物启事信息发布
	@Insert("insert into lost values(DEFAULT,#{uid}, #{releaseType}, #{releaseTime}, #{content}, #{images})")
	int insLostMes(Lost lost);
	
	//显示所有失物招领 寻物启事信息
	@Select("select users.uid,nickName,avatarUrl,id,releaseType,releaseTime,content,images from lost LEFT join users on lost.uid=users.uid")
	List<Lost> selAllLost();
}
