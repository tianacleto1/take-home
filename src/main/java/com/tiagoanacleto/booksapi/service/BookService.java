package com.tiagoanacleto.booksapi.service;

import com.tiagoanacleto.booksapi.model.Book;
import com.tiagoanacleto.booksapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBooks(Map<Long, Book> booksMap) {
        bookRepository.saveAll(booksMap.values());
    }
}
