package com.example.mohan_comp303_assignment3.service;
import com.example.mohan_comp303_assignment3.model.Enrollment;
import com.example.mohan_comp303_assignment3.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void enrollStudent(Enrollment enrollment) {
       // enrollment.setStatus("Pending");
        enrollmentRepository.save(enrollment);
    }
}
