package com.example.mohan_comp303_assignment3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
}
