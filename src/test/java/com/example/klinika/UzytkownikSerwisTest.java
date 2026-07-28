package com.example.klinika;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UzytkownikSerwisTest {

    @Autowired
    private UzytkownikSerwis serwis;

    @Test
    void rejestracjaHaszujeHaslo() {
        Uzytkownik u = serwis.zarejestruj("jan", "tajne123");

        assertNotEquals("tajne123", u.getHaslo());
        assertTrue(u.getHaslo().startsWith("$2"));
        assertEquals("LEKARZ", u.getRola());
    }

    @Test
    void rejestracjaOdrzucaZajetyLogin() {
        serwis.zarejestruj("anna", "haslo1");

        assertThrows(RuntimeException.class, () -> {
            serwis.zarejestruj("anna", "haslo2");
        });
    }

    @Test
    void logowaniePoprawnymHaslemZwracaToken() {
        serwis.zarejestruj("piotr", "mojehaslo");

        String token = serwis.zaloguj("piotr", "mojehaslo");

        assertNotNull(token);
        assertTrue(token.contains("."));
    }

    @Test
    void logowanieZlymHaslemRzucaWyjatek() {
        serwis.zarejestruj("ewa", "prawidlowe");

        assertThrows(RuntimeException.class, () -> {
            serwis.zarejestruj("ewa", "zle-haslo");
        });
    }

}