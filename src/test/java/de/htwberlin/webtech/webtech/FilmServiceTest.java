package de.htwberlin.webtech.webtech;

import de.htwberlin.webtech.webtech.Service.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {
    @Mock
    private FilmRepository repository;

    @InjectMocks
    private FilmService service;

    @Test
    void findById_returnsFilm_whenExists() {
        Film film = new Film(1L, "Titel", "Genre", 5);
        when(repository.findById(1L)).thenReturn(Optional.of(film));

        Film result = service.findById(1L);

        assertThat(result).isEqualTo(film);
    }

    @Test
    void updateFilm_updatesExistingFilm() {
        Film existing = new Film(1L, "Alt", "AltGenre", 2);
        Film updated = new Film(null, "Neu", "NeuGenre", 4);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Film.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Film result = service.update(1L, updated);

        assertThat(result.getTitel()).isEqualTo("Neu");
        assertThat(result.getGenre()).isEqualTo("NeuGenre");
        assertThat(result.getBewertung()).isEqualTo(4);
        verify(repository).save(existing);
    }

    @Test
    void search_returnsByTitelAndGenre_whenBothProvided() {
        Film film = new Film(1L, "Titel", "Genre", 1);
        when(repository.findByTitelContainingIgnoreCaseAndGenreContainingIgnoreCase("titel", "genre"))
                .thenReturn(Collections.singletonList(film));

        List<Film> result = service.search("titel", "genre");

        assertThat(result).containsExactly(film);
    }

    @Test
    void search_returnsByTitel_whenOnlyTitel() {
        Film film = new Film(1L, "Titel", "Genre", 1);
        when(repository.findByTitelContainingIgnoreCase("titel"))
                .thenReturn(Collections.singletonList(film));

        List<Film> result = service.search("titel", null);

        assertThat(result).containsExactly(film);
    }

    @Test
    void search_returnsByGenre_whenOnlyGenre() {
        Film film = new Film(1L, "Titel", "Genre", 1);
        when(repository.findByGenreContainingIgnoreCase("genre"))
                .thenReturn(Collections.singletonList(film));

        List<Film> result = service.search(null, "genre");

        assertThat(result).containsExactly(film);
    }

    @Test
    void search_returnsAll_whenNoParams() {
        Film film = new Film(1L, "Titel", "Genre", 1);
        when(repository.findAll()).thenReturn(Collections.singletonList(film));

        List<Film> result = service.search(null, null);

        assertThat(result).containsExactly(film);
    }
}
