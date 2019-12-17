package tiagoanacleto.bookstore.fileprocessor.exception;

public class FileExtensionNotRecognizedException extends RuntimeException {
    public FileExtensionNotRecognizedException(String message) {
        super(message);
    }
}
