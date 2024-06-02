package commands;

import exceptions.WrongValueException;
import library.Book;
import library.BookCopy;
import library.ItemsList;
import menu.Main;
import menu.Menu;

import java.util.stream.IntStream;

public class BookCopiesCommands extends Menu {

    public BookCopiesCommands() {
        super();
        add("Print all", 1, this::printAll);
        add("Add item(s)", 2, this::addItem);
        add("Remove item", 3, this::removeItem);
        add("Print book copies numbers", 4, this::printBookCopies);
        setTitle("Book copies entry:");
    }

    public ItemsList<BookCopy> getList() {
        return Main.lib.getBookCopies();
    }

    public Boolean printAll() {
        new BookCommands().printAll();
        return true;
    }

    public Boolean addItem() {
        Book book = find(Main.lib.getBooks(), enterInt("Enter book id: "));
        System.out.println(book);
        int bookCopiesMaxNumber = book.findBookCopies().stream()
                .mapToInt(BookCopy::getCopyNumber)
                .max().orElse((book.getId() * 1000)) + 1;
        IntStream stream = IntStream.range(bookCopiesMaxNumber,
                (bookCopiesMaxNumber + enterInt("Enter quantity of added book's copies: ")));
        stream.forEach(number -> getList().add(new BookCopy(book, number)));
        System.out.println(book);
        return true;
    }

    public Boolean removeItem() {
        try {
            Book book = find(Main.lib.getBooks(), enterInt("Enter book id: "));
            System.out.println(book);
            Integer bookCopyNumber = enterInt("Enter book copy number: ");
            book.findBookCopies().stream()
                    .filter(bookCopy ->
                            bookCopy.getCopyNumber() == bookCopyNumber)
                    .findAny()
                    .orElseThrow(() ->
                            new WrongValueException("Book copy with entered number was not found"));
            System.out.println(book);
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Boolean printBookCopies() {
        Book book = find(Main.lib.getBooks(), enterInt("Enter book id: "));
        System.out.println(book);
        System.out.print("Available copy numbers: ");
        book.findBookCopies().forEach(System.out::print);
        System.out.println();
        return true;
    }

}
