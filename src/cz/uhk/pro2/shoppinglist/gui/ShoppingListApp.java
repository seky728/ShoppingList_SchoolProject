package cz.uhk.pro2.shoppinglist.gui;

import cz.uhk.pro2.shoppinglist.model.*;


import javax.swing.*;
import java.awt.*;

public class ShoppingListApp extends JFrame {

    private JTextArea txtOutput = new JTextArea(10,20);
    private ShoppingList shoppingList = new ShoppingList();
    private ShoppingListTableModel tableModel = new ShoppingListTableModel(shoppingList); // sdílým shoppingList s dalším objektem
    private JTable tbl = new JTable(tableModel);


    public ShoppingListApp() throws HeadlessException {  //konstruktor
        setTitle("Nákupní lístek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new JScrollPane(txtOutput),BorderLayout.CENTER);//object constraints -> světové strany
        add(new JScrollPane(tbl),BorderLayout.SOUTH);


        JPanel pnl = new JPanel();

        JButton btn = new JButton("Přidat položku");
        pnl.add(btn);


        btn.addActionListener(e -> {
            addItem();
        });

        JButton btnDel = new JButton("Uprav položku");
        pnl.add(btnDel);

        btnDel.addActionListener(e->{
            editItem();
        });

        JButton btnCheckDone = new JButton("Označ koupené");
        pnl.add(btnCheckDone);
        btnCheckDone.addActionListener(e -> checkDone());


        add(pnl, BorderLayout.NORTH);
        pack(); // Okno tak velké aby se tam ty komponenty vešly
    }

    public void checkDone(){
        int selectedRowIndex = tbl.getSelectedRow();
        if (selectedRowIndex >= 0) {
            shoppingList.getItems().get(selectedRowIndex).setDone(Boolean.TRUE);
        } else {
            JOptionPane.showInternalMessageDialog(null, "Nemáš nic v seznamu","Error",JOptionPane.ERROR_MESSAGE);
        }

        refreshGui();
    }

    public void addItem(){
        String name = JOptionPane.showInputDialog("Zadej název zboží");
        String count = JOptionPane.showInputDialog("Zadej množství zboží ("+name+")");
        try {
            Item item = new Item(name,Integer.parseInt(count));
            shoppingList.addItem(item);
            refreshGui();
        } catch (NumberFormatException e){
            JOptionPane.showInternalMessageDialog(null, "Není číslo", "Error not a number", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void editItem(){
        int selectedRowIndex = tbl.getSelectedRow();
        if (shoppingList.getItems().size() > 0) {
            Item editItem;
            if (selectedRowIndex >= 0){
               editItem = shoppingList.returnItem(selectedRowIndex);
            } else {
                String positionOfEdit = JOptionPane.showInputDialog("Zadej pozici zboží které chceš upravit");
                editItem = shoppingList.returnItem(Integer.parseInt(positionOfEdit));
            }
            String name = JOptionPane.showInputDialog("Zadej nový název zboží (starý: " + editItem.getName() + ")");
            String count = JOptionPane.showInputDialog("Zadej množství zboží (staré:" + editItem.getCount() + ")");
            editItem.setCount(Integer.parseInt(count));
            editItem.setName(name);
            refreshGui();
        } else {
            JOptionPane.showInternalMessageDialog(null, "Nemáš nic v seznamu","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshGui(){
        txtOutput.setText("");
        for (Item item : shoppingList.getItems()){
            txtOutput.append(item.getName() + " " +item.getCount() +"\n");
        }

        tableModel.fireTableDataChanged();
        setTitle("Nákupní lístek " + shoppingList.getCountUndone() + "/" +shoppingList.getCount());


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //lambda výraz
            ShoppingListApp s = new ShoppingListApp();
            s.setVisible(true);

        });
    }


}
