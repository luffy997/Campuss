package com.bjsxt.controller;

import static com.bjsxt.utils.Base64ToMultipartUtil.base64ToMultipart;
import static com.bjsxt.utils.DateUtils.getCurrentTime;
import static com.bjsxt.utils.OrderBidGenerateUtil.generateBidCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bjsxt.pojo.Markets;
import com.bjsxt.pojo.Orders;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.MarketsService;

import sun.misc.BASE64Encoder;

@RestController
@RequestMapping("/api/orders")
public class MarketsController {
	@Autowired
	private MarketsService markesService;

	// 获取配置文件的路径
	@Value("${image.location.path}")
	private String resourceDir;

	/**
	 * 实现文件上传
	 */
	@RequestMapping(value = "/index")
	public ModelAndView toIndex() {
		ModelAndView mv = new ModelAndView("uploadimg");
		return mv;
	}

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
			return "/image/" + fileName;
		}
	}

	/**
	 * 接收前端的base64编码数组，解码，保存，并把图片名称，订单信息插入market表
	 * 
	 * @param base    前端的base64编码数组
	 * @param markets 订单信息
	 * @return
	 */
	@ResponseBody
	@PostMapping("/marketIssue")
	public Map generatOrders(String base, Markets markets) {
		JSONArray obj = JSON.parseArray(base);
		System.out.println("base.size()(前端传回的)===========" + JSON.parseArray(base).size());
		StringBuilder builder = new StringBuilder();
		Map map = new HashMap();
		if (obj.size() > 0&&obj!=null) {
			for (int i = 0; i < obj.size(); i++) {
				System.out.println("string===" + obj.get(i));
				MultipartFile multipartFile = base64ToMultipart(obj.get(i).toString());
				String str = fileUpload(multipartFile);
				builder.append(str).append(",");
				String all = builder.substring(0, builder.length() - 1);
				markets.setImages(all);
				System.out.println(markets);
			}
		}else{
			markets.setImages(null);
		}
		String bidRes = generateBidCode();
		markets.setBid(bidRes); // 加入20位订单号
		Orders orders = new Orders();
		orders.setBid(bidRes);
		orders.setStartTime(getCurrentTime());
		int indexMarkets = markesService.insOrder(markets);
		int indexOrders = markesService.insStatus(orders);
		if (indexMarkets > 0 && indexOrders > 0) {
			map.put("statusCode", "200");
			return map;
		} else {
			map.put("statusCode", "100");
			return map;
		}

	}

	/**
	 * 显示所有订单，前端无需传值
	 * 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showMarket")
	public List<Markets> showOrders() {
		List<Markets> list = markesService.showOrdersList();
		return list;
	}

	/**
	 * 显示订单详情
	 * @param bid 前端传回订单号
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showDetailMarket")
    public Map login(Markets markets){
		//根据前端传回的bid，查出images
		//测试
		//markets.setBid("40005468583144690599");
		Map map=new HashMap();
		Markets resMarkets = markesService.showMarkets(markets);
		String imageString= resMarkets.getImages();
		if (imageString!=null){
			String imageStr=imageString+",";   //一定要
			byte[] data = null;
			// 读取图片字节数组
			String substring = imageStr.substring(0, imageStr.length() - 1);
			//以逗号分割，得出的数据存到 result 里面
			String[] result = substring.split(",");
			// 对字节数组Base64编码
			//BASE64Encoder encoder = new BASE64Encoder();
			String[] image=new String[result.length];//返回的结果
			for (int i = 0; i < result.length; i++) {
				//try {
				//	InputStream in = new FileInputStream("C:/"+result[i]);
				//	data = new byte[in.available()];
				//	in.read(data);
				//	in.close();
				//} catch (IOException e) {
				//	e.printStackTrace();
				//}
				//String temp="data:image/png;base64,"+ encoder.encode(Objects.requireNonNull(data));
				String temp="https://www.booksms.top"+result[i];
				image[i]=temp;
			}
			// 返回Base64编码过的字节数组字符串

			map.put("image",image);
			System.out.println("map====="+map);
		}else{
			map.put("image",null);
		}

		//查询用户昵称和头像地址
		Users users=markesService.selNickNameAva(resMarkets.getUid());
		System.out.println("users========"+users);
		
		Orders orders=new Orders();
		orders =markesService.selStartTimeStatus(markets);
		
		map.put("nickName", users.getNickName());
		map.put("avatarUrl", users.getAvatarUrl());
		map.put("openId", users.getOpenId());

		map.put("startTime", orders.getStartTime());
		map.put("fettle", orders.getFettle());
		map.put("receiver",orders.getReceiver());
		map.put("uid", resMarkets.getUid());
		map.put("name", resMarkets.getName());
		map.put("describes", resMarkets.getDescribes());
		map.put("nowPrice", resMarkets.getNowPrice());
		map.put("beforePrice", resMarkets.getBeforePrice());
		map.put("contact", resMarkets.getContact());
		map.put("goodsType", resMarkets.getGoodsType());
		map.put("orderType",resMarkets.getOrderType());
		map.put("bid", resMarkets.getBid());
		
		
        return map;
    }
}