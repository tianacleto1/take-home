package tiagoanacleto.bookstore.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EqualsAndHashCode.Include
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
