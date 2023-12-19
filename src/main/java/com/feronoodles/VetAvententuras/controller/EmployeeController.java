package com.feronoodles.VetAvententuras.controller;


import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @PostMapping
    public String addEmployee(@RequestBody @Valid EmployeeRegisterDTO employeeRegisterDTO)
    {

        return "";
    }
}
