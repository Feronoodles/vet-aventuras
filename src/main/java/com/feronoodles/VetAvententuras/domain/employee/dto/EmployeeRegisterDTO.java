package com.feronoodles.VetAvententuras.domain.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record EmployeeRegisterDTO(
        @NotBlank
        String first_name,
        @NotBlank
        String last_name,
        @NotNull
        double salary,
        @NotBlank
        String dni,
        @NotBlank
        String address,
        @NotNull
        Date birthday,
        @NotBlank
        String email,
        @NotBlank
        String password

) {
}
