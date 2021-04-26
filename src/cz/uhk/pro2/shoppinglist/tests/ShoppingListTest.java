package cz.uhk.pro2.shoppinglist.tests;

import cz.uhk.pro2.shoppinglist.model.Item;
import cz.uhk.pro2.shoppinglist.model.ShoppingList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingListTest {

    @Test //alt + enter -> stáhnout JUnit4
    public void testEmptyList(){
        ShoppingList l = new ShoppingList();
        int x = l.getCountDone();
        int y = l.getCountUndone();

        assertEquals(0,x);
        assertEquals(0,y);
    }

    @Test
    public void test1Undone2Done(){
        ShoppingList l = new ShoppingList();
        Item item1 = new Item("Pivo",5,true);
        Item item2 = new Item("Rohlík", 1, true);
        Item item3 = new Item("Chleba", 1, false);
        Item item4 = new Item("Chleba", 1, false);

        l.addItem(item1);
        l.addItem(item2);
        l.addItem(item3);
        l.addItem(item4);

        int undone = l.getCountUndone();
        int done = l.getCountDone();



        assertEquals(2,undone);
        assertEquals(2,done);
        assertEquals(0.33,0.44,0.11);


    }
}
