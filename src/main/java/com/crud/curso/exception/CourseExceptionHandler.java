package com.crud.curso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseExceptionHandler {
	@ExceptionHandler(value = { CourseRequestException.class })
	public ResponseEntity<Object> handleCourseRequestException(CourseRequestException e) {
		CourseException courseException = new CourseException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(courseException, HttpStatus.BAD_REQUEST);

	}
}
