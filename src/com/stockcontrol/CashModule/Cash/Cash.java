/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule.Cash;

import com.stockcontrol.App;
import com.stockcontrol.CashModule.CashItem.CashItemCRUD;
import com.stockcontrol.CashModule.CashItem.CashItem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Ramin
 */
public class Cash {

    private final int id;
    private final String name;
    private final String note;
    private final double totalPrice;

    private final ArrayList<CashItem> cashItems;

    public Cash(int id, String name, String note, double totalPrice, ArrayList<CashItem> cashItems) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.totalPrice = totalPrice;
        this.cashItems = cashItems;
    }

    @Override
    public String toString() {
        return "Касса:  " + id + ", " + name + ",   " + totalPrice;
    }

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cash.fxml"));
        Parent root = loader.load();
        App.show(root);

        ((CashController) loader.getController()).Cash(this);

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<CashItem> getCashItems() {
        return cashItems;
    }

    void Import(CashItem item) throws SQLException {
        new CashItemCRUD().INSERT(item);

        new CashCRUD().UPDATE_totalPrice(id, (totalPrice + item.getPrice()));
    }

    void Export(CashItem item) {
        new CashItemCRUD().INSERT(item);

        new CashCRUD().UPDATE_totalPrice(id, (totalPrice - item.getPrice()));
    }

}
