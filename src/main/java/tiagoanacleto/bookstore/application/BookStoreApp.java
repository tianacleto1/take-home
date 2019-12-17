package tiagoanacleto.bookstore.application;

import lombok.extern.log4j.Log4j;
import tiagoanacleto.bookstore.fileprocessor.FileProcessor;
import tiagoanacleto.bookstore.fileprocessor.factory.FileProcessorFactory;
import tiagoanacleto.bookstore.model.Book;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Log4j
public class BookStoreApp {

    private static final String PATH_SAVE_FILE = "/Users/tiagosilva/Downloads/take-home-master/Examples/processed.csv";

    public void startApp(String[] args) {
        Map<Long, Book> bookMap = new HashMap<>();

        for (String filePath : args) {
            if (!filePath.isEmpty()) {
                FileProcessor fileProcessor = FileProcessorFactory.getFileProcessor(filePath);
                Map<Long, Book> lines = fileProcessor.processFile(filePath);

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

        writeToCSVFile(bookMap);
    }

    private void writeToCSVFile(Map<Long, Book> booksMap) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(PATH_SAVE_FILE))) {
            bw.write("Title,Description,ISBN,Author,Genre,Pages,Age Range,Price,Qty.");
            bw.newLine();

            List<Book> booksList = new ArrayList<>(booksMap.values());
            booksList.sort(Comparator.comparing(Book::getGenre)
                    .thenComparing(Book::getAuthor));

            for (Book b : booksList) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
