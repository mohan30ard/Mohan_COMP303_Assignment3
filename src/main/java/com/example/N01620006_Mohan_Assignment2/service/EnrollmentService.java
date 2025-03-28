package com.example.N01620006_Mohan_Assignment2.service;
import com.example.N01620006_Mohan_Assignment2.model.Enrollment;
import com.example.N01620006_Mohan_Assignment2.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Returns a list of all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Create a new enrollment
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Get an enrollment by its ID
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    // Update an existing enrollment
    public Enrollment updateEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Delete an enrollment by ID
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentStudentId(studentId);
    }
}
