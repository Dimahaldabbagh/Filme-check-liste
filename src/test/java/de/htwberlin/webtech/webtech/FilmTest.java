package de.htwberlin.webtech.webtech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmTest {

    @Test
    void testToString() {
        Film film = new Film(42L, "Matrix", "Sci-Fi", 5);
        String expected = "Film{id=42, titel='Matrix', genre='Sci-Fi', bewertung=5}";
        assertEquals(expected, film.toString());
    }
}