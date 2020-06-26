package com.bjsxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.pojo.Schoolinfo;
import com.bjsxt.service.SchoolinfoService;

@RestController
@RequestMapping("/api/schoolInfo")
public class SchoolinfoController {

	@Autowired
	private SchoolinfoService schoolinfoService;

	@GetMapping("/showAll")
	@ResponseBody
	public List<Schoolinfo> showAll() {
		List<Schoolinfo> schoolinfo = schoolinfoService.selAllSchoolinfo();
		return schoolinfo;

	}

}
