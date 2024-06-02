package library;

import menu.Menu;

public class BookCopy extends Item {

    private Book book;

    private int copyNumber;


    private Boolean copyBookStatus;


    public BookCopy(Book book, int copyNumber) {
        super(book.getName());
        this.book = book;
        this.copyNumber = copyNumber;
    }


    public BookCopy() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public Boolean getCopyBookStatus() {
        return copyBookStatus;
    }

    public BookCopy findCopyByBookAndCopyNumber(Book book, int copyNumber) {
        return book.findBookCopies().stream()
                .filter(bookCopy -> bookCopy.getCopyNumber() == copyNumber)
                .findFirst()
                .orElseGet(() -> findCopyByBookAndCopyNumber(book, Menu.enterInt("Wrong copy number, please repeat, 'Enter' to exit: ")));
    }

    public String toString() {
        return String.format("%d ", getCopyNumber());
    }

}
