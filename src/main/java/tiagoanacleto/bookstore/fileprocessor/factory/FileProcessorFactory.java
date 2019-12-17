package tiagoanacleto.bookstore.fileprocessor.factory;

import tiagoanacleto.bookstore.fileprocessor.FileProcessor;
import tiagoanacleto.bookstore.fileprocessor.exception.FileExtensionNotRecognizedException;
import tiagoanacleto.bookstore.fileprocessor.impl.CSVFileProcessor;
import tiagoanacleto.bookstore.fileprocessor.impl.JsonFileProcessor;
import tiagoanacleto.bookstore.fileprocessor.impl.TXTTabSeparatedFileProcessor;

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
                throw new FileExtensionNotRecognizedException("File Extension empty or not recognised!");

        }
    }
}
