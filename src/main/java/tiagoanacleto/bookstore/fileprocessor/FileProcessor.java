package tiagoanacleto.bookstore.fileprocessor;

import tiagoanacleto.bookstore.model.Book;

import java.util.Map;

/**
 * This interface is used to abstract the process
 * file behavior
 */
public interface FileProcessor {

    Map<Long, Book> processFile(String path);
}
