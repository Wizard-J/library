package com.library.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.BookService;

@Controller
@RequestMapping("/api")
public class BookController {
	@Autowired
    private BookService bookService;

    /***
     * 按照id 和name 查找
     * @param request
     * @param searchWord
     * @return
     */
    @RequestMapping(value="/query/book", method=RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> queryBookByNameOrId(HttpServletRequest request, String bookId,String keyWords){
    	HashMap<String,Object> res = new HashMap<>();
    	if(bookId == null && keyWords == null){
    		res.put("stateCode", "0");
    		res.put("msg","请保证参数关键字(keyWords)和bookId必有其一！");
    		return res;
    	}else if(keyWords != null){
    		ArrayList<Book> books = bookService.getBookByKeyWords(keyWords);
    		res.put("stateCode", "1");
    		res.put("msg","查找成功！");
    		res.put("data",books);
    		return res;
    	}else {
    		Book book = bookService.getBookById(bookId);
    		res.put("stateCode", "1");
    		res.put("msg","查找成功！");
    		res.put("data",book);
    		return res;
    	}
		
    }

    @RequestMapping(value="/get/booklist", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Book> getAllBooks(HttpServletRequest request){
    	ArrayList<Book> books=bookService.getBookList();
    	return books;
    }
    
    @RequestMapping(value="/delete/book", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, String> deleteBook(HttpServletRequest request,String bookId){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	if(bookId == null){
    		res.put("stateCode", "0");
            res.put("msg","传入参数bookId有误");
            return res;
    	}
    		
        int result=bookService.deleteBook(bookId);

        if (result==1){
        	res.put("stateCode", "1");
            res.put("msg","图书删除成功！");
            return res;
        }else {
        	res.put("stateCode", "0");
            res.put("msg","图书删除失败！");
            return res;
        }
    }


    @RequestMapping(value="/create/book", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> addBook(HttpServletRequest request, Book newBook,RedirectAttributes redirectAttributes){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	if(newBook.getName() == null || newBook.getClassId() == 0){
    		res.put("stateCode", "0");
            res.put("msg","至少要填写书籍名称和类别编号（1-22，22代表综合类）");
            return res;
    	}

    	newBook.setState(1); // 1表示图示还在图书馆
    	newBook.setId("");
    	
    	boolean succ=bookService.createBook(newBook);
    	
    	if (succ){
    		res.put("stateCode", "1");
            res.put("msg","图书添加成功！");
            return res;
    	}
    	else {
    		res.put("stateCode", "0");
            res.put("msg","图书添加失败！");
            return res;
    	}
    }


    @RequestMapping(value="/update/book", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> bookEditDo(HttpServletRequest request,Book book,RedirectAttributes redirectAttributes){
    	HashMap<String, String> res = new HashMap<>(); // 预定义结果
    	
    	try {
    		User user = ((User) request.getSession().getAttribute("user"));
    		if(user.getIsAdmin()==0){throw new RuntimeException("你不是管理员！");}
		} catch (Exception e) {
			res.put("stateCode", "0");
            res.put("msg","你还没有登陆或者你不是管理员！");
			return res;
		}
    	
    	if(book.getId() == ""){
    		res.put("stateCode", "0");
            res.put("msg","还没有指定bookId！");
            return res;
    	}
    	
    	boolean succ=bookService.updateBook(book);
    	
    	if (succ){
    		res.put("stateCode", "1");
            res.put("msg","图书修改成功！");
            return res;
    	}
    	else {
    		res.put("stateCode", "0");
            res.put("msg","图书修改失败！");
            return res;
    	}
    }
    
}