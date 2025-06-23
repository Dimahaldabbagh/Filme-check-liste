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

        public Film update(Long id, Film updatedFilm) {
            return repository.findById(id)
                    .map(f -> {
                        f.setTitel(updatedFilm.getTitel());
                        f.setGenre(updatedFilm.getGenre());
                        f.setBewertung(updatedFilm.getBewertung());
                        return repository.save(f);
                    })
                    .orElse(null);
        }

        public boolean delete(Long id) {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        }

        public List<Film> search(String titel, String genre) {
            if (titel != null && genre != null) {
                return repository.findByTitelContainingIgnoreCaseAndGenreContainingIgnoreCase(titel, genre);
            } else if (titel != null) {
                return repository.findByTitelContainingIgnoreCase(titel);
            } else if (genre != null) {
                return repository.findByGenreContainingIgnoreCase(genre);
            } else {
                return repository.findAll();
            }
        }

    }

