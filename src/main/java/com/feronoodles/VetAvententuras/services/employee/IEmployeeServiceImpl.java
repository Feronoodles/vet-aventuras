package com.feronoodles.VetAvententuras.services.employee;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRequestDTO;
import com.feronoodles.VetAvententuras.domain.roles.Roles;
import com.feronoodles.VetAvententuras.domain.users.Users;
import com.feronoodles.VetAvententuras.domain.users.UsersRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class IEmployeeServiceImpl implements IEmployeeService{
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder;
    public Users addVetEmployee(EmployeeRegisterDTO employeedto)
    {

        Employee employee = Employee.builder()
                .dni(employeedto.dni())
                .address(employeedto.address())
                .salary(employeedto.salary())
                .last_name(employeedto.last_name())
                .first_name(employeedto.first_name())
                .Birthdate(employeedto.birthday()).build();



        Users user = Users.builder()
                .email(employeedto.email())
                .password(passwordEncoder.encode(employeedto.password()))
                .role(Roles.VET)
                .created_at(LocalDateTime.now())
                .employee(employee)
                .build();

        usersRepository.save(user);

        return user;
    }

    @Override
    public Page<EmployeeRequestDTO> findAll(Pageable page) {

        return (Page<EmployeeRequestDTO>) usersRepository.findUsersByRole(page, Roles.VET).map(EmployeeRequestDTO::new);
    }


}
