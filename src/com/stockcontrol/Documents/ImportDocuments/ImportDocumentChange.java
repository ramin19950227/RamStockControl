/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

import java.sql.SQLException;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class ImportDocumentChange {

    private final ImportDocument document;

    public ImportDocumentChange(ImportDocument document) {
        this.document = document;
    }

    void Diler(String newValue) throws SQLException {
        DB.executeUpdate("UPDATE ImportDocument SET "
                + "`diler`='" + newValue + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void Note(String newValue) throws SQLException {
        DB.executeUpdate("UPDATE ImportDocument SET "
                + "`note`='" + newValue + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void PlusTotalPrice(Double newValue) throws SQLException {
        DB.executeUpdate("UPDATE ImportDocument SET "
                + "`totalPrice`='" + (document.totalPrice() + newValue) + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void MinusTotalPrice(Double newValue) throws SQLException {
        DB.executeUpdate("UPDATE ImportDocument SET "
                + "`totalPrice`='" + (document.totalPrice() - newValue) + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

}
