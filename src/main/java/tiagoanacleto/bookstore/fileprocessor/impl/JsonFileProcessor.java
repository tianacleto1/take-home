package tiagoanacleto.bookstore.fileprocessor.impl;

import lombok.extern.log4j.Log4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tiagoanacleto.bookstore.fileprocessor.FileProcessor;
import tiagoanacleto.bookstore.model.Book;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class implements the FileProcessor interface
 * with json file type processing behavior
 */
@Log4j
public class JsonFileProcessor implements FileProcessor {

    @Override
    public Map<Long, Book> processFile(String path) {
        JSONParser jsonParser = new JSONParser();
        Map books = new HashMap<>();

        try (Reader fileReader = new FileReader(path)) {
            JSONArray lines = (JSONArray) jsonParser.parse(fileReader);

            books = (Map<Long, Book>) lines.stream()
                                .collect(Collectors.toMap(
                                            c -> (Long) c.get("ISBN"), mapToBookObj));

        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }

        return books;
    }

    private Function<JSONObject, Book> mapToBookObj = json -> {
        Book book = new Book();
        book.setTitle((String) json.get("Title"));
        book.setDescription((String) json.get("Description"));
        book.setIsbn((Long) json.get("ISBN"));
        book.setAuthor((String) json.get("Author"));
        book.setGenre((String) json.get("Genre"));
        book.setPages(String.valueOf((Long) json.get("Pages")));
        book.setAgeRange((String) json.get("Age Range"));
        book.setPrice((String) json.get("Price"));
        book.setQty((Long) json.get("Qty."));

        return book;
    };
}
