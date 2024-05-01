package menu;

import commands.AuthorCommands;

public class LibCatalogMenu extends Menu{
    public LibCatalogMenu(){
        super();
        add("Author list", 1, () -> {
            new AuthorCommands().run(); return true;});
        add("Books list", () -> {return true;});
        add("Return to previous menu", 0, null);

    }
}
