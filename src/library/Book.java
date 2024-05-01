package library;

public class Book extends Item {
    private String title;
    private Author author;
    private Genre genre;

    private int year;
    public Book(String title, Author author, Genre genre, int year){
        super();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Book(){
        super();
        this.title = "";
        this.author = null;
        this.genre = null;
        this.year = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author.getFullName();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return String.valueOf(genre);
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return super.toString()
                + String.format(" %s, %s, %s, %d", getTitle(), getAuthor(), getGenre(), getYear());
    }

}
