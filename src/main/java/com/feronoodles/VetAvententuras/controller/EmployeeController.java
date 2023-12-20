package com.feronoodles.VetAvententuras.controller;


import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRequestDTO;
import com.feronoodles.VetAvententuras.domain.users.Users;
import com.feronoodles.VetAvententuras.services.employee.IEmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class EmployeeController {

    public IEmployeeService iEmployeeService;

    @PostMapping
    public ResponseEntity<EmployeeRequestDTO> addEmployee(@RequestBody @Valid EmployeeRegisterDTO employeeRegisterDTO)
    {
        Users employee = iEmployeeService.addVetEmployee(employeeRegisterDTO);
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO(employee);
        return ResponseEntity.ok(employeeRequestDTO);
    }
}
