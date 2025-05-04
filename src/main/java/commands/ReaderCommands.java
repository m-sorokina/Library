package commands;

import exceptions.WrongValueException;
import library.ItemsList;
import library.Reader;
import menu.Main;

public class ReaderCommands extends MenuCommands<Reader> {

    public ReaderCommands() {
        super();
        setTitle("Reader's entry:");
    }

    @Override
    public ItemsList<Reader> getList() {
        return Main.lib.getReaders();
    }

    @Override
    public void addItem() {
        try {
            Reader reader = new Reader();
            reader.setName(enterString("Enter last name: "));
            reader.setFirstName(enterString("Enter first name: "));
            reader.setDateOfBirth(enterDate("Enter date of birth in format {yyyy-mm-dd}: "));
            getList().add(reader);
            System.out.println(reader);
        } catch (WrongValueException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void editItem(Reader reader) {
        if (reader == null) {
            System.out.println("Id was not found");
            return;
        }
        try {
            reader.setName(updateString("Enter last name: ", reader.getName()));
            reader.setFirstName(updateString("Enter first name: ", reader.getFirstName()));
            reader.setDateOfBirth(updateDate("Enter date of birth in format {yyyy-mm-dd}: ", reader.getDateOfBirth()));
            System.out.println(reader);
        }
        catch (WrongValueException e){
            System.out.println(e.getMessage());
        }
    }

}
