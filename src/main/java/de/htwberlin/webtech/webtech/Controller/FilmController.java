package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Film;
import de.htwberlin.webtech.webtech.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/filme")
    @CrossOrigin(origins = "*") // erlaubt Zugriff vom Frontend(Vue.js)
    public class FilmController {

        @Autowired
        FilmService service;

        @GetMapping
        public List<Film> getFilme() {
            return service.findAll();
        }

        @PutMapping("/{id}")
        public Film updateFilm(@PathVariable Long id, @RequestBody Film film) {
            return service.update(id, film);
        }

        @DeleteMapping("/{id}")
        public boolean deleteFilm(@PathVariable Long id) {
            return service.delete(id);
        }

        @GetMapping("/search")
        public List<Film> search(@RequestParam(required = false) String titel,
                                 @RequestParam(required = false) String genre) {
            return service.search(titel, genre);
        }

    }



