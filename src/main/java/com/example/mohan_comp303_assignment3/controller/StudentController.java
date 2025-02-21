package com.example.mohan_comp303_assignment3.controller;

import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.model.Program;
import com.example.mohan_comp303_assignment3.service.StudentService;
import com.example.mohan_comp303_assignment3.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.registerStudent(student);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginStudent(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session) {
        Optional<Student> student = studentService.login(userName, password);
        if (student.isPresent()) {
            session.setAttribute("loggedInStudent", student.get());
            return "redirect:/programs";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidates the session
        return "redirect:/";  // Redirect to login page
    }

    @GetMapping("/student-programs")
    public String showProgramsPage(Model model) {
        model.addAttribute("programs", programService.getAllPrograms());
        return "programs";
    }

    @PostMapping("/programs/checkout")
    public String checkout(@RequestParam Long programCode, HttpSession session) {
        Program selectedProgram = programService.getProgramByCode(programCode);
        if (selectedProgram != null) {
            session.setAttribute("selectedProgram", selectedProgram);
            return "redirect:/checkout";
        }
        return "redirect:/programs";  // Redirect back to programs page if program is not found
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout";
    }

    @PostMapping("/payment/confirm")
    public String confirmPayment(HttpSession session) {
        // Simulate payment processing here
        session.setAttribute("paymentStatus", "Success");
        session.removeAttribute("selectedProgram");  // Clear selected program after payment
        return "redirect:/payment-confirmation";
    }

    @GetMapping("/payment-confirmation")
    public String showPaymentConfirmationPage() {
        return "payment-confirmation";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpSession session) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");
        if (loggedInStudent != null) {
            model.addAttribute("student", loggedInStudent);
            return "profile";
        }
        return "redirect:/";  // If not logged in, redirect to login page
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Student student, HttpSession session) {
        studentService.updateStudent(student);
        session.setAttribute("loggedInStudent", student);  // Update session with new details
        return "redirect:/profile";  // Redirect to profile page after successful update
    }
}
