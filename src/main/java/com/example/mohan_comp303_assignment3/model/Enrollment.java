package com.example.mohan_comp303_assignment3.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_code", referencedColumnName = "programCode")
    private Program program;

    private String startDate;
    private float amountPaid;
    private String status; // e.g., Pending, Paid
}
