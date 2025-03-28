package com.example.N01620006_Mohan_Assignment2.repository;
import com.example.N01620006_Mohan_Assignment2.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentStudentId(Long studentId);
}

