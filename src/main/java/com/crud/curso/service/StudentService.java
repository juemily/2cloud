package com.crud.curso.service;

import java.util.List;
import java.util.Optional;

import com.crud.curso.entity.Student;

public interface StudentService {
	
	public abstract List<Student> getAllStudents();
	
	public abstract  Student addStudent(Student student);
	
	public abstract int removeStudent(int id);
	
	public abstract Student updateStudent(Student student);
	
	public abstract Optional<Student> getStudentById(int id);
	
	public abstract Student findByRut(String rut);
	
	

}
