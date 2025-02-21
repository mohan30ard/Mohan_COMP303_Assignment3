package com.example.mohan_comp303_assignment3.controller;
import com.example.mohan_comp303_assignment3.model.Enrollment;
import com.example.mohan_comp303_assignment3.model.Program;
import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.service.EnrollmentService;
import com.example.mohan_comp303_assignment3.service.ProgramService;
import com.example.mohan_comp303_assignment3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam Long studentId, @RequestParam Long programCode, Model model) {
        Student student = studentService.getStudentById(studentId);
        Program program = programService.getProgramByCode(programCode);

        if (student == null || program == null) {
            model.addAttribute("error", "Invalid Student or Program");
            return "programs";
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollment.setStartDate("2025-03-01"); // Dummy start date
        enrollment.setAmountPaid(program.getFee());
        enrollment.setStatus("Paid");

        enrollmentService.enrollStudent(enrollment);
        model.addAttribute("message", "Enrollment Successful!");

        return "confirmation";
    }
}

