package com.example.mohan_comp303_assignment3.service;

import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Register a new student
    public void registerStudent(Student student) {
        studentRepository.save(student);
    }

    // Login student with username and password
    public Optional<Student> login(String userName, String password) {
        return studentRepository.findByUserNameAndPassword(userName, password);
    }

    // Update student profile
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    // Get student by ID
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

}
