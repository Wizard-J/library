package com.library.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.entity.User;
import com.library.service.UserService;

//标注为一个Spring mvc的Controller
@Controller
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> loginCheck(HttpServletRequest request,String id, String account, String passwd) {

		HashMap<String, String> res = new HashMap<String, String>();// 预定义结果

		if (( id==null && account == null) || passwd == null ) {
			res.put("stateCode", "0");
			res.put("msg", "请检查参数id或passwd是否有误！");
			return res;
		}
		
		User targetUser = id == null ? userService.selectUserByAccount(account) : userService.selectUserById(id);

		try {
			if ( !targetUser.getPasswd().equals(passwd)) { throw new RuntimeException("用户名或密码错误"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
			res.put("msg", "账号或密码错误！");
			return res;
		}
		
		
		if (targetUser.getIsAdmin() == 1) {
			res.put("stateCode", "1");
			res.put("msg", "管理员登陆成功！");
		} else {
			res.put("stateCode", "2");
			res.put("msg", "读者登陆成功！");
		}
		request.getSession().setAttribute("user", targetUser);
		return res;
	};

	@RequestMapping(value="/logout",method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> logout(HttpServletRequest request) {
		request.getSession().invalidate(); // 清除session
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("stateCode", "1");
		res.put("msg", "已登出！");
		return res;
	}



	/**
	 * 配置404页面
	 */
	@RequestMapping(value = { "*", "*/*", "*/*/*" })
	@ResponseBody
	public HashMap<String, String> notFound() {
		HashMap<String, String> res = new HashMap<>(); // 预定义结果
		res.put("stateCode", "0");
		res.put("msg", "年轻人，你的接口可能写错了哟~");
		return res;
	}

}