package com.tiagoanacleto.booksapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookSearchFilter {

    private String title = "";
    private String author = "";
    private String genre = "";

}
