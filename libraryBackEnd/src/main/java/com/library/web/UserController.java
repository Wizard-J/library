package com.library.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.entity.User;
import com.library.service.UserService;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService userService;
	
	/**
	 * 新用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> createNewUser(HttpServletRequest request, User user){
		HashMap<String,Object> res = new HashMap<>();
		boolean succ = userService.createUser(user)!=0;
		if(succ){
			res.put("stateCode", "1");
            res.put("msg","注册成功啦！");
		}else{
			res.put("stateCode", "0");
			res.put("msg","系统繁忙，请稍后重试！");
		}
		return res;
	} 
	
	/**
	 * 查看用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get/userlist", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getReaderList(HttpServletRequest request){
		HashMap<String,Object> res = new HashMap<>();
		User user;
		try {
			user = ((User) request.getSession().getAttribute("user")); // 只有管理员登陆过，才会在session中保存id,否则不会留下id
			if(user == null || user.getIsAdmin()==0){ throw new RuntimeException("你不是管理员"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你不是管理员！");
			return res;
		}
		
		ArrayList<User> users=userService.getUserList();
		res.put("stateCode", "1");
        res.put("msg","获取成功！");
		res.put("data", users);
		return res;
	}
	
	/**
	 * 获取指定用户信息
	 * @param request
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/get/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getReaderInfo(HttpServletRequest request, String id) {
    	HashMap<String,Object> res = new HashMap<>();
    	
    	
    	User presentUser;
    	try {
			presentUser = (User) request.getSession().getAttribute("user");
			if(presentUser == null){ throw new RuntimeException("你没有登录哟！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你没有登录哟~");
			return res;
		}
    	
    	if(presentUser.getIsAdmin()==0){
    		res.put("stateCode", "1");
    		res.put("msg","不行哟，你不是管理员。");
    		return res;
    	}
		
    	User user = userService.selectUserById(id);
    	if(user.getId()==null){
    		res.put("stateCode", "0");
    		res.put("msg","此id不存在！");
    		return res;
    		
    	}else{
    		res.put("stateCode", "1");
    		res.put("msg","查询到信息！");
    		res.put("data",user);
    		return res;
    	}
    }
	
	
	/**
	 * 获取当前用户信息
	 * @param request
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/get/personalinfo", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getPersonalInfo(HttpServletRequest request) {
    	HashMap<String,Object> res = new HashMap<>();
		
    	User user;
		try {
			user = ((User) request.getSession().getAttribute("user"));
			if(user == null){ throw new RuntimeException("你没有登录哟！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你没有登录哟~");
			return res;
		}
		
        res.put("stateCode", "1");
        res.put("msg","查询到信息！");
        res.put("data",user);
		return res;
    }
	
	/**
	 * 修改密码
	 * @param request
	 * @param oldPasswd
	 * @param newPasswd
	 * @return
	 */
	@RequestMapping(value="/update/passwd", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> modifyPassWD(HttpServletRequest request, String oldPasswd, String newPasswd) {

		HashMap<String, String> res = new HashMap<>(); // 预定义结果

		if (newPasswd == null || oldPasswd == null) {
			res.put("stateCode", "0");
			res.put("msg", "请检查参数newPasswd或oldPasswd是否有误！");
			return res;
		}

		User user;
		try {
			user = (User) request.getSession().getAttribute("user"); // 如果没有登陆过，这个位置就是null
			if(user == null){ throw new RuntimeException("用户未登录"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
			res.put("msg", "不行，你还没有登陆哟！");
			return res;
		}
		String truePassWd = user.getPasswd();

		if (truePassWd.equals(oldPasswd)) { // 用户提供的旧密码和真是密码相同
			user.setPasswd(newPasswd); // 将密码设置为新密码
			boolean succ = userService.updateUser(user) != 0; // 受影响的数据数量为1则成功，为0则修改失败
			if (succ) {
				res.put("stateCode", "1");
				res.put("msg", "密码修改成功！");
				return res;
			} else {
				res.put("stateCode", "0");
				res.put("msg", "密码修改失败！");
				return res;
			}
		} else {
			res.put("stateCode", "0");
			res.put("msg", "旧密码错误！");
			return res;
		}
	};

	/**
	 * 删除用户
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/user", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,String> deleteReader(HttpServletRequest request,String id){
		
		HashMap<String,String> res = new HashMap<>();
		
		User user;
		try {
			user = ((User) request.getSession().getAttribute("user"));
			if(user == null || user.getIsAdmin()==0){ throw new RuntimeException("你不是管理员"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你不是管理员！");
			return res;
		}
		
		boolean success=userService.deleteUserById(id) !=0;
		
		if(success){
			res.put("stateCode", "1");
            res.put("msg","删除成功！");
			return res;
		}else {
			res.put("stateCode", "0");
            res.put("msg","删除失败！用户信息不存在");
			return res;
		}
		
	}
	
}
