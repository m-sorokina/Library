package commands;

import exceptions.WrongValueException;
import library.*;
import menu.Main;

import java.util.stream.IntStream;

public class BookCommands extends MenuCommands<Book> {

    public BookCommands() {
        super();
        add("Find all books by author's id", this::findAllBooksByAuthor);
        add("Print author's list", () -> new AuthorCommands().printAll());
        add("Print genre list", Genre::genreToPrint);
        add("Book copies entry", () -> {
            new BookCopiesCommands().run();
            return true;
        });
        setTitle("Books entry:");
    }


    @Override
    public ItemsList<Book> getList() {
        return Main.lib.getBooks();
    }

    @Override
    public void addItem() {
        try {
            Book book = new Book();
            book.setName(enterString("Enter book title: "));
            book.setAuthor(find(Main.lib.getAuthors(), "Enter author's id: "));
            book.setGenre(Genre.toGenre(enterInt("Enter genre id: ")));
            book.setYear(enterInt("Enter year: "));
            getList().add(book);
            IntStream stream = IntStream.range(1, enterInt("Enter number of book's copies: ") + 1);
            stream.forEach(number -> Main.lib.getBookCopies().add(new BookCopy(book, number)));
            System.out.println(book);
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editItem(Book book) {
        try {
            book.setName(updateString("Enter book title: ", book.getName()));
            book.setAuthor(find(Main.lib.getAuthors(), updateInt("Enter author's id: ", book.getAuthor().getId())));
            book.setGenre(Genre.toGenre(updateInt("Enter genre id: ", book.getGenre().ordinal())));
            book.setYear(updateInt("Enter year: ", book.getYear()));
            System.out.println(book);
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean findAllBooksByAuthor() {
        try {
            Author author = find(Main.lib.getAuthors(),"Enter author's id: ");
            println(getList().getListOf().stream()
                    .filter(book -> book.getAuthor() == author)
                    .toList());
        } catch (Exception ignored) {
        }
        return true;
    }

    public void removeItem(Book book) {
        int bookCopies = book.getCopiesNumbers();
        if (bookCopies > 0) {
            System.out.println("Book can't be removed. Some books copies are still available in the library");
            String result = enterString("Do you want to remove book with all copies (Y/N): ");
            if (result.trim().equalsIgnoreCase("Y")) {
                Main.lib.getBookCopies().getListOf()
                        .removeAll(book.findBookCopies());
                System.out.println(book);
                getList().remove(book);
            }
        } else {
            getList().remove(book);
        }
    }

}
