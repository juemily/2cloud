package com.crud.curso.model;

import javax.persistence.Column;

import com.sun.istack.NotNull;

public class StudentModel {
	private int id;

	private String rut;


	private String name;


	private String lastName;


	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	private String course;

	public StudentModel(int id, String rut, String name, String lastName, int age, String course) {
		super();
		this.id = id;
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.course = course;
	}

	public StudentModel() {
		super();
	}
	
	
}
