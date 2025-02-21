package com.example.mohan_comp303_assignment3.controller;
import com.example.mohan_comp303_assignment3.model.Program;
import com.example.mohan_comp303_assignment3.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @GetMapping("/programs")
    public String showPrograms(Model model) {
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "programs";
    }
}

