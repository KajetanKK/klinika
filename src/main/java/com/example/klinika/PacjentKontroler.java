package com.example.klinika;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacjenci")
public class PacjentKontroler {
    private final PacjentSerwis serwis;

    public PacjentKontroler(PacjentSerwis serwis) {
        this.serwis = serwis;
    }

    @GetMapping
    public List<Pacjent> pobierzWszystkich() {
        return serwis.pobierzWszystkich();
    }

    @GetMapping("/{id}")
    public Pacjent pobierzPoId(@PathVariable Long id) {
        return serwis.pobierzPoId(id);
    }

    @PostMapping
    public Pacjent dodaj(@RequestBody Pacjent pacjent) {
        return serwis.dodaj(pacjent);
    }

    @PutMapping("/{id}")
    public Pacjent edytuj(@PathVariable Long id, @RequestBody Pacjent noweDane) {
        return serwis.edytuj(id, noweDane);
    }

    @DeleteMapping("/{id}")
    public void usun(@PathVariable Long id) {
        serwis.usun(id);
    }
}
