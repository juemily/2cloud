package com.crud.curso.service;

import java.util.List;
import java.util.Optional;

import com.crud.curso.entity.Course;

public interface CourseService {

	public abstract List<Course> listAllCourse();

	public abstract Course addCourse(Course course);

	public abstract int removeCourse(int id);

	public abstract Course updateCourse(Course course);

	public abstract Optional<Course> getCourseById(int id);

	public abstract Course findByCode(int code);

	public abstract Course findByName(String name);

}
