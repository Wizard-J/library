package com.library.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.LoanLogsDao;
import com.library.entity.Book;
import com.library.entity.LoanLogs;

@Service
@Transactional(readOnly=false,rollbackFor={Exception.class})
public class LoanLogsService {
	@Autowired
    private LoanLogsDao loanLogsDao;
	
	@Autowired
	private BookService bookService;

	/**
	 * 还书
	 * @param bookId
	 * @return
	 */
    public boolean returnBook(String bookId){
        return loanLogsDao.returnBook(bookId);
    }

    
    /**
     * 借书
     * @param bookId
     * @param readerId
     * @return
     */
    public boolean borrowBook(String bookId,String readerId){
    	Book book = bookService.getBookById(bookId);
    	if(book.getState() == 0){ return false; } // 该图书不在馆
        return loanLogsDao.borrowBook(bookId,readerId);
    }

    /**
     * 借书日志
     * @return
     */
    public ArrayList<LoanLogs> getLogs(){
    	ArrayList<LoanLogs> logs = loanLogsDao.getLoanLogList();
    	for (LoanLogs log : logs) {
			log.setBook(bookService.getBookById(log.getBookId()));
		}
        return logs;
    }
    
    /**
     * 个人借书日志
     * @param readerId
     * @return
     */
    public ArrayList<LoanLogs> getPersonalLogs(String readerId){
    	ArrayList<LoanLogs> logs = loanLogsDao.getPersonalLoanLogList(readerId);
    	for (LoanLogs log : logs) {
			log.setBook(bookService.getBookById(log.getBookId()));
		}
        return logs;
    }

}
