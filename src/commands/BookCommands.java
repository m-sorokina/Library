package commands;

import exceptions.CommandCancelException;
import exceptions.WrongValueException;
import library.*;
import menu.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class BookCommands extends MenuCommands<Book> {

    public BookCommands() {
        super();
        add("Find all books by author's name", this::findAllBooksByAuthor);
        add("Print author's list", () -> new AuthorCommands().printAll());
        add("Print genre list", Genre::genreToPrint);
        add("Add book copies", this::addBookCopies);
        add("Return to previous menu", 0, null);
    }


    @Override
    public ItemsList<Book> getList() {
        return Main.lib.getBooks();
    }

    @Override
    public Boolean addItem() {
        try {
            Book book = new Book();
            book.setName(enterString("Enter book title: "));
            book.setAuthor(Main.lib.getAuthors().find(enterInt("Enter author's id: ")));
            book.setGenre(Genre.toGenre(enterInt("Enter genre id: ")));
            book.setYear(enterInt("Enter year: "));
            getList().add(book);
            IntStream stream = IntStream.range(1, enterInt("Enter number of book's copies: ") + 1);
            stream.forEach(number -> Main.lib.getBookCopies().add(new BookCopy(book, number)));
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        } catch (CommandCancelException ignored) {
        }
        return true;
    }

    public Boolean editItem() {
        try {
            Book book = getList().find(enterInt("Enter book id: "));
            if (book == null) {
                System.out.println("Id was not found");
                return true;
            }
            System.out.println(book);
            book.setName(updateString("Enter book title: ", book.getName()));
            book.setAuthor(Main.lib.getAuthors().find(updateInt("Enter author's id: ", book.getAuthor().getId())));
            book.setGenre(Genre.toGenre(updateInt("Enter genre id: ", book.getGenre().ordinal())));
            book.setYear(updateInt("Enter year: ", book.getYear()));
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Boolean findAllBooksByAuthor() {
        Author author = Main.lib.getAuthors().find(enterInt("Enter author's id: "));
        println(getList().getListOf().stream()
                .filter(book -> book.getAuthor() == author)
                .toList());
        return true;
    }

    public Boolean addBookCopies() {
        Book book = getList().find(enterInt("Enter book id: "));
        if (book == null) {
            System.out.println("Id was not found");
            return true;
        }
        System.out.println(book);
        int bookCopiesMaxNumber = 1;
        if (!Main.lib.getBookCopies().getListOf().isEmpty()) {
            ArrayList<Integer> array = new ArrayList<>();
            for (BookCopy bookCopy : Main.lib.getBookCopies().getListOf()) {
                if (bookCopy.getBook().getId() == book.getId()) {
                    array.add(bookCopy.getCopyNumber());
                }
            }
            if (!array.isEmpty()) bookCopiesMaxNumber = Collections.max(array) + 1;
        }
        IntStream stream = IntStream.range(bookCopiesMaxNumber,
                (bookCopiesMaxNumber + enterInt("Enter number of book's copies: ")));
        stream.forEach(number -> Main.lib.getBookCopies().add(new BookCopy(book, number)));
        return true;
    }
}
