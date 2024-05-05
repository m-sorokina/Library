package library;

public class Library {
    private final ItemsList<Book> books;
    private ItemsList<Client> clients;
    private final ItemsList<Author> authors;

    public ItemsList<BookCopy> bookCopies;

    public Library() {
        authors = new ItemsList<>();
        authors.add(new Author("Ray", "Bradbury"));
        authors.add(new Author("Isaak", "Asimov"));
        authors.add(new Author("Robert", "Heinlein"));

        books = new ItemsList<>();
        books.add(new Book("Stranger in a Stranger Land", authors.getItem(2), Genre.SCIENCE_FICTION, 1961));
        books.add(new Book("Foundation", authors.getItem(1), Genre.SCIENCE_FICTION, 1950));
        books.add(new Book("Fahrenheit 451", authors.getItem(0), Genre.SCIENCE_FICTION, 1951));

        bookCopies = new ItemsList<>();
        bookCopies.add(new BookCopy(books.getItem(1), 1));
        bookCopies.add(new BookCopy(books.getItem(1), 2));
        bookCopies.add(new BookCopy(books.getItem(2), 1));

        //        clients = new ItemsList<>();
    }

    public ItemsList<Author> getAuthors() {
        return authors;
    }

    public ItemsList<Book> getBooks() {
        return books;
    }

    public ItemsList<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void linkingAuthors() {
        try {
            getBooks()
                    .getListOf()
                    .forEach(book -> {
                        if (book.getAuthor() != null)
                            book.setAuthor(getAuthors().find(book.getAuthor().getId()));
                        else
                            System.out.println("The book doesn't have author. Book id: "
                                    + book.getId());
                    });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void linkingBooks() {
        try {
            getBookCopies()
                    .getListOf()
                    .forEach(bookCopy -> {
                        if (bookCopy.getBook() != null)
                            bookCopy.setBook(getBooks().find(bookCopy.getBook().getId()));
                        else
                            System.out.println("The book doesn't have original. Book name: "
                                    + bookCopy.getName());
                    });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}

