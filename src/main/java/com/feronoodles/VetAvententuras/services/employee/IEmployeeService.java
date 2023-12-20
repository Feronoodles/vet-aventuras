package com.feronoodles.VetAvententuras.services.employee;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.users.Users;
import org.springframework.security.access.prepost.PreAuthorize;



public interface IEmployeeService {
    public Users addVetEmployee(EmployeeRegisterDTO employeedto);

}
