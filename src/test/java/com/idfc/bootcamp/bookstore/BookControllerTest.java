package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @Test
    @DisplayName("should return success http status code")
    void shouldReturnSuccessHttpStatusCode() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("should return 5 books when fetched")
    void shouldReturn5BooksWhenFetched() throws Exception {
        Book book1 = new Book("Refactoring");
        Book book2 = new Book("TDD");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        mockMvc.perform(get("/books")).andExpect(jsonPath("$.length()").value(2));
        verify(bookRepository).findAll();
    }
    
    @Test
    @DisplayName("Should return book details as Json response")
    void shouldReturnBookDetailsAsJsonResponse() throws Exception {
        Book book1 = new Book("Refactoring");
        Book book2 = new Book("TDD");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Refactoring"))
                        .andExpect(jsonPath("$[1].title").value("TDD"));
        verify(bookRepository).findAll();
    }
}
