package com.example.N01620006_Mohan_Assignment2.repository;

import com.example.N01620006_Mohan_Assignment2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // For login
    Student findByUserNameAndPassword(String userName, String password);
}

