package com.bjsxt.controller;

import static com.bjsxt.utils.UidGenerateUtil.generateUidCode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bjsxt.pojo.Students;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
		
	/**
	 * 测试
	 */
	@ResponseBody
	@GetMapping("/test")
	public Map Test() {
		Map map =new HashMap();
		map.put("status", "200");
		return map;
	}
	
	/**
	 * 
	 * @param users 用户注册
	 * @param students 校园认证  前端传回sno sname
	 * @return
	 */
	@ResponseBody
	@GetMapping("/regist")
	public Map regist(Users users,Students students) {
		//测试
		users.setUid(generateUidCode()); //随机生成10位的uid
		System.out.println("Uid======"+users.getUid());
//		users.setNickName("Alen");
//		users.setGender(1);
//		users.setAvatarUrl("/avater/1.jap");
//		users.setEmail("389783961@qq.com");
//		users.setOpenid("23442342342");
		//校园认证
		//students.setSname("牟伦利");
		//students.setSno("1807270218");
		Map map = new HashMap();
		students.setSno(users.getSno());
		Students student=usersService.selStudents(students);
		if (student !=null) {
			int index= usersService.insUsers(users);  //先插入数据
			Users user = usersService.selUser(users);  //再查询数据
			if (index>0) {
				map.put("status", "1");
				map.put("uid", user.getUid());
				return map;
			}else {
				map.put("status", "0");
				return map;
			}
		}else {
			map.put("status", "0");
			map.put("msg", "认证失败");
			return map;
		}
		
	
	}
}
