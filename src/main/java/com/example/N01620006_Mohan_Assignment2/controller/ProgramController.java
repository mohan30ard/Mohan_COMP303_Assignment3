package com.example.N01620006_Mohan_Assignment2.controller;
import com.example.N01620006_Mohan_Assignment2.model.Program;
import com.example.N01620006_Mohan_Assignment2.model.Student;
import com.example.N01620006_Mohan_Assignment2.service.ProgramService;
import com.example.N01620006_Mohan_Assignment2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/programs")
    public String showPrograms(@RequestParam Long studentId, Model model) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            return "redirect:/";
        }
        List<Program> programs = programService.findAllPrograms();
        model.addAttribute("student", student);
        model.addAttribute("programs", programs);
        return "programs"; // programs.html
    }
}

