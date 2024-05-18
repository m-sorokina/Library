package library;

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

    public String toString(){
        return String.format("%d ", getCopyNumber());
    }

}
