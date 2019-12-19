package com.tiagoanacleto.booksapi.fileprocessor.impl;

import com.tiagoanacleto.booksapi.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class TXTTabSeparetedFileProcessorTest {

    private static final String FILE_PATH = "src/test/resources/txtFileTestStub.txt";
    private TXTTabSeparatedFileProcessor txtFileProcessor;
    private File file;

    @Before
    public void setup() {
        txtFileProcessor = new TXTTabSeparatedFileProcessor();
        file = new File(FILE_PATH);
    }

    @Test
    public void whenFileIsReaded_ThenItShouldReturnBooksMapTest() {
        Map<Long, Book> bookMock;
        bookMock = txtFileProcessor.processFile(file.getAbsolutePath());

        Assert.assertEquals("The Dutch House", bookMock.get(9780062963673L).getTitle());
    }
}
