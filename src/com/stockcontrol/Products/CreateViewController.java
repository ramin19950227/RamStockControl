/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Products;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class CreateViewController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField inPriceField;

    @FXML
    private TextField outPriceField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void backButtonOnAction() throws IOException {
        System.out.println("com.stockcontrol.Products.CreateViewController.backButtonOnAction()");

        new Products().showProductsView();
    }

    @FXML
    private void createProductButtonOnAction() throws SQLException, IOException {
        System.out.println("com.stockcontrol.Products.CreateViewController.createProductButtonOnAction()");

        if (isNameValid() && isInPriceValid() && isOutPriceValid()) {
            new Products().Create(nameField.getText(), Double.valueOf(inPriceField.getText()), Double.valueOf(outPriceField.getText()));

            new Products().showProductsView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Zehmet Olmasa Daxil elediyiniz melumatlari yoxlayin");
            alert.setHeaderText("Kecerli melumat Daxil edin");
            alert.setContentText("Kecerli melumat Daxil Edin");
            alert.showAndWait();

        }

    }

    private boolean isNameValid() {
        System.out.println("com.stockcontrol.Products.CreateViewController.isNameValid()");
        if (nameField.getText().trim().isEmpty() || nameField.getText() == null || nameField.getText().trim().length() == 0) {
            System.out.println("false");
            return false;

        } else {
            System.out.println("true");
            return true;
        }
    }

    private boolean isInPriceValid() {
        System.out.println("com.stockcontrol.Products.CreateViewController.isInPriceValid()");
        if (inPriceField.getText().trim().isEmpty() || inPriceField.getText() == null || inPriceField.getText().trim().length() == 0) {
            System.out.println("false");
            return false;

        } else {
            System.out.println("try parsing");
            try {
                Double.parseDouble(inPriceField.getText());
                System.out.println("parsing ok");
                System.out.println("true");
                return true;

            } catch (NumberFormatException e) {
                System.out.println("parsing fail");
                System.out.println("false");
                return false;
            }
        }
    }

    private boolean isOutPriceValid() {
        System.out.println("com.stockcontrol.Products.CreateViewController.isOutPriceValid()");
        if (outPriceField.getText().trim().isEmpty() || outPriceField.getText() == null || outPriceField.getText().trim().length() == 0) {
            System.out.println("false");
            return false;

        } else {
            System.out.println("try parsing");
            try {
                Double.parseDouble(outPriceField.getText());
                System.out.println("parsing ok");
                System.out.println("true");
                return true;

            } catch (NumberFormatException e) {
                System.out.println("parsing fail");
                System.out.println("false");
                return false;
            }
        }
    }

}
