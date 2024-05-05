package commands;

import library.Author;
import library.Book;
import library.Genre;
import library.ItemsList;
import menu.Main;

public class BookCommands extends MenuCommands<Book> {

    public BookCommands() {
        super();
        add("Find all books by author's name", this::findAllBooksByAuthor);
        add("Print author's list", () -> new AuthorCommands().printAll());
        add("Print genre list", Genre::genreToPrint);
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
        }
        catch(RuntimeException ignored){
        }
        return true;
    }

    public Boolean editItem() {
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
        return true;
    }

    public Boolean findAllBooksByAuthor() {
        Author author = Main.lib.getAuthors().find(enterInt("Enter author's id: "));
        println(getList().getListOf().stream()
                .filter(book -> book.getAuthor() == author)
                .toList());
        return true;
    }
}
