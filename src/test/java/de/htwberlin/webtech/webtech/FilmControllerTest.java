package de.htwberlin.webtech.webtech;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import de.htwberlin.webtech.webtech.Controller.FilmController;
import de.htwberlin.webtech.webtech.Service.FilmService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
@Import(FilmControllerTest.MockConfig.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmService service;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        FilmService filmService() {
            return Mockito.mock(FilmService.class);
        }
    }

    @Test
    void testGetFilme() throws Exception {
        Film f1 = new Film(1L, "Matrix", "Sci-Fi", 5);
        Film f2 = new Film(2L, "Avatar", "Fantasy", 4);
        Mockito.when(service.findAll()).thenReturn(List.of(f1, f2));

        String expected = objectMapper.writeValueAsString(List.of(f1, f2));

        mockMvc.perform(get("/api/filme"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(expected)));
    }
}