/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import com.stockcontrol.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class ExportDocuments {

    public void show() throws IOException {
        App.show(FXMLLoader.load(getClass().getResource("ExportDocumentsView.fxml")));
    }

    public ExportDocument Create() throws SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.ImportDocuments.Create()");

        DB.executeUpdate("INSERT INTO ExportDocument (client) "
                + "VALUES ('Client')");

        return ExportDocument(LastCreatedDocumentId()).get(0);
    }

    private int LastCreatedDocumentId() throws SQLException {
        ResultSet rs = DB.executeQuery("SELECT ID FROM ExportDocument ORDER BY `id` DESC LIMIT 1");

        if (rs.next()) {

            return rs.getInt("ID");

        } else {
            return -1;
        }
    }

    public ArrayList<ExportDocument> ExportDocument(int id) throws SQLException {

        ArrayList<ExportDocument> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ExportDocument where id = " + id + " ORDER BY `id` ASC");

        while (rs.next()) {
            int docId = rs.getInt("ID");

            list.add(new ExportDocument(
                    docId,
                    rs.getDate("date"),
                    rs.getString("client"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new ExportedProducts().List(docId))
            );
        }
        return list;
    }

    public ArrayList<ExportDocument> List() throws SQLException {

        ArrayList<ExportDocument> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ExportDocument ORDER BY `id` ASC");

        while (rs.next()) {
            int docId = rs.getInt("ID");

            list.add(new ExportDocument(
                    docId,
                    rs.getDate("date"),
                    rs.getString("client"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new ExportedProducts().List(docId))
            );
        }
        return list;
    }
}
