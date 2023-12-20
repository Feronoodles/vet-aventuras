package com.feronoodles.VetAvententuras.domain.employee.dto;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.users.Users;

import java.util.Date;

public record EmployeeRequestDTO(
        String first_name,
        String last_name,
        double salary,
        String dni,
        String address,
        Date birthday,
        String email
) {
    public EmployeeRequestDTO(Users users)
    {
        this(users.getEmployee().getFirst_name(), users.getEmployee().getLast_name(), users.getEmployee().getSalary(), users.getEmployee().getDni(), users.getEmployee().getAddress(), users.getEmployee().getBirthdate(),users.getEmail());
    }
}
