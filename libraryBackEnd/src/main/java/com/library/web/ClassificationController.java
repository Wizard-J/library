package com.library.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.entity.Book;
import com.library.entity.Classification;
import com.library.entity.User;
import com.library.service.BookService;
import com.library.service.ClassificationService;

@Controller
@RequestMapping("/api")
public class ClassificationController {
	@Autowired
    private BookService bookService;
	@Autowired
	private ClassificationService classificationService;

    /**
     * 新增分类
     * @param request
     * @param clf
     * @return
     */
    @RequestMapping(value="/createclassfication", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> createClassification(HttpServletRequest request, Classification clf){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	boolean succ = classificationService.createClassification(clf);
    	if(succ){
    		res.put("stateCode", "1");
            res.put("msg","添加成功！");
    	}else{
    		res.put("stateCode", "0");
    		res.put("msg","系统繁忙，请稍后重试！");
    	}
    	return res;
    }
    
    /**
     * 删除分类
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="/deleteclassfication", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, String> deleteClassification(HttpServletRequest request, String id){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	boolean succ = classificationService.deleteClassification(id) != 0;
    	if(succ){
    		res.put("stateCode", "1");
            res.put("msg","删除成功！");
    	}else{
    		res.put("stateCode", "0");
    		res.put("msg","系统繁忙，请稍后重试！");
    	}
    	return res;
    }
    
    /**
     * 更新分类条目
     * @param request
     * @param clf
     * @return
     */
    @RequestMapping(value="/updateclassificationlist", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> updateAllClassifications(HttpServletRequest request, Classification clf){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	boolean succ = classificationService.updateClassification(clf);
    	if(succ){
    		res.put("stateCode", "1");
            res.put("msg","更新成功！");
    	}else{
    		res.put("stateCode", "0");
    		res.put("msg","系统繁忙，请稍后重试！");
    	}
    	
    	return res;
    }
    
	/**
	 * 获取全图图书分类列表
	 * @return
	 */
    @RequestMapping(value="/get/classificationlist", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Classification> getAllClassifications(HttpServletRequest request){
    	ArrayList<Classification> classificationList=classificationService.getClassificationList();
    	return classificationList;
    }
    
    /**
     * 根据分类ID获取图书列表
     * @param request
     * @param classId
     * @return
     */
    @RequestMapping(value="/get/booklistbyclassification", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Book> getBooklistByClassification(HttpServletRequest request, String classId){
    	if(classId == null) return bookService.getBookList();
    	ArrayList<Book> classificationList=bookService.getBookListByClassId(classId);
    	return classificationList;
    }
}