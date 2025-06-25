package br.ueg.progweb1.empresa.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(jwtSecret);
    }

    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .sign(getAlgorithm());
    }

    public String getUsernameFromToken(String token) {
        try {
            DecodedJWT decodedJWT = getVerifier().verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            logger.error("Token JWT inválido: {}", exception.getMessage());
            return null;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()));
    }

    private JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
