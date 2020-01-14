package com.library.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class Book implements Serializable{

	private String id;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private String introduction;
    private String language;
    private BigDecimal price;
    private Date pubdate;
    private int classId;
    private int pressmark;
    private int state;  // 1表示书在馆，0表示不在馆
    private String imgSrc; // 图片地址
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getPubdate() {
		return pubdate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getPressmark() {
		return pressmark;
	}
	public void setPressmark(int pressmark) {
		this.pressmark = pressmark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + id + ", name=" + name + ", author=" + author + ", publish=" + publish + ", isbn="
				+ isbn + ", introduction=" + introduction + ", language=" + language + ", price=" + price + ", pubdate="
				+ pubdate + ", classId=" + classId + ", pressmark=" + pressmark + ", state=" + state + "]";
	}
  
}
