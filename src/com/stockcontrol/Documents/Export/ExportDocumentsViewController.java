/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import com.stockcontrol.Documents.DocumentsView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class ExportDocumentsViewController implements Initializable {

    ExportDocument document;

    @FXML
    private TableView<ExportDocument> table;

    @FXML
    private TableColumn<ExportDocument, Number> idColumn;
    @FXML
    private TableColumn<ExportDocument, String> dateColumn;
    @FXML
    private TableColumn<ExportDocument, String> clientColumn;
    @FXML
    private TableColumn<ExportDocument, Number> totalPriceColumn;

    private final ObservableList<ExportDocument> observableList = FXCollections.observableArrayList();

    @FXML
    private TextField searchIdField;
    @FXML
    private TextField searchDilerField;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());

        table.setItems(observableList);

        table.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                if (document != null) {
                    try {
                        document.show();
                    } catch (IOException | SQLException ex) {
                        Logger.getLogger(ExportDocumentsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // bazada axtarish verdikde Error cixirdi
            // bu JavaFx-de table view cox qeribedir eee oz ozune avtomatik bu metodu cagirir
            // ona gore birinci yoxlayiram sonra.
            // yoxsa gonderirem ve error cixir nullPointerException
            if (newValue != null) {
                document = newValue;
            }
        });

        try {
            updateTable(new ExportDocuments().List());
        } catch (SQLException ex) {
            Logger.getLogger(ExportDocumentsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateTable(ArrayList<ExportDocument> list) {

        observableList.clear();

        if (!list.isEmpty()) {
            observableList.addAll(list);

        }
    }

    @FXML
    private void backButtonOnAction() throws IOException {
        new DocumentsView().show();

    }

    @FXML
    private void createExportDocumentButtonOnAction() throws IOException, SQLException {
        new ExportDocuments().Create().show();

    }

}
