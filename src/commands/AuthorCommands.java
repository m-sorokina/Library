package commands;

import exceptions.CommandCancelException;
import library.Author;
import library.ItemsList;
import menu.Main;

import java.util.Objects;

public class AuthorCommands extends MenuCommands<Author> {

    public AuthorCommands() {
        super();
        add("Return to previous menu", 0, null);
    }

    @Override
    public ItemsList<Author> getList() {
        return Main.lib.getAuthors();
    }

    public Boolean removeItem() {
        Integer id = enterInt("Enter id: ");
        if (Main.lib.getBooks().getListOf().stream()
                .anyMatch(book ->
                        Objects.equals(book.getAuthor().getId(), id))) {
            System.out.println("Author can't be removed. Some author's books are still available");
        } else {
            getList().remove(getList().find(id));
        }
        return true;
    }

    public Boolean addItem() {
        try {
            Author author = new Author();
            author.setName(enterString("Enter last name: "));
            author.setFirstName(enterString("Enter first name: "));
            getList().add(author);
        } catch (CommandCancelException ignored) {

        }
        return true;
    }

    public Boolean editItem() {
        Author author = getList().find(enterInt("Enter id: "));
        if (author == null) {
            System.out.println("Id was not found");
            return true;
        }
        System.out.println(author);
        author.setName(updateString("Enter last name: ", author.getName()));
        author.setFirstName(updateString("Enter first name: ", author.getFirstName()));
        return true;
    }

}

