package com.library.entity;

public class Classification{
	private String id;
	private String index;
	private String className;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	@Override
	public String toString() {
		return "Classification [id=" + id + ", index=" + index + ", className=" + className + "]";
	}
	
}