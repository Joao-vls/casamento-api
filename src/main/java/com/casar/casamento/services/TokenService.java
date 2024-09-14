package com.casar.casamento.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.casar.casamento.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public String generateToken(Usuario user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("casamento-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            logger.error("Error creating JWT token", exception);
            throw new RuntimeException("Error while authenticating: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            logger.warn("Token is null or empty");
            return null;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("casamento-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            logger.warn("Invalid token", exception);
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
