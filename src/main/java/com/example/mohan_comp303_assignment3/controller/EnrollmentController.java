package com.example.mohan_comp303_assignment3.controller;
import com.example.mohan_comp303_assignment3.model.Enrollment;
import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.service.EnrollmentService;
import com.example.mohan_comp303_assignment3.service.ProgramService;
import com.example.mohan_comp303_assignment3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;

    @GetMapping
    public String listAllEnrollments(@RequestParam Long studentId, Model model) {
        // Retrieve only enrollments for this student
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        model.addAttribute("enrollments", enrollments);

        // Retrieve and add the logged-in student so the header can use it
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);

        return "enrollment-list"; // Thymeleaf template for listing enrollments
    }
}
