package menu;

import commands.BookLogsCommands;
import commands.FindBookCommands;
import commands.ReaderCommands;
import commands.ReportCommands;

public class WorkWithReadersMenu extends Menu{

    public WorkWithReadersMenu(){
        super();
        add("Reader list", 1, () -> {
            new ReaderCommands().run(); return true;});
        add("Books log", () -> {new BookLogsCommands().run(); return true;});
        add("Find book", () -> {new FindBookCommands().run(); return true;});
        add("Reports", () -> {new ReportCommands().run(); return true;});
        setTitle("Work with readers:");
    }
}
