package com.tiagoanacleto.booksapi.exception;

import com.tiagoanacleto.booksapi.fileprocessor.exception.FileExtensionNotRecognizedException;
import com.tiagoanacleto.booksapi.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.Collections.singletonList;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @org.springframework.web.bind.annotation.ExceptionHandler({ BookNotFoundException.class })
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex) {
        String userMessage = messageSource.getMessage("book.not-found", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();

        List<Error> errors = singletonList(new Error(userMessage, devMessage));

        return ResponseEntity.badRequest().body(errors);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ FileExtensionNotRecognizedException.class })
    public ResponseEntity<Object> handleBookNotFoundException(FileExtensionNotRecognizedException ex) {
        String userMessage = messageSource.getMessage("file.extension-not-recognized", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();

        List<Error> errors = singletonList(new Error(userMessage, devMessage));

        return ResponseEntity.badRequest().body(errors);
    }

    public static class Error {
        private String userMessage;
        private String devMessage;

        public Error(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDevMessage() {
            return devMessage;
        }
    }
}
