package library;

public class Item {
    private Integer id;
    private String name;

    public Item(){
        this("");
    }
    public Item(String name){
        this.name = name;
        id = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return String.format("<%d %s", getId(), getName());
    }

}
