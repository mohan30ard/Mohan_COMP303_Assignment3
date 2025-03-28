package com.example.N01620006_Mohan_Assignment2.service;

import com.example.N01620006_Mohan_Assignment2.model.Student;
import com.example.N01620006_Mohan_Assignment2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
