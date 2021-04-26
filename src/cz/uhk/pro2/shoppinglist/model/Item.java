package cz.uhk.pro2.shoppinglist.model;

public class Item {
    private String name;
    private int count;
    private Boolean done;


    public Item(String name, int count){
        this.name = name;
        this.count = count;
        this.done = false;
    }

    public Item(String name, int count, Boolean done) {
        this.name = name;
        this.count = count;
        this.done = done;
    }

    @Override
    public String toString() {

        return "Produkt ='" + name +
                ", množství =" + count +
                ", v košíku =" + done +
                '\n';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
