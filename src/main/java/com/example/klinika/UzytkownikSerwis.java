package com.example.klinika;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikSerwis {
    private final UzytkownikRepozytorium repozytorium;
    private final PasswordEncoder passwordEncoder;

    public UzytkownikSerwis(UzytkownikRepozytorium repozytorium,  PasswordEncoder passwordEncoder) {
        this.repozytorium = repozytorium;
        this.passwordEncoder = passwordEncoder;
    }

    public Uzytkownik zarejestruj(String login, String hasloJawne) {
        if (repozytorium.existsByLogin(login)) {
            throw new RuntimeException("Login " + login + " jest juz zajety");
        }

        String zahaszowane = passwordEncoder.encode(hasloJawne);

        Uzytkownik uzytkownik = new Uzytkownik(login, zahaszowane);
        return repozytorium.save(uzytkownik);
    }
}