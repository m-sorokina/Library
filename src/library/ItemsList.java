package library;

import com.fasterxml.jackson.annotation.JsonProperty;
import commands.MenuCommands;
import menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class ItemsList<T> {
    @JsonProperty("list")
    List<T> listOf;

    public ItemsList() {
        listOf = new ArrayList<>();
    }

    public List<T> getListOf() {
        return listOf;
    }

//    public void setListOf(List<T> listOf) {
//        this.listOf = listOf;
//    }

    public T getItem(int index) {
        checkIndex(index);
        return listOf.get(index);
    }

//    public void setItem(int index, T item) {
//        checkIndex(index);
//        listOf.set(index, item);
//    }

    public void add(T item) {
        ((Item) item).setId(listOf.isEmpty() ? 1 : ((Item) listOf.getLast()).getId() + 1);
        listOf.add(item);
    }

//    public void remove(int index) {
//        checkIndex(index);
//        listOf.remove(index);
//    }

    public void remove(T item) {
        listOf.remove(item);
    }

    public T find(Integer id) {
        return listOf.stream().filter(item -> ((Item) item).getId().equals(id)).findAny().
                orElseGet(() -> {
                    Integer value  = (Menu.enterInt("Wrong id, please repeat, 'Enter' to exit: "));
                    if (value == MenuCommands.COMMAND_CANCEL){throw new RuntimeException();}
                    return find(value);
                });
    }

    public List<T> find(String namePart) {
        return listOf.stream().filter(item -> ((Item) item).getName().toLowerCase().contains(namePart.toLowerCase())).toList();
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= listOf.size()) {
            throw new RuntimeException("Index out of bound");
        }

    }

}

