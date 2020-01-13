package com.library.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

@Repository
public class BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String ADD_BOOK = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String DELETE_BOOK = "delete from book where id = ?  ";
	private final static String UPDATE_BOOK = "update book set name= ? ,author= ? ,publish= ? ,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,pubdate= ? ,class_id= ? ,pressmark= ? ,state= ?  where book_id= ? ;";
	private final static String SELECT_ALL_BOOKS = "SELECT * FROM book ";
	private final static String SELECT_BOOK_BY_NAME_OR_ABSTRACT = "SELECT * FROM book WHERE name like ? OR author like ? OR publish like ? OR introduction like ? OR language like ?";
	private final static String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?   ";

	/**
	 * 新增图书
	 * 
	 * @param book
	 * @return
	 */
	public int createBook(Book book) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		String name = book.getName();
		String author = book.getAuthor();
		String publish = book.getPublish();
		String isbn = book.getIsbn();
		String introduction = book.getIntroduction();
		String language = book.getLanguage();
		BigDecimal price = book.getPrice();
		Date pubdate = book.getPubdate();
		int classId = book.getClassId();
		int pressmark = book.getPressmark();
		int state = book.getState();

		return jdbcTemplate.update(ADD_BOOK, new Object[] { id, name, author, publish, isbn, introduction, language,
				price, pubdate, classId, pressmark, state });
	}

	/**
	 * 删除图书
	 * 
	 * @param bookId
	 * @return
	 */
	public int deleteBook(String bookId) {
		return jdbcTemplate.update(DELETE_BOOK, bookId);
	}

	/**
	 * 更新图书
	 * 
	 * @param book
	 * @return
	 */
	public int updateBook(Book book) {
		String bookId = book.getBookId();
		String name = book.getName();
		String author = book.getAuthor();
		String publish = book.getPublish();
		String isbn = book.getIsbn();
		String introduction = book.getIntroduction();
		String language = book.getLanguage();
		BigDecimal price = book.getPrice();
		Date pubdate = book.getPubdate();
		int classId = book.getClassId();
		int pressmark = book.getPressmark();
		int state = book.getState();
		return jdbcTemplate.update(UPDATE_BOOK, new Object[] { name, author, publish, isbn, introduction, language,
				price, pubdate, classId, pressmark, state, bookId });
	}

	/**
	 * id获取图书
	 * 
	 * @param id
	 * @return
	 */
	public Book getBookById(String id) {
		Book book = new Book();
		jdbcTemplate.query(SELECT_BOOK_BY_ID, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					book.setBookId(resultSet.getString("id"));
					book.setAuthor(resultSet.getString("author"));
					book.setClassId(resultSet.getInt("class_id"));
					book.setIntroduction(resultSet.getString("introduction"));
					book.setIsbn(resultSet.getString("isbn"));
					book.setLanguage(resultSet.getString("language"));
					book.setName(resultSet.getString("name"));
					book.setPressmark(resultSet.getInt("pressmark"));
					book.setPubdate(resultSet.getDate("pubdate"));
					book.setPrice(resultSet.getBigDecimal("price"));
					book.setState(resultSet.getInt("state"));
					book.setPublish(resultSet.getString("publish"));
				}
			}
		});
		return book;
	}

	/**
	 * 关键字获取图书列表
	 * 
	 * @param keywords
	 * @return
	 */
	public ArrayList<Book> getBookByKeyWords(String keywords) {
		ArrayList<Book> books = new ArrayList<Book>();
		jdbcTemplate.query(SELECT_BOOK_BY_NAME_OR_ABSTRACT, new Object[] { "%" + keywords + "%", "%" + keywords + "%",
				"%" + keywords + "%", "%" + keywords + "%", "%" + keywords + "%" }, new RowCallbackHandler() {
					public void processRow(ResultSet resultSet) throws SQLException {
						resultSet.beforeFirst();
						while (resultSet.next()) {
							Book book = new Book();
							book.setBookId(resultSet.getString("id"));
							book.setAuthor(resultSet.getString("author"));
							book.setClassId(resultSet.getInt("class_id"));
							book.setIntroduction(resultSet.getString("introduction"));
							book.setIsbn(resultSet.getString("isbn"));
							book.setLanguage(resultSet.getString("language"));
							book.setName(resultSet.getString("name"));
							book.setPressmark(resultSet.getInt("pressmark"));
							book.setPubdate(resultSet.getDate("pubdate"));
							book.setPrice(resultSet.getBigDecimal("price"));
							book.setState(resultSet.getInt("state"));
							book.setPublish(resultSet.getString("publish"));
							books.add(book);
						}

					}
				});
		return books;
	}

	/**
	 * 获取全部图书列表
	 * 
	 * @return
	 */
	public ArrayList<Book> getBookList() {
		final ArrayList<Book> books = new ArrayList<Book>();

		jdbcTemplate.query(SELECT_ALL_BOOKS, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Book book = new Book();
					book.setBookId(resultSet.getString("id"));
					book.setPrice(resultSet.getBigDecimal("price"));
					book.setState(resultSet.getInt("state"));
					book.setPublish(resultSet.getString("publish"));
					book.setPubdate(resultSet.getDate("pubdate"));
					book.setName(resultSet.getString("name"));
					book.setIsbn(resultSet.getString("isbn"));
					book.setClassId(resultSet.getInt("class_id"));
					book.setAuthor(resultSet.getString("author"));
					book.setIntroduction(resultSet.getString("introduction"));
					book.setPressmark(resultSet.getInt("pressmark"));
					book.setLanguage(resultSet.getString("language"));
					books.add(book);
				}
			}
		});
		return books;

	}

}
