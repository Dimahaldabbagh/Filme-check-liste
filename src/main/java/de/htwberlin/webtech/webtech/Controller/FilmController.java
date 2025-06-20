package de.htwberlin.webtech.webtech.Controller;

import Service.FilmService;
import de.htwberlin.webtech.webtech.Film;
import org.springframework.beans.factory.annotation.Autowired;
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

        @Autowired
        FilmService service;

        @GetMapping
        public List<Film> getFilme() {
            return service.findAll();
        }
    }



