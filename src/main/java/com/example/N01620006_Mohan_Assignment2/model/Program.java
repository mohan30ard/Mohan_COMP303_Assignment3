package com.example.N01620006_Mohan_Assignment2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programCode;  // PK

    @NotBlank
    private String programName;

    private int duration;   // e.g., number of semesters
    private double fee;     // application/tuition fee
}
