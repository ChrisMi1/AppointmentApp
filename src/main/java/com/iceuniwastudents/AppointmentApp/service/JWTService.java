package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String key;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private long expireInSeconds;

    public String generateToken(Employee employee){
        return Jwts.builder()
                .setSubject(employee.getId())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(new Date(System.currentTimeMillis()+(1000 * expireInSeconds)))
                .setIssuer(issuer)
                .compact();
    }
    public String generateTokenEmail(Employee employee){
        return Jwts.builder()
                .setSubject(employee.getEmail())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(new Date(System.currentTimeMillis()+(1000 * expireInSeconds)))
                .setIssuer(issuer)
                .compact();
    }



    public String getEmployeeId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
