package com.tiagoanacleto.booksapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "books")
public class Book {

    @EqualsAndHashCode.Include
    @Id
    private Long isbn;

    private String title;
    private String description;
    private String author;
    private String genre;
    private String pages;
    private String ageRange;
    private String price;
    private Long qty;

    @Override
    public String toString() {
        return title + "," + description + "," + isbn + "," + author + "," + genre + "," +
                pages + "," + ageRange + "," + price + "," + qty;
    }
}
