package com.tiagoanacleto.booksapi.service;

import com.tiagoanacleto.booksapi.model.Book;
import com.tiagoanacleto.booksapi.repository.BookRepository;
import com.tiagoanacleto.booksapi.service.exception.BookNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private static List<Book> booksListMock;
    private static Book bookMock;

    static {
        bookMock = new Book();
        bookMock.setIsbn(123456L);
        bookMock.setTitle("Book Test");
        bookMock.setDescription("Description Test");
        bookMock.setAuthor("Test Author");
        bookMock.setGenre("Genre Test");
        bookMock.setPages("80");
        bookMock.setAgeRange("test");
        bookMock.setPrice("$30");
        bookMock.setQty(10);

        booksListMock = singletonList(bookMock);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBooksTest() {
        when(bookRepositoryMock.findAll()).thenReturn(booksListMock);

        assertEquals("Book Test", bookService.getBooks().get(0).getTitle());
    }

    @Test
    public void whenBookToBeUpdatedFoundOnDB_thenItShouldBeUpdatedItsQtyTest() {
        when(bookRepositoryMock.findBookByIsbn(any())).thenReturn(Optional.of(bookMock));
        when(bookRepositoryMock.save(any())).thenReturn(bookMock);

        Book updated = bookService.updateBookQty(123456L, 10);

        // qty before update was 10
        assertEquals(20, updated.getQty());
    }

    @Test
    public void whenBookToBeUpdatedNotFount_thenItShouldThrowBookNotFoundExceptionTest() {
        when(bookRepositoryMock.findBookByIsbn(any())).thenReturn(Optional.empty());

        exceptionRule.expect(BookNotFoundException.class);
        exceptionRule.expectMessage("Book not found on database!");

        bookService.updateBookQty(123456L, 10);
    }

    @Test
    public void saveBooksTest() {
        when(bookRepositoryMock.saveAll(any())).thenReturn(booksListMock);

        bookService.saveBooks(new HashMap<Long, Book>());
    }
}
