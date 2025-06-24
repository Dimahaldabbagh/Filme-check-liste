package de.htwberlin.webtech.webtech.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author; // Benutzername oder E-Mail
    private String text; // Kommentartext
    private double rating; // Bewertung von 0 bis 5

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Comment() {
    }

    public Comment(String author, String text, double rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public double getRating() {
        return rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Bewertung muss zwischen 0 und 5 liegen.");
        }
        this.rating = rating;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
