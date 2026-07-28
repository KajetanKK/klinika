package com.example.klinika;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PacjentSerwisTest {

    @Autowired
    private PacjentSerwis serwis;

    @Test
    void dodajPacjentaZapisujeIzwraca() {
        Pacjent nowy = new Pacjent("Anna", "Nowak", LocalDate.of(1990, 5, 14), "600100200", "anna@example.com");

        Pacjent zapisany = serwis.dodaj(nowy);

        assertNotNull(zapisany.getId());
        assertEquals("Anna", zapisany.getImie());
        assertEquals("Nowak", zapisany.getNazwisko());
    }

    @Test
    void pobierzPoIdZwracaWlasciwegoPacjenta() {
        Pacjent zapisany = serwis.dodaj(
                new Pacjent("Piotr", "Kowalski", LocalDate.of(1985, 11, 2), "600300400", "piotr@example.com"));

        Pacjent znaleziony = serwis.pobierzPoId(zapisany.getId());

        assertNotNull(znaleziony);
        assertEquals("Kowalski", znaleziony.getNazwisko());
    }

    @Test
    void edytujZmieniaDanePacjenta() {
        Pacjent zapisany = serwis.dodaj(
                new Pacjent("Jan", "Zielinski", LocalDate.of(1980, 7, 1), "600700800", "jan@example.com"));

        Pacjent noweDane = new Pacjent("Jan", "Nowak-Zielinski", LocalDate.of(1980, 7, 1), "600700800", "jan@example.com");
        Pacjent zmieniony = serwis.edytuj(zapisany.getId(), noweDane);

        assertEquals("Nowak-Zielinski", zmieniony.getNazwisko());
    }

    @Test
    void usunKasujePacjenta() {
        Pacjent zapisany = serwis.dodaj(
          new Pacjent("Maria", "Lewandowska", LocalDate.of(1975, 12, 5), "600900100", "maria@example.com"));

        Long id = zapisany.getId();

        serwis.usun(id);

        assertNull(serwis.pobierzPoId(id));
    }

}