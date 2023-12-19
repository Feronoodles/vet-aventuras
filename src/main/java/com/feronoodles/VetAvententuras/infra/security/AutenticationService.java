package com.feronoodles.VetAvententuras.infra.security;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.employee.EmployeeRepository;
import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.LoginRequest;
import com.feronoodles.VetAvententuras.domain.roles.Roles;
import com.feronoodles.VetAvententuras.domain.token.Token;
import com.feronoodles.VetAvententuras.domain.token.TokenRepository;
import com.feronoodles.VetAvententuras.domain.token.TokenType;
import com.feronoodles.VetAvententuras.domain.token.dto.DatosJWTToken;
import com.feronoodles.VetAvententuras.domain.token.dto.DatosRefreshJWTToken;
import com.feronoodles.VetAvententuras.domain.token.dto.RefreshTokenRequest;
import com.feronoodles.VetAvententuras.domain.users.Users;
import com.feronoodles.VetAvententuras.domain.users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AutenticationService  {
    private UsersRepository usersRepository;

    private EmployeeRepository employeeRepository;
    private TokenRepository tokenRepository;

    private TokenService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public DatosJWTToken login(LoginRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        Users user=usersRepository.findByEmail(request.email()).orElseThrow();
        String token=jwtService.getToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,token);
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

        String token=jwtService.getToken(user);

        usersRepository.save(user);

        saveUserToken(user,token);

        DatosJWTToken datosJWTToken = new DatosJWTToken(token);
        return datosJWTToken;
    }
    public DatosRefreshJWTToken refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.getUsernameFromToken(refreshTokenRequest.getToken());
        Users user = usersRepository.findByEmail(userEmail).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user))
        {
            String jwt = jwtService.getToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user,jwt);
            DatosRefreshJWTToken datosRefreshJWTToken = new DatosRefreshJWTToken(jwt,refreshTokenRequest.getToken());
            return  datosRefreshJWTToken;
        }
        return null;
    }

    public void saveUserToken(Users user,String jwtToken)
    {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Users user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUser_id());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


}
