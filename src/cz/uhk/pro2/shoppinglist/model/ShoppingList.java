package cz.uhk.pro2.shoppinglist.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingList {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public Item returnItem(int number) {
       return items.get(number);
    }

    public int getCountDone(){
       return (int)items.stream().filter(Item::getDone).count();
    }

    public int getCount(){
        return items.size();
    }


    public int getCountUndone(){
        return getCount()-getCountDone();
    }

    public List<Item> getItems(){
        return Collections.unmodifiableList(items);
    }
}
