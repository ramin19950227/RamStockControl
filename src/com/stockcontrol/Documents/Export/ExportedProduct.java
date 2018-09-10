/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

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
class ExportedProduct {

    private final int id;
    private final int documentId;
    private final int productId;
    private final String productName;
    private final int qty;
    private final double outPrice;
    private final double totalPrice;

    ExportedProduct(int id, int documentId, int productId, String productName, int qty, double outPrice, double totalPrice) {
        this.id = id;
        this.documentId = documentId;
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.outPrice = outPrice;
        this.totalPrice = totalPrice;
    }

    public final StringProperty productName() {
        return new SimpleStringProperty(productName);
    }

    public final IntegerProperty qty() {
        return new SimpleIntegerProperty(qty);
    }

    public final DoubleProperty outPrice() {
        return new SimpleDoubleProperty(outPrice);
    }

    public final DoubleProperty totalPrice() {
        return new SimpleDoubleProperty(totalPrice);
    }

    @Override
    public String toString() {
        return "ExportedProduct{" + "id=" + id + ", documentId=" + documentId + ", productId=" + productId + ", productName=" + productName + ", qty=" + qty + ", outPrice=" + outPrice + ", totalPrice=" + totalPrice + '}';
    }

}
