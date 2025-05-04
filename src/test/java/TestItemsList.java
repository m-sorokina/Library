import library.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestItemsList {

    @Test
    public void testAdd() {
        ItemsList<Book> books = new ItemsList<>();
        Book book = new Book("Book1", new Author("Name", "Surname"), Genre.FANTASY, 2020);
        ItemsList<Client> clients;
        ItemsList<Author> authors;

        ItemsList<BookCopy> bookCopies;

        ItemsList<Reader> readers;
        ItemsList<BookLogsRecord> bookLogsRecords;

        books.add(book);

        Assertions.assertEquals(1, books.getListOf().size(), "List should contain 1 book");
        Assertions.assertTrue(books.getListOf().contains(book), "List should contain the added book");
    }
}
