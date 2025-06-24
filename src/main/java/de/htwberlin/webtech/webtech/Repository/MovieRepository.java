package de.htwberlin.webtech.webtech.Repository;

import de.htwberlin.webtech.webtech.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Standard CRUD-Operationen für Filme
}
