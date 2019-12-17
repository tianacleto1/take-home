package tiagoanacleto.bookstore.fileprocessor.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tiagoanacleto.bookstore.model.Book;

import java.io.File;
import java.util.Map;

public class CSVFileProcessorTest {

    private static final String FILE_PATH = "src/test/resources/csvFileTestStub.csv";
    private CSVFileProcessor csvFileProcessor;
    private File file;

    @Before
    public void setup() {
        csvFileProcessor = new CSVFileProcessor();
        file = new File(FILE_PATH);
    }

    @Test
    public void whenFileIsReaded_ThenItShouldReturnBooksMapTest() {
        Map<Long, Book> bookMock = csvFileProcessor.processFile(file.getAbsolutePath());

        Assert.assertEquals("This is Just a Test", bookMock.get(23131103628L).getTitle());
    }
}
