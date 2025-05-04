package commands;

import library.Book;
import library.ItemsList;
import menu.Main;
import menu.Menu;

public class FindBookCommands extends Menu {
    public FindBookCommands() {
        super();
        add("Find by name", 1, new BookCommands()::findByName);
        add("Find all books by author's id", new BookCommands()::findAllBooksByAuthor);
        add("Find by book id", new BookCommands()::printItem);
        add("Print all books", new BookCommands()::printAll);
        add("Print all copy books available", this::printAllCopyBooksAvailable);
        setTitle("Book info:");
    }

    public ItemsList<Book> getList() {
        return Main.lib.getBooks();
    }

    public Boolean printAllCopyBooksAvailable() {
        try {
            getList().getListOf()
                    .forEach(book -> System.out.println(book.toStringAvailableCopies()));
            return true;
        }
        catch(Exception ignored){
        }
        return true;
    }

}
