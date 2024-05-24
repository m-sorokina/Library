package menu;

public class MainMenu extends Menu {


    public MainMenu() {
        super();
        add("Library catalog", 1, () -> {new LibCatalogMenu().run(); return true;});
        add("Work with clients", () -> {return true;});
        setTitle("Main menu:");
    }
}
