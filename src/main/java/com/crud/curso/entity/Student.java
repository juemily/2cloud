package com.crud.curso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "rut")
	@NotNull
	private String rut;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Column(name = "age")
	@NotNull
	private int age;

	@Column(name = "course")
	@NotNull
	private String course;

	public Student() {
	}

	public Student(int id, String rut, String name, String lastName, int age, String course) {
		super();
		this.id = id;
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.course = course;
	}

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

}
