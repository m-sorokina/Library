package commands;

import library.ItemsList;
import menu.Menu;

public abstract class MenuCommands<T> extends Menu {

    public MenuCommands() {
        super();
        add("Print all", 1, this::printAll);
        add("Add item", this::add);
        add("Remove item", this::remove);
        add("Edit item", this::edit);
        add("Print item", this::printItem);
        add("Find by name", this::findByName);
    }

    public abstract ItemsList<T> getList();

    public abstract void addItem();

    public abstract void editItem(T item);

    public Boolean add() {
        try{
            addItem();
        }
        catch (Exception ignored) {}
        return true;
    }

    public Boolean edit() {
        try {
            T item = find(getList(), "Enter id: ");
            System.out.println(item);
            editItem(item);
        }
        catch(Exception ignored){}
        return true;
    }

    public Boolean printAll() {
        try {
            System.out.println("-".repeat(30));
            if (getList().getTitle() != null && !getList().getTitle().trim().isEmpty()) {
                println(getList().getListOf(), getList().getTitle());
            } else {
                println(getList().getListOf());
            }
        }
        catch (Exception ignored){}
        return true;
    }


    public Boolean remove() {
        try{
            T item = find(getList(), "Enter id: ");
            removeItem(item);
        }
        catch (Exception ignored){}
        return true;
    }
    public void removeItem(T item) {
            getList().remove(item);
    }


    public Boolean printItem() {
        try {
            System.out.println(getList().find(enterInt("Enter id: ")));
        }
        catch (Exception ignored){}
        return true;
    }

    public Boolean findByName() {
        try {
            println(getList().find(enterString("Enter name or part of name: ")));
        }
        catch (Exception ignored) {}
        return true;
    }

}
