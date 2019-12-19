package com.tiagoanacleto.booksapi.service;

import com.tiagoanacleto.booksapi.model.Book;
import com.tiagoanacleto.booksapi.repository.BookRepository;
import com.tiagoanacleto.booksapi.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * This method retrieve a list of books from DB
     * sort and return it.
     *
     * @return List<Book>
     */
    public List<Book> getBooks() {
        return bookRepository.findAll()
                             .stream()
                             .sorted(Comparator.comparing(Book::getGenre)
                             .thenComparing(Book::getAuthor))
                             .collect(Collectors.toList());
    }

    public Book updateBookQty(long isbn, int bookQty) {
         Book bookFound = bookRepository.findBookByIsbn(isbn).orElse(null);

         if (bookFound == null) {
             throw new BookNotFoundException();
         }

         bookFound.setIsbn(isbn);
         bookFound.setQty(bookFound.getQty() + bookQty);

         return bookRepository.save(bookFound);
    }

    public void saveBooks(Map<Long, Book> booksMap) {
        bookRepository.saveAll(booksMap.values());
    }
}
