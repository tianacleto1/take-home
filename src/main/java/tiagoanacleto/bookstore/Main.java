package tiagoanacleto.bookstore;

import tiagoanacleto.bookstore.application.BookStoreApp;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Error: Please provide the fully qualified path to the \"3\" files separated by space as command line arguments!");
            System.exit(1);
        }

        BookStoreApp bookStoreApp = new BookStoreApp();
        bookStoreApp.startApp(args);
    }
}
