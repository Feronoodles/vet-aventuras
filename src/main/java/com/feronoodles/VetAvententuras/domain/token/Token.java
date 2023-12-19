package com.feronoodles.VetAvententuras.domain.token;

import com.feronoodles.VetAvententuras.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Token")
@Table(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    private boolean revoked;

    private boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;


}
