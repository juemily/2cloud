package com.crud.curso.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.curso.entity.Course;
import com.crud.curso.entity.Student;
import com.crud.curso.exception.CourseRequestException;
import com.crud.curso.service.CourseService;
import com.crud.curso.service.StudentService;


@RestController
@RequestMapping("/student")
@ResponseBody
public class StudenController {

	private static final Log LOG = LogFactory.getLog(CursoController.class);

	private Student validStudent = null;
	private boolean validRut = false;
	private List<Student> response = null;
	private Course courseExist = null;

	@Autowired
	@Qualifier("studentServiceImp")
	private StudentService studentService;

	@Autowired
	@Qualifier("courseServiceImp")
	private CourseService couseService;

	/*
	 * function to add a new student
	 */

	@PostMapping("/addStudent")
	public List<Student> addStudent(@RequestBody final Student student) {

		try {

			if (!student.getRut().isEmpty()) {
				validRut = verifyRut(student.getRut());

			}

			if (!student.getCourse().isEmpty()) {

				courseExist = couseService.findByName(student.getCourse());

			}

			validStudent = studentService.findByRut(student.getRut());

			if (validStudent == null && validRut && (student.getAge() >= 18) && courseExist != null) {
				studentService.addStudent(student);
				response = studentService.getAllStudents();
			}else {
				throw new CourseRequestException("Error addStudent");
			}

		} catch (Exception e) {
			LOG.error("add Student", e);
			throw new CourseRequestException(e.getMessage());
		}
		return response;
	}

	/*
	 * function to update students
	 */
	@PutMapping("/updateStudent")
	public List<Student> updateStudent(@RequestBody final Student student) {

		try {
			if (!student.getRut().isEmpty()) {
				validRut = verifyRut(student.getRut());

			}

			validStudent = studentService.findByRut(student.getRut());

			if (validStudent == null && validRut && (student.getAge() >= 18)) {
				studentService.updateStudent(student);
				response = studentService.getAllStudents();
			}else {
				throw new CourseRequestException("Error updateStudent");
			}

		} catch (Exception e) {
			LOG.error("update Student", e);
			throw new CourseRequestException(e.getMessage());
		}
		return response;
	}

	/*
	 * function to delete student
	 * 
	 */
	@DeleteMapping("/deleteUser/{id}")
	public List<Student> deleteStudentById(@PathVariable int id) {
		try {
			studentService.removeStudent(id);
			
		} catch (Exception e) {
			LOG.error("delete student fail", e);
			throw new CourseRequestException(e.getMessage());
		}
		response = studentService.getAllStudents();
		return response;
	}

	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {

		response = studentService.getAllStudents();

		return response;
	}

	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable int id) {
		Optional<Student> optional = null;
		try {

			optional = studentService.getStudentById(id);

		} catch (Exception e) {
			LOG.error("get student by id fail", e);
			throw new CourseRequestException(e.getMessage());
		}
		return optional;

	}

	/*
	 * function to validate rut format with regex
	 */
	public static Boolean verifyRut(String rut) {
		Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
		Matcher matcher = pattern.matcher(rut);
		if (matcher.matches() == false)
			return false;
		String[] stringRut = rut.split("-");
		return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
	}

	/*
	 * function to validate verification digit
	 */
	public static String dv(String rut) {
		Integer M = 0, S = 1, T = Integer.parseInt(rut);
		for (; T != 0; T = (int) Math.floor(T /= 10))
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		return (S > 0) ? String.valueOf(S - 1) : "k";
	}

}
