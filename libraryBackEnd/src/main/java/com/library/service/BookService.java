package com.library.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.BookDao;
import com.library.entity.Book;

@Service
@Transactional(readOnly=false)
public class BookService {
	@Autowired
    private BookDao bookDao;
	
	/**
	 * 新增图书
	 * @param book
	 * @return
	 */
	public boolean createBook(Book book){
        return bookDao.createBook(book)>0;
    }
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public int deleteBook(String bookId){
        return bookDao.deleteBook(bookId);
    }
	
	/**
	 * 更新图书
	 * @param book
	 * @return
	 */
	public boolean updateBook(Book book){
    	Book targetBook = getBookById(book.getId());
    	if(book.getName() == null){
    		book.setName(targetBook.getName());
    		}
    	if(book.getAuthor() == null){
    		book.setAuthor(targetBook.getAuthor());
    	}
    	if(book.getPublish() == null){
    		book.setPublish(targetBook.getPublish());
    		}
    	if(book.getIsbn() == null ){
    		book.setIsbn(targetBook.getIsbn());
    		}
    	if(book.getIntroduction() == null ){
    		book.setIntroduction(targetBook.getIntroduction());
    		}
    	if(book.getLanguage() == null ){
    		book.setLanguage(targetBook.getLanguage());
    		}
    	if(book.getPrice() == null ){
    		book.setPrice(targetBook.getPrice());
    		}
    	if(book.getPubdate() == null ){
    		book.setPubdate(targetBook.getPubdate());
    		}
    	if(book.getClassId() == 0){
    		book.setClassId(targetBook.getClassId());
    		}
    	if(book.getPressmark() == 0){
    		book.setPressmark(targetBook.getPressmark());
    		}
    	if(book.getState() == 0){
    		book.setState(targetBook.getState());
    		}
    	
        return bookDao.updateBook(book)>0;
    }
	
	/**
	 * id查找图书
	 * @param id
	 * @return
	 */
	public Book getBookById(String id){
	  	return bookDao.getBookById(id);
	}
	
	/**
	 * classid查找图书列表
	 * @param id
	 * @return
	 */
	public ArrayList<Book> getBookListByClassId(String classId){
		return bookDao.getBookListByClassId(classId);
	}

	/**
	 * 关键字查找图书列表
	 * @param searchWord
	 * @return
	 */
    public ArrayList<Book> getBookByKeyWords(String searchWord){
        return  bookDao.getBookByKeyWords(searchWord);
    }

    /**
     * 查找全部图书列表
     * @return
     */
    public ArrayList<Book> getBookList(){
        return bookDao.getBookList();
    }

}
