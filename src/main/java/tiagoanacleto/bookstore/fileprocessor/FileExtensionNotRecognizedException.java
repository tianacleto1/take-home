package tiagoanacleto.bookstore.fileprocessor;

public class FileExtensionNotRecognizedException extends RuntimeException {
    public FileExtensionNotRecognizedException(String message) {
        super(message);
    }
}
