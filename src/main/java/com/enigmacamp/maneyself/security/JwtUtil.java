package com.enigmacamp.maneyself.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigmacamp.maneyself.model.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.maneyselfapp.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.maneyselfapp.jwt.app-name}")
    private String appName;
    @Value("${app.maneyselfapp.jwt.jwt-expired}")
    private Long jwtExpired;
    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser appUser) {
        return JWT.create()
                //Set Payload JWT
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Instant.now().plusSeconds(jwtExpired))
                .withIssuedAt(Instant.now())
                .withClaim("role", appUser.getRole().name())
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {

        JWTVerifier verfier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verfier.verify(token);
        return decodedJWT.getIssuer().equals(appName);

    }

    public Map<String, String> getUserInfoByToken(String token) {

        JWTVerifier verfier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verfier.verify(token);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", decodedJWT.getSubject());
        userInfo.put("role", decodedJWT.getClaim("role").asString());
        return userInfo;

    }
}
