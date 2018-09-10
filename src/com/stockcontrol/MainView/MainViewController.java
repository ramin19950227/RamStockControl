/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.MainView;

import com.stockcontrol.CashModule.Cashs;
import com.stockcontrol.Documents.DocumentsView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.stockcontrol.Products.Products;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class MainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void productsButtonOnAction() throws IOException {
        new Products().showProductsView();

    }

    @FXML
    private void documentsButtonOnAction() throws IOException {
        new DocumentsView().show();

    }

    @FXML
    private void cashButtonOnAction() throws IOException {
        new Cashs().show();
    }

}
