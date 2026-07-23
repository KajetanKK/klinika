package com.example.klinika;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UzytkownikKontroler {
    private final UzytkownikSerwis serwis;

    public UzytkownikKontroler(UzytkownikSerwis serwis) {
        this.serwis = serwis;
    }

    @PostMapping("/rejestracja")
    public String zarejestruj(@RequestBody DaneLogowania dane) {
        serwis.zarejestruj(dane.getLogin(), dane.getHaslo());
        return "Zarejestrowano uzytkownika: " + dane.getLogin();
    }
}