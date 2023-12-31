package com.feronoodles.VetAvententuras.domain.users;

import com.feronoodles.VetAvententuras.domain.roles.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    Page<Users> findUsersByRole(Pageable page, Roles role);

}
