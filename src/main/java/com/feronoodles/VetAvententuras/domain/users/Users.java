package com.feronoodles.VetAvententuras.domain.users;

import com.feronoodles.VetAvententuras.domain.employee.Employee;
import com.feronoodles.VetAvententuras.domain.roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "Users")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private LocalDateTime created_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
