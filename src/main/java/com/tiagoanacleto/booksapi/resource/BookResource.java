package com.tiagoanacleto.booksapi.resource;

import com.tiagoanacleto.booksapi.model.Book;
import com.tiagoanacleto.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<Book> updateBookQty(@PathVariable Long isbn, @RequestParam int bookQty) {
        Book updatedBook = bookService.updateBookQty(isbn, bookQty);

        return ResponseEntity.ok().body(updatedBook);
    }
}
