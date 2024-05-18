package commands;

import library.ItemsList;
import menu.Menu;

public class MenuCommands<T> extends Menu {

    public MenuCommands() {
        super();
        add("Print all", 1, this::printAll);
        add("Add item", this::addItem);
        add("Remove item", this::removeItem);
        add("Edit item", this::editItem);
        add("Print item", this::printItem);
        add("Find by name", this::findByName);
    }

    public ItemsList<T> getList() {
        return null;
    }

    public Boolean printAll() {
        System.out.println("-".repeat(30));
        if (getList().getTitle() != null && !getList().getTitle().trim().isEmpty()) {
            println(getList().getListOf(), getList().getTitle());
        } else {
            println(getList().getListOf());
        }
        return true;
    }

//    public Boolean printAll(String title) {
//        System.out.println("-".repeat(30));
//        println(getList().getListOf(), title);
//        return true;
//    }


    public Boolean addItem() {
        return true;
    }

    public Boolean removeItem() {
        getList().remove(getList().find(enterInt("Enter id: ")));
        return true;
    }

    public Boolean editItem() {
        T item = getList().find(enterInt("Enter id: "));
        return true;
    }

    public Boolean printItem() {
        System.out.println(getList().find(enterInt("Enter id: ")));
        return true;
    }

    public Boolean findByName() {
        println(getList().find(enterString("Enter name or part of name: ")));
        return true;
    }

}
