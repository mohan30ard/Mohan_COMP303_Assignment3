package com.example.N01620006_Mohan_Assignment2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;  // PK

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String address;
    private String city;
    private String postalCode;

    // "Dummy data" field for phone number
    private String phoneNumber;
}
