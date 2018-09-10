/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import com.ramlib.Converters.MyDateConverter;
import com.stockcontrol.App;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Ramin
 */
public class ExportDocument {

    private final int id;
    private final java.util.Date date;
    private final String client;
    private final String note;
    private final Double totalPrice;

    private final ArrayList<ExportedProduct> exportedProducts;

    public ExportDocument(int id, Date date, String client, String note, Double totalPrice, ArrayList<ExportedProduct> exportedProducts) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.note = note;
        this.totalPrice = totalPrice;
        this.exportedProducts = exportedProducts;

    }

    public void show() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExportDocumentView.fxml"));
        App.show(loader.load());

        ((ExportDocumentViewController) loader.getController()).initDocument(this);
    }

    public final IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public final StringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

    public final StringProperty clientProperty() {
        return new SimpleStringProperty(client);
    }

    public final DoubleProperty totalPriceProperty() {
        return new SimpleDoubleProperty(totalPrice);
    }

    int id() {
        return id;
    }

    String idString() {
        return String.valueOf(id);
    }

    String client() {
        return client;
    }

    java.util.Date date() {
        return date;
    }

    String dateString() {
        return MyDateConverter.utilDate.toString(date);
    }

    String note() {
        return note;
    }

    double totalPrice() {
        return totalPrice;
    }

    String totalPriceString() {
        return String.valueOf(totalPrice);
    }

    ExportDocumentChange change() {
        return new ExportDocumentChange(this);
    }

}
