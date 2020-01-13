package com.library.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class LoanLogs implements Serializable {

	private String id;
    private String bookId;
    private String readerId;
    private Date loanDate;
    private Date returnDate;
    private Book book;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "LoanLogs [id=" + id + ", bookId=" + bookId + ", readerId=" + readerId + ", loanDate=" + loanDate
				+ ", returnDate=" + returnDate + "]";
	}
    
}
