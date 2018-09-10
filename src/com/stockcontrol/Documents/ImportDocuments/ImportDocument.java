/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

import com.ramlib.Converters.MyDateConverter;
import com.stockcontrol.App;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Ramin
 */
public class ImportDocument {

    private final int id;
    private final Date date;
    private final String diler;
    private final String note;
    private final Double totalPrice;

    private final ArrayList<ImportedProduct> importedDocumentList;

    public ImportDocument(int id, Date date, String diler, String note, Double totalPrice, ArrayList<ImportedProduct> importedDocumentList) {
        this.id = id;
        this.date = date;
        this.diler = diler;
        this.note = note;
        this.totalPrice = totalPrice;
        this.importedDocumentList = importedDocumentList;
    }

    public void show() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditView.fxml"));
        App.show(loader.load());

        ((EditViewController) loader.getController()).documentId(id);
    }

    public final IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public final StringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

    public final StringProperty dilerProperty() {
        return new SimpleStringProperty(diler);
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

    String diler() {
        return diler;
    }

    Date date() {
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

    ImportDocumentChange change() {
        return new ImportDocumentChange(this);
    }

}
