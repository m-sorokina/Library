package library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import menu.Menu;
import org.joda.time.LocalDate;


public class Reader extends Item {
    @JsonProperty("first name")
    private String firstName;
    @JsonProperty("last name")
    private String lastName;

    @JsonProperty("date of birth")
    private LocalDate dateOfBirth;

    public Reader(String firstName, String lastName, LocalDate dateOfBirth){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Reader(){
        super();
        firstName = "";
        lastName = "";
        dateOfBirth = new LocalDate(1900, 1, 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return lastName;
    }

    public void setName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public String getFullName(){
        return String.format("%s, %s", getName(), getFirstName());
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String toString(){
        return super.toString()
                + String.format(" %s, %s>", getFirstName(), getDateOfBirth().toString(Menu.dateFormat));
    }

}
