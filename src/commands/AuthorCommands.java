package commands;

import library.Author;
import library.ItemsList;
import menu.Main;

public class AuthorCommands extends MenuCommands<Author> {

    public AuthorCommands() {
        super();
        add("Return to previous menu", 0, null);
    }

    @Override
    public ItemsList<Author> getList() {
        return Main.lib.getAuthors();
    }

    public Boolean addItem() {
        Author author = new Author();
        author.setName(enterString("Enter last name: "));
        author.setFirstName(enterString("Enter first name: "));
        getList().add(author);
        return true;
    }

    public Boolean editItem() {
        Author author = getList().find(enterInt("Enter id: "));
        if (author == null) {
            System.out.println("Id was not found");
            return true;
        }
        System.out.println(author);
        author.setName(enterString("Enter last name: "));
        author.setFirstName(enterString("Enter first name: "));
        return true;
    }

}

