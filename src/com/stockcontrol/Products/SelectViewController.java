/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Products;

import com.stockcontrol.Documents.Export.ExportDocumentViewController;
import com.stockcontrol.Documents.ImportDocuments.EditViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.stockcontrol.MainView.MainView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class SelectViewController implements Initializable {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Number> qtyColumn;
    @FXML
    private TableColumn<Product, Number> inPriceColumn;
    @FXML
    private TableColumn<Product, Number> outPriceColumn;

    @FXML
    private TextField searchField;

    private Stage stage;
    //export
    private ExportDocumentViewController exportDocumentViewController;
    //Import
    private EditViewController editViewController;

    private final ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty());
        inPriceColumn.setCellValueFactory(cellData -> cellData.getValue().inPriceProperty());
        outPriceColumn.setCellValueFactory(cellData -> cellData.getValue().outPriceProperty());

        productTable.setEditable(false);

        // cedvel bosh olduqda arxada yazilir yoxdur ve s.
        productTable.setPlaceholder(new Label("Товар не найден. "));

        productTable.setItems(productObservableList);

        try {
            updateTable(new Products().Products());
        } catch (SQLException ex) {
            Logger.getLogger(SelectViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        productTable.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                if (product != null) {

                    if (editViewController != null) {
                        editViewController.SelectedProduct(product);
                    }

                    if (exportDocumentViewController != null) {
                        exportDocumentViewController.SelectedProduct(product);
                    }

                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Zehmet Olmasa Secim Edin");
                    alert.setHeaderText("Zehmet olmasa secin");
                    alert.setContentText("Secib Sonra Duymeye basin");
                    alert.showAndWait();

                }
            }
        });

        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    // bazada axtarish verdikde Error cixirdi
                    // bu JavaFx-de table view cox qeribedir eee oz ozune avtomatik bu metodu cagirir
                    // ona gore birinci yoxlayiram sonra.
                    // yoxsa gonderirem ve error cixir nullPointerException
                    if (newValue != null) {
                        product = newValue;
                    }
                });

    }

    @FXML
    private void backButtonOnAction() throws IOException {
        System.out.println("com.stockcontrol.Products.ProductsViewController.backButtonOnAction()");

        new MainView().show();
    }

    @FXML
    private void createProductButtonOnAction() throws IOException {
        System.out.println("com.stockcontrol.Products.ProductsViewController.createProductButtonOnAction()");

        new Products().showCreateView();
    }

    @FXML
    private void searchFieldOnKeyReleased() throws SQLException {
        System.out.println("com.stockcontrol.Products.ProductsViewController.searchFieldOnChangeReleased()");
        System.out.println("Searching text: " + searchField.getText().trim());

        updateTable(new Products().SearchByName(searchField.getText().trim()));

    }

    private void updateTable(ArrayList<Product> list) {

        productObservableList.clear();

        if (!list.isEmpty()) {
            productObservableList.addAll(list);

        }
    }

    public void EditViewController(EditViewController editViewController) {
        this.editViewController = editViewController;
    }

    public void Stage(Stage stage) {
        this.stage = stage;
    }

    void ExportDocumentViewController(ExportDocumentViewController exportDocumentViewController) {
        this.exportDocumentViewController = exportDocumentViewController;
    }

}
