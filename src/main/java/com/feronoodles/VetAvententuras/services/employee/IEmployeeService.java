package com.feronoodles.VetAvententuras.services.employee;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRequestDTO;
import com.feronoodles.VetAvententuras.domain.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;



public interface IEmployeeService {
    public Users addVetEmployee(EmployeeRegisterDTO employeedto);

    public Page<EmployeeRequestDTO> findAll(Pageable page);

}
