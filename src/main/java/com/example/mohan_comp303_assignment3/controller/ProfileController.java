package com.example.mohan_comp303_assignment3.controller;

import com.example.mohan_comp303_assignment3.model.Student;
import com.example.mohan_comp303_assignment3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public String showProfile(@PathVariable Long studentId, Model model) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            return "redirect:/";
        }
        model.addAttribute("student", student);
        return "profile"; // profile.html
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("student") Student updatedStudent, Model model) {
        Student existing = studentService.findById(updatedStudent.getStudentId());
        if (existing == null) {
            return "redirect:/";
        }

        // Update relevant fields
        existing.setAddress(updatedStudent.getAddress());
        existing.setCity(updatedStudent.getCity());
        existing.setPostalCode(updatedStudent.getPostalCode());
        existing.setPhoneNumber(updatedStudent.getPhoneNumber());

        // If you want them to update name, password, etc., do it here too
        // existing.setFirstName(updatedStudent.getFirstName());
        // existing.setLastName(updatedStudent.getLastName());

        studentService.updateStudent(existing);
        model.addAttribute("student", existing);
        model.addAttribute("message", "Profile updated successfully!");
        return "profile";
    }
}
