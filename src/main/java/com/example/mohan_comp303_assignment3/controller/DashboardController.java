package com.example.mohan_comp303_assignment3.controller;

import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "redirect:/";  // if not found, send back to login/index
        }
        model.addAttribute("student", student);
        return "dashboard";  // returns dashboard.html
    }
}
