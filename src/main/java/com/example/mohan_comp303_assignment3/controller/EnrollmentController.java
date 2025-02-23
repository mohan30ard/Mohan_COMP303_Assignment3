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

import java.time.LocalDate;
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


    // ***** NEW: Explicit checkout mapping *****
    @GetMapping("/checkout")
    public String showCheckout(@RequestParam Long studentId,
                               @RequestParam Long programCode,
                               Model model) {
        Student student = studentService.getStudentById(studentId);
        Program program = programService.getProgramById(programCode);
        if (student == null || program == null) {
            return "redirect:/";  // or display an error message
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollment.setStartDate(LocalDate.now());
        enrollment.setStatus("Pending");
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("student", student);

        return "checkout"; // Returns checkout.html template
    }

    // Process payment (dummy processing)
    @PostMapping("/process")
    public String processPayment(@ModelAttribute("enrollment") Enrollment enrollment) {
        enrollment.setAmountPaid(enrollment.getProgram().getFee());
        enrollment.setStatus("Paid");
        Enrollment saved = enrollmentService.createEnrollment(enrollment);
        return "redirect:/enrollments/confirmation?applicationNo=" + saved.getApplicationNo();
    }

    // Confirmation page
    @GetMapping("/confirmation")
    public String showConfirmation(@RequestParam Long applicationNo, Model model) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(applicationNo);
        if (enrollment == null) {
            return "redirect:/";
        }
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("student", enrollment.getStudent());
        return "confirmation"; // Returns confirmation.html
    }

    // ----- Other CRUD methods (optional) -----

    // CREATE - Show form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        return "enrollment-form";
    }

    // CREATE - Process form
    @PostMapping
    public String createEnrollment(@RequestParam Long studentId,
                                   @RequestParam Long programId,
                                   @ModelAttribute("enrollment") Enrollment enrollment) {
        Student student = studentService.getStudentById(studentId);
        Program program = programService.getProgramById(programId);
        if (student == null || program == null) {
            return "redirect:/enrollments";
        }
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollmentService.createEnrollment(enrollment);
        return "redirect:/enrollments";
    }

    // READ by ID
    @GetMapping("/{id}")
    public String getEnrollmentById(@PathVariable("id") Long id, Model model) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment == null) {
            return "redirect:/enrollments";
        }
        model.addAttribute("enrollment", enrollment);
        return "enrollment-detail";
    }

    // UPDATE - Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment == null) {
            return "redirect:/enrollments";
        }
        model.addAttribute("enrollment", enrollment);
        return "enrollment-form";
    }

    // UPDATE - Process form
    @PostMapping("/{id}")
    public String updateEnrollment(@PathVariable("id") Long id,
                                   @RequestParam Long studentId,
                                   @RequestParam Long programId,
                                   @ModelAttribute("enrollment") Enrollment updatedEnrollment) {
        Enrollment existingEnrollment = enrollmentService.getEnrollmentById(id);
        if (existingEnrollment == null) {
            return "redirect:/enrollments";
        }
        Student student = studentService.getStudentById(studentId);
        Program program = programService.getProgramById(programId);
        if (student == null || program == null) {
            return "redirect:/enrollments";
        }
        existingEnrollment.setStudent(student);
        existingEnrollment.setProgram(program);
        existingEnrollment.setStartDate(updatedEnrollment.getStartDate());
        existingEnrollment.setAmountPaid(updatedEnrollment.getAmountPaid());
        existingEnrollment.setStatus(updatedEnrollment.getStatus());
        enrollmentService.updateEnrollment(existingEnrollment);
        return "redirect:/enrollments/" + id;
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable("id") Long id) {
        // Retrieve the enrollment before deletion
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment == null) {
            return "redirect:/enrollments";
        }
        Long studentId = enrollment.getStudent().getStudentId();
        // Delete the enrollment
        enrollmentService.deleteEnrollment(id);
        // Redirect with the studentId parameter
        return "redirect:/enrollments?studentId=" + studentId;
    }
}
