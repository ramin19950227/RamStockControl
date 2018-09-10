/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Products;

import java.sql.SQLException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class Product {
    
    private final int id;
    private final String name;
    private final int qty;
    private final double inPrice;
    private final double outPrice;
    
    public Product(int id, String name, int qty, double inPrice, double outPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", qty=" + qty + ", inPrice=" + inPrice + ", outPrice=" + outPrice + '}';
    }
    
    public final StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }
    
    public final IntegerProperty qtyProperty() {
        return new SimpleIntegerProperty(qty);
    }
    
    public final DoubleProperty inPriceProperty() {
        return new SimpleDoubleProperty(inPrice);
    }
    
    public final DoubleProperty outPriceProperty() {
        return new SimpleDoubleProperty(outPrice);
    }
    
    public String name() {
        return name;
    }
    
    public int qty() {
        return qty;
    }
    
    public double inPrice() {
        return inPrice;
    }
    
    public double outPrice() {
        return outPrice;
    }
    
    public String outPriceString() {
        return String.valueOf(outPrice);
    }
    
    public int id() {
        return id;
    }
    
    public void incQty(int qty) throws SQLException {
        DB.executeUpdate("UPDATE Product SET "
                + "`qty`='" + (this.qty + qty) + "'"
                + "WHERE  `id`=" + id + ";");
    }
    
    public void minusQty(int qty) throws SQLException {
        DB.executeUpdate("UPDATE Product SET "
                + "`qty`='" + (this.qty - qty) + "'"
                + "WHERE  `id`=" + id + ";");
    }
    
}
