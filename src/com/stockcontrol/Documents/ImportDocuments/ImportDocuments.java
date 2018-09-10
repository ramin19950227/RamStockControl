/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

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
public class ImportDocuments {

    public void show() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ImportDocumentsView.fxml"));
        App.show(root);
    }

    public void showEditView(int id) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditView.fxml"));
        Parent root = loader.load();
        App.show(root);

        ((EditViewController) loader.getController()).documentId(id);

    }

    public ImportDocument Create() throws SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.ImportDocuments.Create()");

        DB.executeUpdate("INSERT INTO ImportDocument (diler) "
                + "VALUES ('Diler')");

        return ImportDocument(LastCreatedDocumentId()).get(0);
    }

    private int LastCreatedDocumentId() throws SQLException {
        ResultSet rs = DB.executeQuery("SELECT ID FROM ImportDocument ORDER BY `id` DESC LIMIT 1");

        if (rs.next()) {

            return rs.getInt("ID");

        } else {
            return -1;
        }
    }

    public ArrayList<ImportDocument> ImportDocument(int id) throws SQLException {

        ArrayList<ImportDocument> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ImportDocument where id = " + id + " ORDER BY `id` ASC");

        while (rs.next()) {
            int docId = rs.getInt("ID");

            list.add(new ImportDocument(
                    docId,
                    rs.getDate("date"),
                    rs.getString("diler"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new ImportedProducts().ImportedProductListByDocumentId(docId))
            );
        }
        return list;
    }

    public ArrayList<ImportDocument> ImportDocumentList() throws SQLException {

        ArrayList<ImportDocument> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ImportDocument ORDER BY `id` ASC");

        while (rs.next()) {
            int docId = rs.getInt("ID");

            list.add(new ImportDocument(
                    docId,
                    rs.getDate("date"),
                    rs.getString("diler"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new ImportedProducts().ImportedProductListByDocumentId(docId))
            );
        }
        return list;
    }

    ArrayList<ImportDocument> findByDilerName(String name) throws SQLException {

        ArrayList<ImportDocument> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ImportDocument WHERE diler LIKE '%" + name + "%'");

        while (rs.next()) {
            int docId = rs.getInt("ID");

            list.add(new ImportDocument(
                    docId,
                    rs.getDate("date"),
                    rs.getString("diler"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new ImportedProducts().ImportedProductListByDocumentId(docId))
            );
        }
        return list;
    }

}
