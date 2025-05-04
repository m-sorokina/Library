package library;

import com.fasterxml.jackson.annotation.*;

public class Author extends Item {
    @JsonProperty("first name")
    private String firstName;
    @JsonProperty("last name")
    private String lastName;

    public Author(String firstName, String lastName){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(){
        super();
        firstName = "";
        lastName = "";
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

    public String toString(){
        return super.toString()
                + String.format(" %s>", getFirstName());
    }


}
