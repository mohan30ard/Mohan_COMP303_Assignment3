package com.example.mohan_comp303_assignment3.repository;
import com.example.mohan_comp303_assignment3.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}

