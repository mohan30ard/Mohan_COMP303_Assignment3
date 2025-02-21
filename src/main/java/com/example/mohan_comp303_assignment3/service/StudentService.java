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

    public Student registerStudent(Student student) {
        // Could check if userName already exists, etc.
        return studentRepository.save(student);
    }

    public Student login(String userName, String password) {
        return studentRepository.findByUserNameAndPassword(userName, password);
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
