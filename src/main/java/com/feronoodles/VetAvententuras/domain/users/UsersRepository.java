package com.feronoodles.VetAvententuras.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);



}
