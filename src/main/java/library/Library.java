package library;

import org.joda.time.LocalDate;

public class Library {
    private final ItemsList<Book> books;
    private ItemsList<Client> clients;
    private final ItemsList<Author> authors;

    public ItemsList<BookCopy> bookCopies;

    public ItemsList<Reader> readers;
    public ItemsList<BookLogsRecord> bookLogsRecords;

    public Library() {
        authors = new ItemsList<>("AUTHORS LIST");
        authors.add(new Author("Ray", "Bradbury"));
        authors.add(new Author("Isaak", "Asimov"));
        authors.add(new Author("Robert", "Heinlein"));

        books = new ItemsList<>("BOOKS LIST");
        books.add(new Book("Stranger in a Stranger Land", authors.getItem(2), Genre.SCIENCE_FICTION, 1961));
        books.add(new Book("Foundation", authors.getItem(1), Genre.SCIENCE_FICTION, 1950));
        books.add(new Book("Fahrenheit 451", authors.getItem(0), Genre.SCIENCE_FICTION, 1951));

        bookCopies = new ItemsList<>();
        bookCopies.add(new BookCopy(books.getItem(1), 1));
        bookCopies.add(new BookCopy(books.getItem(1), 2));
        bookCopies.add(new BookCopy(books.getItem(2), 1));

        readers = new ItemsList<>("READER LIST");
        readers.add(new Reader("Joe", "Black", new LocalDate(1980, 1, 5)));
        readers.add(new Reader("Darth", "Vader", new LocalDate(1900, 10, 25)));
        readers.add(new Reader("Arthur", "King", new LocalDate(1990, 12, 14)));

        bookLogsRecords = new ItemsList<>("LOG RECORDS");
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

    public ItemsList<Reader> getReaders() {
        return readers;
    }
    public ItemsList<BookLogsRecord> getBookLogsRecords() {
        return bookLogsRecords;
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

    public void linkingBookLogsRecords() {
        try {
            getBookLogsRecords()
                    .getListOf()
                    .forEach(bookLogsRecord -> {
                        if (bookLogsRecord.getReader() != null)
                            bookLogsRecord.setReader(getReaders().find(bookLogsRecord.getReader().getId()));
                        else
                            System.out.println("There is no reader for the book log record. Log record id: "
                                    + bookLogsRecord.getId());
                        if (bookLogsRecord.getBookCopy() != null)
                            bookLogsRecord.setBookCopy(getBookCopies().find(bookLogsRecord.getBookCopy().getId()));
                        else
                            System.out.println("There is no book copy for the book log record. Log record id: "
                                    + bookLogsRecord.getId());
                    });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }







}

