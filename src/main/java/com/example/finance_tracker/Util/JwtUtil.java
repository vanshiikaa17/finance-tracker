package com.example.finance_tracker.Util;
import com.example.finance_tracker.config.JwtConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.finance_tracker.models.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    /**
     * Generate a JWT token for the given user.
     *
     * @param user the User object containing email and other details
     * @return the generated JWT token
     */
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        
        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .sign(algorithm);
    }

    /**
     * Extract the email from the given JWT token.
     *
     * @param token the JWT token
     * @return the extracted email
     */
    public String extractEmail(String token) {
        if (!validateToken(token)) {
            throw new RuntimeException("Invalid or expired JWT token");
        }
        try {
           
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    /**
     * Validate the JWT token.
     *  
     * @param token the JWT token
     * @return true if valid, otherwise false
     */
    public boolean validateToken(String token) {
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
