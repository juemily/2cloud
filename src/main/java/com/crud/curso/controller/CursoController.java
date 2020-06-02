package com.crud.curso.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.crud.curso.exception.CourseRequestException;
import com.crud.curso.service.CourseService;

@RestController
@RequestMapping("/course")
@ResponseBody
public class CursoController {

	private static final Log LOG = LogFactory.getLog(CursoController.class);

	private boolean validCode = false;
	private List<Course> response = null;

	@Autowired
	@Qualifier("courseServiceImp")
	private CourseService couseService;

	/*
	 * function to add new course
	 */

	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse(@RequestBody final Course course) {
		
		Course returnCourse = new Course();
		
		
		try {
			validCode = codeCheck(course.getCode());
			if (validCode) {

				Course verify = couseService.findByCode(course.getCode());

				if (verify == null) {
					couseService.addCourse(course);

					returnCourse = couseService.findByCode(course.getCode());
				}else {
					throw new CourseRequestException("The Course already Exist");
				}

			}

		} catch (Exception e) {

			LOG.error("addCourse", e);
			
			throw new CourseRequestException(e.getMessage());
		
			

		}

		
		return new ResponseEntity<Course>(returnCourse,HttpStatus.CREATED);
		}
	/*
	 * Function to update a course
	 */
	@PutMapping("/updateCourse")
	public List<Course> updateArticle(@RequestBody final Course course) {

		try {

			validCode = codeCheck(course.getCode());

			if (validCode) {

				couseService.updateCourse(course);

				response = couseService.listAllCourse();

			} else {
				// return couseService.sendError(HttpStatus.NOT_FOUND.value());
				throw new RuntimeException("----test exception------");
			}

		} catch (Exception e) {
			LOG.error("update", e);
			throw new CourseRequestException("Error In Update");
		}

		return response;
	}

	/*
	 * Function to delete a course
	 */

	@DeleteMapping("/deleteCourse/{id}")
	public List<Course> deleteCourse(@PathVariable int id) {

		try {
			couseService.removeCourse(id);
		} catch (Exception e) {
			LOG.error("delete course fail", e);
			throw new CourseRequestException("Error In Delete");
		}

		return couseService.listAllCourse();

	}

	/*
	 * Function to show all courses in DB
	 */
	@GetMapping("/listAllCourses")
	public List<Course> listAllCourse() {
		return couseService.listAllCourse();
	}

	
	/*
	 * function to get course by ID
	 * */
	@GetMapping("/getCourseById/{id}")
	public List<Course> getCourseById (@PathVariable int id){
		try {
			couseService.getCourseById(id);
		} catch (Exception e) {
			LOG.error("get by id", e);
			throw new CourseRequestException("Error getCourseById");
			
		}
		
		return response;
	}
	/*
	 * make a validation of curse code
	 */
	public boolean codeCheck(int code) {
		boolean flag = false;

		int sizeCode = Integer.toString(code).length();

		if (sizeCode <= 4) {
			flag = true;
		}
		return flag;
	}

}
