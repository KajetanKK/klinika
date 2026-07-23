package com.example.klinika;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtSerwis {
    private final SecretKey klucz;
    private final long waznoscMs;

    public JwtSerwis(
            @Value("${jwt.sekret}") String sekret,
            @Value("${jwt.waznoscMs}") long waznoscMs) {
        this.klucz = Keys.hmacShaKeyFor(sekret.getBytes(StandardCharsets.UTF_8));
        this.waznoscMs = waznoscMs;
    }

    public String wystawToken(String login) {
        Date teraz = new Date();
        Date wygasa = new Date(teraz.getTime() + waznoscMs);

        return Jwts.builder().subject(login).issuedAt(teraz).expiration(wygasa).signWith(klucz).compact();
    }

    public String pobierzLogin(String token) {
        Claims dane = Jwts.parser().verifyWith(klucz).build().parseSignedClaims(token).getPayload();

        return dane.getSubject();
    }

    public boolean czyPoprawny(String token) {
        try {
            pobierzLogin(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}