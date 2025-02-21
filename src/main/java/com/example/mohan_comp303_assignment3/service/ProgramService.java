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
