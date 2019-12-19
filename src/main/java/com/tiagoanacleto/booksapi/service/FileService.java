package com.tiagoanacleto.booksapi.service;

import com.tiagoanacleto.booksapi.fileprocessor.FileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.factory.FileProcessorFactory;
import com.tiagoanacleto.booksapi.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private BookService bookService;

    public void processAndSaveFiles(MultipartFile[] files) throws IOException {
        Map<Long, Book> bookMap = new HashMap<>();

        for (MultipartFile filePath : files) {
            if (!filePath.getName().isEmpty()) {
                FileProcessor fileProcessor = FileProcessorFactory.getFileProcessor(filePath.getOriginalFilename());
                Map<Long, Book> lines = fileProcessor.processFile(filePath.getResource().getFile().getPath());

                lines.values()
                        .forEach(b -> {
                            bookMap.computeIfPresent(b.getIsbn(), (k, v) -> {
                                v.setQty(v.getQty() + b.getQty());

                                return v;
                            });

                            bookMap.putIfAbsent(b.getIsbn(), b);
                        });
            }
        }

        saveBooks(bookMap);
    }

    private void saveBooks(Map<Long, Book> booksMap) {
        bookService.saveBooks(booksMap);
    }
}
