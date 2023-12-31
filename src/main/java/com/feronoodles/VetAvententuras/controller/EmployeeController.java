package com.feronoodles.VetAvententuras.controller;


import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRequestDTO;
import com.feronoodles.VetAvententuras.domain.users.Users;
import com.feronoodles.VetAvententuras.services.employee.IEmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private IEmployeeService iEmployeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<EmployeeRequestDTO> addEmployee(@RequestBody @Valid EmployeeRegisterDTO employeeRegisterDTO)
    {
        Users employee = iEmployeeService.addVetEmployee(employeeRegisterDTO);
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO(employee);
        return ResponseEntity.ok(employeeRequestDTO);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeRequestDTO>> findAllEmployees(@PageableDefault(size = 10)Pageable page)
    {
        return ResponseEntity.ok(iEmployeeService.findAll(page));
    }
}
