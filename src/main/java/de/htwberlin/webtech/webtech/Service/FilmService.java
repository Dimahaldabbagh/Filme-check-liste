package de.htwberlin.webtech.webtech.Service;

import de.htwberlin.webtech.webtech.Film;
import de.htwberlin.webtech.webtech.FilmRepository;
import org.springframework.stereotype.Service;
import java.util.List;


    @Service
    public class FilmService {

        private final FilmRepository repository;

        public FilmService(FilmRepository repository) {
            this.repository = repository;
        }

        public Film create(Film film) {
            return repository.save(film);
        }

        public List<Film> findAll() {
            return repository.findAll();
        }

        public Film findById(Long id) {
            return repository.findById(id).orElse(null);
        }
    }

