package com.example.mohan_comp303_assignment3.service;

import com.example.mohan_comp303_assignment3.model.Program;
import com.example.mohan_comp303_assignment3.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    // Retrieve all programs
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    // Retrieve program by code
    public Program getProgramByCode(Long programCode) {
        return programRepository.findById(programCode).orElse(null);
    }
}
