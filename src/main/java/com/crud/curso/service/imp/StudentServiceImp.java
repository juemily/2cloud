package com.crud.curso.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crud.curso.entity.Student;
import com.crud.curso.repository.StudentJpaRepository;
import com.crud.curso.service.StudentService;

@Service("studentServiceImp")
public class StudentServiceImp implements StudentService{
	
	@Autowired
	@Qualifier("studentJpaRepository")
	private StudentJpaRepository studentJpaRepository;

	@Override
	public List<Student> getAllStudents() {

		return studentJpaRepository.findAll();
	}

	@Override
	public Student addStudent(Student student) {
	
		return studentJpaRepository.save(student);
	}

	@Override
	public int removeStudent(int id) {

		studentJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Student updateStudent(Student student) {

		return studentJpaRepository.save(student);
	}

	@Override
	public Optional<Student> getStudentById(int id) {

		return studentJpaRepository.findById(id);
	}

	@Override
	public Student findByRut(String rut) {
		return studentJpaRepository.findByRut(rut);
	}
	

}
