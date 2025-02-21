package com.example.mohan_comp303_assignment3.repository;

import com.example.mohan_comp303_assignment3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // For login
    Student findByUserNameAndPassword(String userName, String password);
}

