package library;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ItemsList<T> {
    @JsonProperty("list")
    List<T> listOf;

    @JsonProperty("list title")
    String title;

    public ItemsList() {
        listOf = new ArrayList<>();
        title = "";
    }

    public ItemsList(String title) {
        listOf = new ArrayList<>();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<T> getListOf() {
        return listOf;
    }

    public T getItem(int index) {
        checkIndex(index);
        return listOf.get(index);
    }

    public void add(T item) {
        ((Item) item).setId(listOf.isEmpty() ? 1 : ((Item) listOf.getLast()).getId() + 1);
        listOf.add(item);
    }

    public void remove(T item) {
        listOf.remove(item);
    }

    public T find(Integer id) {
        return listOf.stream()
                .filter(item -> ((Item) item).getId().equals(id))
                .findAny().
                orElse(null);
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

