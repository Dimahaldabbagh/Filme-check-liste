package de.htwberlin.webtech.webtech;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;
    private String genre;
    private int bewertung;

    public Film() {}

    public Film(Long id, String titel, String genre, int bewertung) {
        this.id = id;
        this.titel = titel;
        this.genre = genre;
        this.bewertung = bewertung;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getBewertung() { return bewertung; }
    public void setBewertung(int bewertung) { this.bewertung = bewertung; }
}
