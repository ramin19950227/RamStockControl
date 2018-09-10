/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class ExportedProducts {

    public void Create(int documentId, int productId, String name, int qty, double outPrice, double totalPrice) throws SQLException {
        DB.executeUpdate("INSERT INTO ExportedProduct (documentId, productId, productName, qty, outPrice, totalPrice) "
                + "VALUES ('"
                + documentId + "', '"
                + productId + "', '"
                + name + "', '"
                + qty + "', '"
                + outPrice + "', '"
                + totalPrice + "')");
    }

    public ArrayList<ExportedProduct> ExportedProduct(int id) {

        ArrayList<ExportedProduct> list = new ArrayList<>();

        list.add(new ExportedProduct(id, id, id, "", id, id, id));

        return list;
    }

    public ArrayList<ExportedProduct> List() {

        ArrayList<ExportedProduct> list = new ArrayList<>();

        list.add(new ExportedProduct(0, 0, 0, "", 0, 0, 0));

        return list;
    }

    /**
     * Metod Parametrde Verilen Id kod la qeydiyyata alinmish Senedde Olan
     * Mehsullari Qaytarir
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList<ExportedProduct> List(int id) throws SQLException {
        System.out.println("com.stockcontrol.Documents.Export.ExportedProducts.List()");

        // select * from ImportedProduct where documentId = " + id;
        ArrayList<ExportedProduct> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM ExportedProduct where documentId = " + id + " ORDER BY `id` ASC");

        while (rs.next()) {
            list.add(new ExportedProduct(
                    rs.getInt("ID"),
                    rs.getInt("documentId"),
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getInt("qty"),
                    rs.getDouble("outPrice"),
                    rs.getDouble("totalPrice"))
            );
            System.out.println("ExportedProduct added");
        }

        return list;
    }

}
