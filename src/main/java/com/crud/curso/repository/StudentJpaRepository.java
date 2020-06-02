package com.crud.curso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.curso.entity.Student;

@Repository("studentJpaRepository")
public interface StudentJpaRepository extends JpaRepository<Student, Serializable> {

	Student findByRut(String rut);
}
