package com.bjsxt.controller;

import static com.bjsxt.utils.Base64ToMultipartUtil.base64ToMultipart;
import static com.bjsxt.utils.DateUtils.getCurrentTime;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bjsxt.pojo.Lost;
import com.bjsxt.pojo.Markets;
import com.bjsxt.service.LostService;

@RestController
@RequestMapping("/api/lost")
public class LostController {
	@Autowired
	private LostService lostService;

	// 获取配置文件的路径
	@Value("${lostImage.location.path}")
	private String resourceDir;

	// 单个文件上传
	@PostMapping("/fileUpload")
	@ResponseBody
	public String fileUpload(MultipartFile file) {
		// 获取上传文件路径
		// String uploadPath = file.getOriginalFilename();

		// 获取文件名称
		String oldName = file.getOriginalFilename();

		// 获取上传文件的后缀
		// 使用字符串截取函数 截取最后一个以.结尾的位置到文件名称的长度
		String fileSuffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());

		// 可以根据后缀来判断上传的路径
		/*
		 * if (fileSuffix.equals("apk")) { uploadPath = resourceDir; } else { // 上传目录地址
		 * // String uploadpath="E:/hot-manage/image/";//windows路径 uploadPath =
		 * resourceDir;// linux路径 }
		 */

		// 获取上传文件路径
		String uploadPath = resourceDir;

		// 上传文件名(新名字)
		String fileName = new Date().getTime() + new Random().nextInt(100) + fileSuffix;
		File savefile = new File(uploadPath + fileName);
		if (!savefile.getParentFile().exists()) {
			savefile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(savefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fileSuffix.equals("apk")) {
			return "/apk/" + fileName;
		} else {
			return "/lost/" + fileName;
		}
	}

	@ResponseBody
	@PostMapping("/lostIssue")
	public Map generatLost(String base, Lost lost) {
		JSONArray obj = JSON.parseArray(base);
		System.out.println("base.size()(前端传回的)===========" + JSON.parseArray(base).size());
		StringBuilder builder = new StringBuilder();
		Map map = new HashMap();
		if (obj.size() > 0 && obj != null) {
			for (int i = 0; i < obj.size(); i++) {
				System.out.println("string===" + obj.get(i));
				MultipartFile multipartFile = base64ToMultipart(obj.get(i).toString());
				String str = fileUpload(multipartFile);
				builder.append("https://www.booksms.top").append(str).append(",");
				String all = builder.substring(0, builder.length() - 1);
				lost.setImages(all);
				System.out.println(lost);
			}
		} else {
			lost.setImages(null);
		}

		lost.setReleaseTime(getCurrentTime());
		int index = lostService.insLostMes(lost);
		if (index > 0) {
			map.put("statusCode", "200");
			return map;
		} else {
			map.put("statusCode", "100");
			map.put("msg", "发布失败");
			return map;
		}
	}

	/**
	 * 显示所有lost 前端不传
	 * 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showAllLost")
	public Map showAllLost() {
		List<Lost> lostList = lostService.selAllLost();
		Map map = new HashMap();
		for (int j = 0; j < lostList.size(); j++) {
			Lost resList = lostList.get(j);
			System.out.println("每条resList====" + resList);
			String imageStr = resList.getImages();
			System.out.println("每条images===" + imageStr);
			if (imageStr != null) {
				String[] result = imageStr.split(",");

				resList.setImage(result);
			}
		}
		//删除 字段images
//		for (int i = 0; i < lostList.size(); i++) {
//			if (lostList.get(i).equals("images"))
//				lostList.remove(i);
//		}
		for(int i=lostList.size()-1;i>=0;i--){
			if (lostList.get(i).equals("images"))
				lostList.remove(i);
			}
		map.put("lost", lostList);
		return map;
	}
}
