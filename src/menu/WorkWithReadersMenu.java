package menu;

import commands.ReaderCommands;

public class WorkWithReadersMenu extends Menu{

    public WorkWithReadersMenu(){
        super();
        add("Reader list", 1, () -> {
            new ReaderCommands().run(); return true;});
        add("Books log", () -> {return true;});
        add("Find book", () -> {return true;});
        add("Reports", () -> {return true;});
        setTitle("Work with readers:");
    }
}
