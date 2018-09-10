/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol;

import com.stockcontrol.CashModule.Cashs;
import com.stockcontrol.Documents.DocumentsView;
import com.stockcontrol.Documents.Export.ExportDocuments;
import com.stockcontrol.Documents.ImportDocuments.ImportDocuments;
import com.stockcontrol.MainView.MainView;
import com.stockcontrol.Products.Products;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class AppSceneController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCenter(Parent root) {
        borderPane.setCenter(root);
    }

    @FXML
    private void menuShowMainView() throws IOException {
        new MainView().show();
    }

    @FXML
    private void menuShowProducts() throws IOException {
        new Products().showProductsView();
    }

    @FXML
    private void menuShowCreateProduct() throws IOException {
        new Products().showCreateView();
    }

    @FXML
    private void menuShowDocuments() throws IOException {
        new DocumentsView().show();
    }

    @FXML
    private void menuShowImportDocuments() throws IOException {
        new ImportDocuments().show();
    }

    @FXML
    private void menuShowCreateImportDocuments() throws IOException, SQLException {
        new ImportDocuments().Create().show();
    }

    @FXML
    private void menuShowExportDocuments() throws IOException {
        new ExportDocuments().show();
    }

    @FXML
    private void menuShowCreateExportDocuments() throws IOException, SQLException {
        new ExportDocuments().Create().show();
    }

    @FXML
    private void menuShowCashs() throws IOException, SQLException {
        new Cashs().show();
    }
}
