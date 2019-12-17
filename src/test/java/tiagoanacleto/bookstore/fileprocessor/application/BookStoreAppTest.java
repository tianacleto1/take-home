package tiagoanacleto.bookstore.fileprocessor.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import tiagoanacleto.bookstore.application.BookStoreApp;
import tiagoanacleto.bookstore.fileprocessor.FileProcessor;
import tiagoanacleto.bookstore.fileprocessor.factory.FileProcessorFactory;
import tiagoanacleto.bookstore.model.Book;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
public class BookStoreAppTest {

    @Mock
    private FileProcessor fileProcessor;

    private FileProcessorFactory fileProcessorFactoryMock;

    @InjectMocks
    private BookStoreApp bookStoreApp;

    @Before
    public void setup() {
        bookStoreApp = new BookStoreApp();
        MockitoAnnotations.initMocks(this);
        fileProcessorFactoryMock = PowerMockito.mock(FileProcessorFactory.class);
    }

    @Test
    public void startAppTest() {
        Mockito.when(fileProcessor.processFile(anyString())).thenReturn(prepareStubForTest());

        bookStoreApp.startApp(new String[] {"test.csv", "test.txt", "test.json"});
    }

    public Map<Long, Book> prepareStubForTest() {
        Map<Long, Book> booksMock = new HashMap<>();

        Book bookMock = new Book();
        bookMock.setIsbn(23131103628L);
        bookMock.setTitle("Test Book");
        bookMock.setDescription("This is Just a Test");
        bookMock.setAuthor("Albert James");
        bookMock.setGenre("Test");
        bookMock.setPages("200");
        bookMock.setAgeRange("test");
        bookMock.setPrice("$30");
        bookMock.setQty(12L);

        booksMock.put(23131103628L, bookMock);

        return booksMock;
    }
}
