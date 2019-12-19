package com.tiagoanacleto.booksapi.fileprocessor.factory;

import com.tiagoanacleto.booksapi.fileprocessor.FileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.exception.FileExtensionNotRecognizedException;
import com.tiagoanacleto.booksapi.fileprocessor.impl.CSVFileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.impl.JsonFileProcessor;
import com.tiagoanacleto.booksapi.fileprocessor.impl.TXTTabSeparatedFileProcessor;

import java.util.Optional;

/**
 * This class is a factory for FileProcessor
 * behavior implementation classes.
 */
public class FileProcessorFactory {

    private FileProcessorFactory() {}

    public static FileProcessor getFileProcessor(String fileExt) {
        String fileExtension = Optional.ofNullable(fileExt)
                                        .filter(f -> f.contains("."))
                                        .map(f -> f.substring(fileExt.lastIndexOf('.') + 1))
                                        .orElse("")
                                        .toLowerCase();

        switch (fileExtension) {
            case "csv":
                return new CSVFileProcessor();
            case "txt":
                return new TXTTabSeparatedFileProcessor();
            case "json":
                return new JsonFileProcessor();
            default:
                throw new FileExtensionNotRecognizedException();
        }
    }
}
