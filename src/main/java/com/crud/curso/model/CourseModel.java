package com.crud.curso.model;

import javax.persistence.Column;

public class CourseModel {
	
	private int id;
	private String name;
	private int code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public CourseModel(int id, String name, int code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	public CourseModel() {
		super();
	}


}
