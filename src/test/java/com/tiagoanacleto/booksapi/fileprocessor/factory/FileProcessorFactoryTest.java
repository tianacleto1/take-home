package com.tiagoanacleto.booksapi.fileprocessor.factory;

import com.tiagoanacleto.booksapi.fileprocessor.exception.FileExtensionNotRecognizedException;
import com.tiagoanacleto.booksapi.fileprocessor.impl.CSVFileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.impl.JsonFileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.impl.TXTTabSeparatedFileProcessor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertSame;

public class FileProcessorFactoryTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenTheFileExtensionIsTxt_thenItShouldReturnTxtTabSeparetadeFileProcessorObjectInstanceTest() {
        assertSame(TXTTabSeparatedFileProcessor.class, FileProcessorFactory.getFileProcessor("file.txt").getClass());
    }

    @Test
    public void whenTheFileExtensionIsCsv_thenItShouldReturnCsvFileProcessorObjectInstanceTest() {

        assertSame(CSVFileProcessor.class, FileProcessorFactory.getFileProcessor("file.csv").getClass());
    }

    @Test
    public void whenTheFileExtensionIsJson_thenItShouldReturnJsonFileProcessorObjectInstanceTest() {

        assertSame(JsonFileProcessor.class, FileProcessorFactory.getFileProcessor("file.json").getClass());
    }

    @Test
    public void whenTheFileExtensionIsNotRecognized_thenItShouldThrowsFileExtensionNotRecognizedExceptionTest() {
        exceptionRule.expect(FileExtensionNotRecognizedException.class);
        exceptionRule.expectMessage("File Extension empty or not recognised!");

        assertSame(JsonFileProcessor.class, FileProcessorFactory.getFileProcessor("file.xml").getClass());
    }

}
