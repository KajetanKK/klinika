package com.example.klinika;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String haslo;

    public Uzytkownik() {

    }

    public Uzytkownik(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo) { this.haslo = haslo; }
}