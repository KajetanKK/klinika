package com.example.klinika;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UzytkownikRepozytorium extends JpaRepository<Uzytkownik, Long> {
    Optional<Uzytkownik> findByLogin(String login);

    boolean existsByLogin(String login);
}