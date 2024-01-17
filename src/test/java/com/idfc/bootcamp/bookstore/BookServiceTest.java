package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

   /* @Test
    @DisplayName("should return 2 books")
    void shouldReturn2Books() {
        Book book1 = new Book("Refactoring");
        Book book2 = new Book("TDD");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        BookService bookService = new BookService(bookRepository);
        assertEquals(2,bookService.listBooks().size());
    }*/
}
