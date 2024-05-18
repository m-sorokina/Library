package menu;

import commands.AuthorCommands;
import commands.BookCommands;

public class LibCatalogMenu extends Menu{
    public LibCatalogMenu(){
        super();
        add("Author list", 1, () -> {
            new AuthorCommands().run(); return true;});
        add("Books list", () -> {
            new BookCommands().run(); return true;});
        add("Return to previous menu", 0, null);
        setTitle("Library catalog:");
    }


}
