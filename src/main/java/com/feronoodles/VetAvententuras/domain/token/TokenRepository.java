package com.feronoodles.VetAvententuras.domain.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query(value = """
      select t from Token t inner join Users u\s
      on t.user.user_id = u.user_id\s
      where u.user_id = ?1 and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Long id);
}
