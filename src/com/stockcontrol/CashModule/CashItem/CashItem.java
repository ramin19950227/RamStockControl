/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule.CashItem;

import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ramin
 */
public class CashItem {

    private final int id;
    private final int cashId;
    private final String operation;
    private final double price;
    private final String note;
    private final Date date;

    public CashItem(int id, int cashId, String operation, double price, String note, Date date) {
        this.id = id;
        this.cashId = cashId;
        this.operation = operation;
        this.price = price;
        this.note = note;
        this.date = date;
    }

    @Override
    public String toString() {
        return "CashItem{" + "id=" + id + ", cashId=" + cashId + ", type=" + operation + ", price=" + price + ", note=" + note + ", date=" + date + '}';
    }

    public final IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public final StringProperty operationProperty() {
        return new SimpleStringProperty(operation);
    }

    public final DoubleProperty priceProperty() {
        return new SimpleDoubleProperty(price);
    }

    public final StringProperty noteProperty() {
        return new SimpleStringProperty(note);
    }

    public final StringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

    public int getId() {
        return id;
    }

    public int getCashId() {
        return cashId;
    }

    public String getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }



}
