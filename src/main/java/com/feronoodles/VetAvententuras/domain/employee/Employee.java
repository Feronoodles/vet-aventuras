package com.feronoodles.VetAvententuras.domain.employee;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "Employee")
@Table(name = "employee")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    private String first_name;
    private String last_name;
    private double salary;
    private String dni;
    private String address;
    private Date Birthdate;
}
