/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class ImportedProducts {

    public void Create(int documentId, int productId, String name, int qty, double inPrice, double totalPrice) throws SQLException {
        DB.Collection("ImportedProduct").insert("NULL", documentId, productId, name, qty, inPrice, totalPrice);

//        dbConnect.executeUpdate("INSERT INTO ImportedProduct (documentId, productId, productName, qty, inPrice, totalPrice) "
//                + "VALUES ('"
//                + documentId + "', '"
//                + productId + "', '"
//                + name + "', '"
//                + qty + "', '"
//                + inPrice + "', '"
//                + totalPrice + "')");
    }

    public ArrayList<ImportedProduct> ImportedProduct(int id) {

        ArrayList<ImportedProduct> list = new ArrayList<>();

        list.add(new ImportedProduct(id, id, id, "", id, id, id));

        return list;
    }

    public ArrayList<ImportedProduct> ImportedProductList() {

        ArrayList<ImportedProduct> list = new ArrayList<>();

        list.add(new ImportedProduct(0, 0, 0, "", 0, 0, 0));

        return list;
    }

    public ArrayList<ImportedProduct> ImportedProductListByDocumentId(int id) throws SQLException {

        // select * from ImportedProduct where documentId = " + id;
        ArrayList<ImportedProduct> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ImportedProduct where documentId = " + id + " ORDER BY `id` ASC");

        while (rs.next()) {
            list.add(new ImportedProduct(
                    rs.getInt("ID"),
                    rs.getInt("documentId"),
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getInt("qty"),
                    rs.getDouble("inPrice"),
                    rs.getDouble("totalPrice"))
            );

        }

        return list;
    }

}
