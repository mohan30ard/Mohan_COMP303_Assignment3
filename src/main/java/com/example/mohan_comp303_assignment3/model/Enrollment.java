package com.example.mohan_comp303_assignment3.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;  // PK

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;     // FK -> students

    @ManyToOne
    @JoinColumn(name = "program_code", nullable = false)
    private Program program;     // FK -> programs

    private LocalDate startDate; // date of enrollment
    private double amountPaid;   // fee paid
    private String status;       // e.g., "Pending", "Paid"
}
