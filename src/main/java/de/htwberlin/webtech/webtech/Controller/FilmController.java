package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Film;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

    @RestController
    @RequestMapping("/api/filme")
    @CrossOrigin(origins = "*") // erlaubt Zugriff vom Frontend(Vue.js)
    public class FilmController {

        @GetMapping
        public List<Film> getFilme() {
            return Arrays.asList(
                    new Film(1L, "Inception", "Sci-Fi", 5),
                    new Film(2L, "The Matrix", "Action", 4),
                    new Film(3L, "Titanic", "Drama", 3)
            );
        }
    }



