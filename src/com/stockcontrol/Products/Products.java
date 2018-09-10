/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Products;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.stockcontrol.App;
import com.stockcontrol.Documents.Export.ExportDocumentViewController;
import com.stockcontrol.Documents.ImportDocuments.EditViewController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.stage.Stage;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class Products {

    //---View API's
    public void showProductsView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProductsView.fxml"));
        App.show(root);
    }

    public void showCreateView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateView.fxml"));
        App.show(root);
    }

    public void showSelectView(EditViewController editViewController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        SelectViewController controller = (SelectViewController) loader.getController();
        controller.EditViewController(editViewController);
        controller.Stage(stage);
    }

    public void showExportSelectView(ExportDocumentViewController exportDocumentViewController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        SelectViewController selectController = (SelectViewController) loader.getController();
        selectController.ExportDocumentViewController(exportDocumentViewController);
        selectController.Stage(stage);
    }

    //---DB API's
    public void Create(String name, double inPrice, double outPrice) throws SQLException {
        DB.executeUpdate("INSERT INTO Product (name, inPrice, outPrice) "
                + "VALUES ('" + name + "',"
                + " '" + inPrice + "',"
                + " '" + outPrice + "')");
    }

    public ArrayList<Product> Product(int id) throws SQLException {
        System.out.println("stockcontrolv1.Products.Products.Product()");

        ArrayList<Product> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM Product WHERE id=" + id);

        if (rs.next()) {

            list.add(new Product(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getInt("qty"),
                    rs.getDouble("inPrice"),
                    rs.getDouble("outPrice")
            )
            );
        }

        return list;

    }

    public ArrayList<Product> Products() throws SQLException {
        System.out.println("stockcontrolv1.Products.Products.Products()");

        ArrayList<Product> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM Product ORDER BY `id` ASC");

        while (rs.next()) {

            list.add(new Product(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getInt("qty"),
                    rs.getDouble("inPrice"),
                    rs.getDouble("outPrice")
            )
            );
        }

        return list;
    }

    public ArrayList<Product> SearchByName(String name) throws SQLException {
        System.out.println("stockcontrolv1.Products.Products.SearchByName()");

        ArrayList<Product> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM Product WHERE name LIKE '%" + name + "%'");

        while (rs.next()) {

            list.add(new Product(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getInt("qty"),
                    rs.getDouble("inPrice"),
                    rs.getDouble("outPrice")
            )
            );
        }

        return list;

    }

}
