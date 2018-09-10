/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import java.sql.SQLException;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class ExportDocumentChange {

    private final ExportDocument document;

    ExportDocumentChange(ExportDocument document) {
        this.document = document;

    }

    void Client(String newValue) throws SQLException {
        DB.executeUpdate("UPDATE ExportDocument SET "
                + "`client`='" + newValue + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void Note(String newValue) throws SQLException {
        DB.executeUpdate("UPDATE ExportDocument SET "
                + "`note`='" + newValue + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void PlusTotalPrice(Double newValue) throws SQLException {
        DB.executeUpdate("UPDATE ExportDocument SET "
                + "`totalPrice`='" + (document.totalPrice() + newValue) + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

    void MinusTotalPrice(Double newValue) throws SQLException {
        DB.executeUpdate("UPDATE ExportDocument SET "
                + "`totalPrice`='" + (document.totalPrice() - newValue) + "'"
                + " WHERE  `id`=" + document.id() + ";");
    }

}
