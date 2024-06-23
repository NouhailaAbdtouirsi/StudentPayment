package com.example.student_springboot.repositories;

import com.example.student_springboot.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findStudentByCode(String code);
    List<Student> findStudentByProgramId(String programId);

}
