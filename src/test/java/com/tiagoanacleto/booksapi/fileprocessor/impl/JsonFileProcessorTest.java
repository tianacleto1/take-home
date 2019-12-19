package com.tiagoanacleto.booksapi.fileprocessor.impl;

import com.tiagoanacleto.booksapi.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class JsonFileProcessorTest {

    private static final String FILE_PATH = "src/test/resources/jsonFileTestStub.json";
    private JsonFileProcessor jsonFileProcessor;
    private File file;

    @Before
    public void setup() {
        jsonFileProcessor = new JsonFileProcessor();
        file = new File(FILE_PATH);
    }

    @Test
    public void whenFileIsReaded_ThenItShouldReturnBooksMapTest() {
        Map<Long, Book> bookMock = jsonFileProcessor.processFile(file.getAbsolutePath());

        Assert.assertEquals("C Programming Language", bookMock.get(131103628L).getTitle());
    }
}
