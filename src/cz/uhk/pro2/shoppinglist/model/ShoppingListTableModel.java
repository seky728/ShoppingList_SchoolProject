package cz.uhk.pro2.shoppinglist.model;

import javax.swing.table.AbstractTableModel;

public class ShoppingListTableModel extends AbstractTableModel {

    private ShoppingList shoppingList;

    public ShoppingListTableModel(ShoppingList shoppingList){
        this.shoppingList = shoppingList;
    }

    @Override
    public int getRowCount() {
        return shoppingList.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = shoppingList.getItems().get(rowIndex);
        switch (columnIndex){
            case 0: return item.getName();
            case 1: return item.getCount();
            case 2: return item.getDone();
            default: return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Název";
            case 1: return "Počet";
            case 2: return "Koupeno";
            default: return "Chyba!"; // this should not happen
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return String.class;
            case 1: return Integer.class; // zarovnají se pod sebe řády
            case 2: return Boolean.class; // přidá checkbox
            default: return Object.class; // this should not happen
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 2);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Item item = shoppingList.getItems().get(rowIndex);
        if (columnIndex == 2){
            item.setDone((Boolean) aValue);
        }
    }
}
