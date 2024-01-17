package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
public class BookApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {baseUrl = "http://localhost:"+randomServerPort+"/";}

    @AfterEach
    void tearDown() {bookRepository.deleteAll();}
    
    @Test
    @DisplayName("should return list of books when books endpoint is accessed")
    void shouldReturnListOfBooksWhenBooksEndpointIsAccessed() {
        Book book1 = new Book("Refactoring Legacy Code");
        Book book2 = new Book("TDD");

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl + "/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
        }).getBody();

        assertEquals(2, books.size());

        assertNotNull(books.get(0).getId());
        assertNotNull("Refactoring Legacy Code", books.get(0).getTitle());

        assertNotNull(books.get(1).getId());
        assertNotNull("TDD", books.get(1).getTitle());
    }

}
