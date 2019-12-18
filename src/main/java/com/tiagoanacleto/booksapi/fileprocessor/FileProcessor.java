package com.tiagoanacleto.booksapi.fileprocessor;

import com.tiagoanacleto.booksapi.model.Book;

import java.util.Map;

/**
 * This interface is used to abstract the process
 * file behavior
 */
public interface FileProcessor {

    Map<Long, Book> processFile(String path);
}
