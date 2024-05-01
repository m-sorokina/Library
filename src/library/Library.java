package library;

public class Library {
//    private ItemsList<Book> books;
//    private ItemsList<Client> clients;
    private ItemsList<Author> authors;

    public Library() {
        authors = new ItemsList<>();
        authors.add(new Author("Ray", "Bradbury"));
        authors.add(new Author("Isaak", "Asimov"));
        authors.add(new Author("Robert", "Heinlein"));
//        books = new ItemsList<>();
//        books.add(new Book("Stranger in a Stranger Land", authors.getItem(2), Genre.SCIENCE_FICTION, 1961));
//        books.add(new Book("Foundation", authors.getItem(1), Genre.SCIENCE_FICTION, 1950));
//        books.add(new Book("Fahrenheit 451", authors.getItem(0), Genre.SCIENCE_FICTION, 1951));
//        clients = new ItemsList<>();
    }

    public ItemsList<Author> getAuthors(){
        return authors;
    }

}
