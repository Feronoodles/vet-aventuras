package com.feronoodles.VetAvententuras.infra.security;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.EmployeeRepository;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.LoginRequest;
import com.feronoodles.VetAvententuras.domain.roles.Roles;
import com.feronoodles.VetAvententuras.domain.users.Users;
import com.feronoodles.VetAvententuras.domain.users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AutenticationService  {
    private UsersRepository usersRepository;

    private EmployeeRepository employeeRepository;

    private TokenService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public DatosJWTToken login(LoginRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        UserDetails user=usersRepository.findByEmail(request.email()).orElseThrow();
        String token=jwtService.getToken(user);
        System.out.println(token);
        DatosJWTToken datosJWTToken = new DatosJWTToken(token);
        return datosJWTToken;
    }

    public DatosJWTToken register(EmployeeRegisterDTO request) {
        Employee employee = Employee.builder()
                .dni(request.dni())
                .address(request.address())
                .salary(request.salary())
                .last_name(request.last_name())
                .first_name(request.first_name())
                .Birthdate(request.birthday()).build();

        employeeRepository.save(employee);

        Users user = Users.builder()
                .email(request.email())
                .password(passwordEncoder.encode( request.password()))
                .role(Roles.ADMINISTRATOR)
                .created_at(LocalDateTime.now())
                .employee(employee)
                .build();

        usersRepository.save(user);

        DatosJWTToken datosJWTToken = new DatosJWTToken(jwtService.getToken(user));
        return datosJWTToken;
    }

}
