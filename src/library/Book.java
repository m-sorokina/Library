package library;

import com.fasterxml.jackson.annotation.JsonProperty;
import exceptions.WrongValueException;
import org.joda.time.LocalDate;

public class Book extends Item {
    private Author author;
    private Genre genre;

    @JsonProperty("title")
    private String title;

    private int year;

    public Book(String name, Author author, Genre genre, int year) {
        super();
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.title = name;
    }

    public Book() {
        super();
        this.author = null;
        this.genre = null;
        this.year = 0;
        this.title = "";
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (checkYear(year)){
            throw new WrongValueException("Entered year is wrong: " + year);
        }
        this.year = year;
    }

    public String toString() {
        return super.toString()
                + String.format(" %s, %s, %d>",
                getAuthor(), getGenre().toString(), getYear());
    }

    private Boolean checkYear(int year) {
        return (year < 1900 || year > (new LocalDate()).getYear());
    }

}
