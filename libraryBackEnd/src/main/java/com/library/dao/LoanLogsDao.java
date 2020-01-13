package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.library.entity.LoanLogs;
@Repository
public class LoanLogsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    private final static String BOOK_RETURN_SQL_1="UPDATE loan_logs SET return_date = ? WHERE book_id = ? AND return_date is NULL";

    private final static String BOOK_RETURN_SQL_2="UPDATE book SET state = 1 WHERE id = ? ";
    

    private final static String BORROW_BOOK_SQL_1="INSERT INTO loan_logs (id,book_id,reader_id,loan_date) VALUES ( ? , ? , ? , ? )";

    private final static String BORROW_BOOK_SQL_2="UPDATE book SET state = 0 WHERE id = ? ";
    

    private final static String LOG_LIST="SELECT * FROM loan_logs";

    private final static String PERSONAL_LOG_LIST="SELECT * FROM loan_logs WHERE reader_id = ? ";
    
    
    /**
     * 归还书图书
     * @return
     */
    public boolean returnBook(String bookId){
		int res1 = jdbcTemplate.update(BOOK_RETURN_SQL_1,new Object[]{ (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()), bookId } );
		int res2 = jdbcTemplate.update(BOOK_RETURN_SQL_2,new Object[]{ bookId });
    	return res1 > 0 && res2 > 0;
    }

    /**
     * 借取图书
     * @return
     */
    public boolean borrowBook(String bookId, String readerId){
    	int res1 = jdbcTemplate.update(BORROW_BOOK_SQL_1,new Object[]{UUID.randomUUID().toString().replaceAll("-", ""),bookId, readerId, (new SimpleDateFormat("yyyy-MM-dd")).format(new Date())});
		int res2 = jdbcTemplate.update(BORROW_BOOK_SQL_2,new Object[]{ bookId });
    	return res1 > 0 && res2 > 0;
    }

    /**
     * 获取图书日志
     * @return
     */
    public ArrayList<LoanLogs> getLoanLogList(){
        ArrayList<LoanLogs> logs =new ArrayList<LoanLogs>();

        jdbcTemplate.query(LOG_LIST, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                	LoanLogs log=new LoanLogs();
                	log.setId(resultSet.getString("id"));
                	log.setBookId(resultSet.getString("book_id"));
                	log.setReaderId(resultSet.getString("reader_id"));
                	log.setLoanDate(resultSet.getDate("loan_date"));
                	log.setReturnDate(resultSet.getDate("return_date"));
                	logs.add(log);
                }
            }
        });
        return logs;
    }

    /**
     * 获取某个用户的图书日志
     * @param id
     * @return
     */
    public ArrayList<LoanLogs> getPersonalLoanLogList(String id){
        ArrayList<LoanLogs> logs=new ArrayList<LoanLogs>();

        jdbcTemplate.query(PERSONAL_LOG_LIST, new Object[]{id},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                	LoanLogs log=new LoanLogs();
                	log.setId(resultSet.getString("id"));
                	log.setBookId(resultSet.getString("book_id"));
                	log.setReaderId(resultSet.getString("reader_id"));
                	log.setLoanDate(resultSet.getDate("loan_date"));
                	log.setReturnDate(resultSet.getDate("return_date"));
                	logs.add(log);
                }
            }
        });
        return logs;

    }
}
