package com.bjsxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bjsxt.pojo.Schoolinfo;

public interface SchoolinfoMapper {
	
	//查询全部
	@Select("select * from schoolinfo")
	List<Schoolinfo> selAllSchoolinfo();
}
