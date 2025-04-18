package com.example.demouniclubBE.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtHelper {

    @Value("${jwt.private-key}")
    private String key;

    public String generateToken (int id, String data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        Date currentDate = new Date();
        long datePlus = 8*60*60*1000;
        Date futureDate = new Date(currentDate.getTime() + datePlus);

        String token = Jwts.builder()
                .id(String.valueOf(id))
                .subject(data)
                .expiration(futureDate)
                .signWith(secretKey)
                .compact();

        return token;
    }

    public String deCodeToken(String token) {
        String roleName = "";
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        try {
            roleName = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
        } catch (Exception e) {
            System.out.println("decode token fail " + e.getMessage());
        }
        return roleName;
    }

    public String getIdUserFromToken(HttpServletRequest request) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        try {
            String idUser = "";
            String token = request.getHeader("Authorization").substring(7);
            idUser = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getId();
            return idUser;
        } catch (Exception e) {
            throw new RuntimeException("Error " + e.getMessage());
        }
    }

}
