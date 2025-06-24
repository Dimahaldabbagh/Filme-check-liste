package de.htwberlin.webtech.webtech.Service;

import de.htwberlin.webtech.webtech.Model.Comment;
import de.htwberlin.webtech.webtech.Model.Movie;
import de.htwberlin.webtech.webtech.Repository.CommentRepository;
import de.htwberlin.webtech.webtech.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class MovieService {

        private final MovieRepository movieRepository;
        private final CommentRepository commentRepository;

        public MovieService(MovieRepository movieRepository, CommentRepository commentRepository) {
            this.movieRepository = movieRepository;
            this.commentRepository = commentRepository;
        }

        public List<Movie> getAllMovies() {
            return movieRepository.findAll();
        }

        public Movie getMovieById(Long id) {
            return movieRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Film", "ID", id));
        }

        public Movie createMovie(Movie movie) {
            return movieRepository.save(movie);
        }

        public Movie updateMovie(Long id, Movie updatedMovie) {
            Movie movie = getMovieById(id);
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setThumbnailUrl(updatedMovie.getThumbnailUrl());
            return movieRepository.save(movie);
        }

        public void deleteMovie(Long id) {
            Movie movie = getMovieById(id);
            movieRepository.delete(movie);
        }

        public Comment addComment(Long movieId, Comment comment) {
            Movie movie = getMovieById(movieId);
            movie.addComment(comment);
            updateMovieRating(movie);
            movieRepository.save(movie);
            return commentRepository.save(comment);
        }

        private void updateMovieRating(Movie movie) {
            List<Comment> comments = movie.getComments();
            double avg = comments.stream().mapToDouble(Comment::getRating).average().orElse(0);
            movie.setAverageRating(avg);
        }
    }

