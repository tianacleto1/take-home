package com.tiagoanacleto.booksapi.fileprocessor.impl;

import com.tiagoanacleto.booksapi.fileprocessor.FileProcessor;
import com.tiagoanacleto.booksapi.model.Book;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class implements the FileProcessor interface
 * with txt file type processing behavior
 */
@Log4j2
public class TXTTabSeparatedFileProcessor implements FileProcessor {

    private static final String SPECIAL_CHARS = "\t";

    @Override
    public Map<Long, Book> processFile(String path) {
        Map<Long, Book> books = new HashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            lines.skip(1)
                    .filter(l -> !l.isEmpty()).sorted()
                    .forEach(l -> {
                        Book book = mapToBookObj(l.split(SPECIAL_CHARS));

                        books.put(book.getIsbn(), book);
                    });
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return books;
    }

    private Book mapToBookObj(String[] str) {
        Book book = new Book();
        book.setTitle(str[0]);
        book.setDescription(str[1]);
        book.setIsbn(Long.valueOf(str[2].trim()));
        book.setAuthor(str[3]);
        book.setGenre(str[4]);
        book.setPages(str[5]);
        book.setAgeRange(str[6]);
        book.setPrice(str[7]);
        book.setQty(Long.valueOf(str[8].trim()));

        return book;
    }
}

