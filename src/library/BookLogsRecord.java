package library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import menu.Menu;
import org.joda.time.LocalDate;

public class BookLogsRecord extends Item{

    private Reader reader;
    private BookCopy bookCopy;
    private LocalDate dateOfAction;

    private ActionTypes action;

    public enum ActionTypes{
        TAKE,
        RETURN
    }

    public BookLogsRecord(Reader reader, BookCopy bookCopy, LocalDate dateOfAction, ActionTypes action){
        super();
        this.reader = reader;
        this.bookCopy = bookCopy;
        this.dateOfAction = dateOfAction;
        this.action = action;
    }

    public BookLogsRecord(){
        super();
        this.reader = null;
        this.bookCopy = null;
        this.dateOfAction = null;
        this.action = null;
    }

    public Reader getReader() {
        return reader;
    }
    @JsonIgnore
    public String getReaderName() {return getReader().getName();}

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public LocalDate getDateOfAction() {
        return dateOfAction;
    }

    public void setDateOfAction(LocalDate dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    public ActionTypes getAction() {
        return action;
    }

    public void setAction(ActionTypes action) {
        this.action = action;
    }

    public String toString(){
        return super.toString()
                + String.format(" (%d) %s, <(%d) %s>, copy number: %d, %s, %s>",
                getReader().getId(),
                getReader().getName(),
                getBookCopy().getBook().getId(),
                getBookCopy().getBook().getName(),
                getBookCopy().getCopyNumber(),
                getDateOfAction().toString(Menu.dateFormat),
                action.toString());
    }
}
