package de.htwberlin.webtech.webtech;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitelContainingIgnoreCase(String titel);
    List<Film> findByGenreContainingIgnoreCase(String genre);
    List<Film> findByTitelContainingIgnoreCaseAndGenreContainingIgnoreCase(String titel, String genre);
}
