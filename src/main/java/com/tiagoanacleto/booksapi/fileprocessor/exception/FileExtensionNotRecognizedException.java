package com.tiagoanacleto.booksapi.fileprocessor.exception;

public class FileExtensionNotRecognizedException extends RuntimeException {

    public FileExtensionNotRecognizedException(String message) {
        super(message);
    }
}
