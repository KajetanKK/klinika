package com.example.klinika;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikSerwis {
    private final UzytkownikRepozytorium repozytorium;
    private final PasswordEncoder passwordEncoder;
    private final JwtSerwis jwtSerwis;

    public UzytkownikSerwis(UzytkownikRepozytorium repozytorium, PasswordEncoder passwordEncoder, JwtSerwis jwtSerwis) {
        this.repozytorium = repozytorium;
        this.passwordEncoder = passwordEncoder;
        this.jwtSerwis = jwtSerwis;
    }

    public Uzytkownik zarejestruj(String login, String hasloJawne) {
        if (repozytorium.existsByLogin(login)) {
            throw new RuntimeException("Login " + login + " jest juz zajety");
        }

        String zahaszowane = passwordEncoder.encode(hasloJawne);

        Uzytkownik uzytkownik = new Uzytkownik(login, zahaszowane, "LEKARZ");
        return repozytorium.save(uzytkownik);
    }

    public String zaloguj(String login, String hasloJawne) {
        Uzytkownik uzytkownik = repozytorium.findByLogin(login).orElse(null);

        if (uzytkownik == null) {
            throw new RuntimeException("Nieprawidlowy login lub haslo");
        }

        if (!passwordEncoder.matches(hasloJawne, uzytkownik.getHaslo())) {
            throw new RuntimeException("Nieprawidlowy login lub haslo");
        }

        return jwtSerwis.wystawToken(login, uzytkownik.getRola());
    }
}