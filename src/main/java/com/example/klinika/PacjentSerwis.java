package com.example.klinika;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacjentSerwis {
    private final PacjentRepozytorium repozytorium;

    public PacjentSerwis(PacjentRepozytorium repozytorium) {
        this.repozytorium = repozytorium;
    }

    public List<Pacjent> pobierzWszystkich() {
        return repozytorium.findAll();
    }

    public Pacjent pobierzPoId(Long id) {
        return repozytorium.findById(id).orElse(null);
    }

    public Pacjent dodaj(Pacjent pacjent) {
        return repozytorium.save(pacjent);
    }

    public Pacjent edytuj(Long id, Pacjent noweDane) {
        Pacjent pacjent = repozytorium.findById(id).orElse(null);

        if (pacjent == null) {
            return null;
        }

        pacjent.setImie(noweDane.getImie());
        pacjent.setNazwisko(noweDane.getNazwisko());
        pacjent.setDataUrodzenia(noweDane.getDataUrodzenia());
        pacjent.setTelefon(noweDane.getTelefon());
        pacjent.setEmail(noweDane.getEmail());
        return repozytorium.save(pacjent);
    }

    public void usun(Long id) {
        repozytorium.deleteById(id);
    }
}