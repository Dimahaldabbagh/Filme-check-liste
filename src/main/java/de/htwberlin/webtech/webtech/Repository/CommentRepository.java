package de.htwberlin.webtech.webtech.Repository;

import de.htwberlin.webtech.webtech.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Standard CRUD für Kommentare
    List<Comment> findByMovieId(Long movieId);
}
