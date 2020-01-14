package com.library.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.entity.LoanLogs;
import com.library.entity.User;
import com.library.service.LoanLogsService;

@Controller
@RequestMapping("/api")
public class LoanLogsController {

	@Autowired
	private LoanLogsService loanLogsService;

	@RequestMapping(value = "/borrowbook", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> borrowBook(HttpServletRequest request, String bookId){
		HashMap<String, String> res = new HashMap<>();
		
		User user;
		try {
			user = (User) request.getSession().getAttribute("user");
			if(user == null){ throw new RuntimeException("对方没有登录！"); }
			
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你没有登录哟！");
			return res;
		}
		
		boolean succ = loanLogsService.borrowBook(bookId, user.getId());
		if(succ){
			res.put("stateCode", "1");
			res.put("msg","Done！");
			return res;
		}else{
			res.put("stateCode", "0");
			res.put("msg","该图书当前不在馆！");
			return res;
		}
		
	}
	
	@RequestMapping(value = "/returnbook", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> returnBook(HttpServletRequest request, String bookId){
		HashMap<String, String> res = new HashMap<>();
		
		boolean succ = loanLogsService.returnBook(bookId);
		if(succ){
			res.put("stateCode", "1");
			res.put("msg","Done！");
			return res;
		}else{
			res.put("stateCode", "0");
			res.put("msg","信息有误，此图书已经在馆！");
			return res;
		}
		
	}

	@RequestMapping(value = "/query/logs", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> queryLogs(HttpServletRequest request){
		HashMap<String, Object> res = new HashMap<>();
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user == null || user.getIsAdmin()==0){ throw new RuntimeException("对方没有登录或者不是管理员！"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
			res.put("msg","你没有登录哟~或者说你不是管理员哟~");
			return res;
		}

		ArrayList<LoanLogs> logs = loanLogsService.getLogs();
		
		res.put("stateCode", "1");
		res.put("msg","一大波图书日志来了~！");
		res.put("data",logs);
		return res;
		
	}
	
	@RequestMapping(value = "/query/personallogs", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> queryPersonalLogs(HttpServletRequest request){
		HashMap<String, Object> res = new HashMap<>();
		User user;
		try {
			user = (User) request.getSession().getAttribute("user");
			if(user == null ){ throw new RuntimeException("对方没有登录或者不是管理员！"); }
		} catch (Exception e) {
			res.put("stateCode", "0");
			res.put("msg","你没有登录哟~");
			return res;
		}
		
		ArrayList<LoanLogs> logs = loanLogsService.getPersonalLogs(user.getId());
		res.put("stateCode", "1");
		res.put("msg","一大波图书日志来了~！");
		res.put("data",logs);
		return res;
		
	}

}
