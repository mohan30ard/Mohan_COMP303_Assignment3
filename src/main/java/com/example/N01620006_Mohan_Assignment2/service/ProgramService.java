package com.example.N01620006_Mohan_Assignment2.service;

import com.example.N01620006_Mohan_Assignment2.model.Program;
import com.example.N01620006_Mohan_Assignment2.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }

    public Program findById(Long programCode) {
        return programRepository.findById(programCode).orElse(null);
    }

    public Program getProgramById(Long programId) {
        return programRepository.findById(programId).orElse(null);
    }
}
