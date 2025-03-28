package com.example.N01620006_Mohan_Assignment2.controller;

import com.example.N01620006_Mohan_Assignment2.model.Student;
import com.example.N01620006_Mohan_Assignment2.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        // Provide a fresh Student object for registration
        model.addAttribute("student", new Student());
        return "index"; // index.html
    }

    @PostMapping("/register")
    public String registerStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            // If validation fails, reload index page
            return "index";
        }
        studentService.registerStudent(student);
        model.addAttribute("message", "Registration successful! You can now login.");
        return "index";
    }

    @PostMapping("/login")
    public String loginStudent(
            @RequestParam String userName,
            @RequestParam String password,
            Model model) {

        Student student = studentService.login(userName, password);
        if (student == null) {
            model.addAttribute("loginError", "Invalid credentials!");
            return "index";
        }
        // Successful login -> go to programs page
        //return "redirect:/programs?studentId=" + student.getStudentId();
        return "redirect:/dashboard?studentId=" + student.getStudentId();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clears the session
        return "redirect:/";  // Redirects to index page
    }
}
