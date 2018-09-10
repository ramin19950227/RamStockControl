/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

import com.ramlib.Validator.Validator;
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
public class ImportDocumentsViewController implements Initializable {

    ImportDocument selectedImportDocument;

    @FXML
    private TableView<ImportDocument> table;

    @FXML
    private TableColumn<ImportDocument, Number> idColumn;
    @FXML
    private TableColumn<ImportDocument, String> dateColumn;
    @FXML
    private TableColumn<ImportDocument, String> dilerColumn;
    @FXML
    private TableColumn<ImportDocument, Number> totalPriceColumn;

    private final ObservableList<ImportDocument> observableList = FXCollections.observableArrayList();

    @FXML
    private TextField idField;
    @FXML
    private TextField dilerField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        dilerColumn.setCellValueFactory(cellData -> cellData.getValue().dilerProperty());
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());

        table.setItems(observableList);

        table.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                if (selectedImportDocument != null) {
                    try {
                        new ImportDocuments().showEditView(selectedImportDocument.id());
                    } catch (IOException ex) {
                        Logger.getLogger(ImportDocumentsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ImportDocumentsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    // bazada axtarish verdikde Error cixirdi
                    // bu JavaFx-de table view cox qeribedir eee oz ozune avtomatik bu metodu cagirir
                    // ona gore birinci yoxlayiram sonra.
                    // yoxsa gonderirem ve error cixir nullPointerException
                    if (newValue != null) {
                        selectedImportDocument = newValue;
                    }
                });

        try {
            updateTable(new ImportDocuments().ImportDocumentList());
        } catch (SQLException ex) {
            Logger.getLogger(ImportDocumentsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateTable(ArrayList<ImportDocument> list) {

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
    private void createImportDocumentButtonOnAction() throws IOException, SQLException {
        new ImportDocuments().Create().show();

    }

    @FXML
    private void menuOpenDocumentOnAction() throws IOException, SQLException {
        new ImportDocuments().showEditView(table.getSelectionModel().getSelectedItem().id());
    }

    @FXML
    private void onIdFieldKeyReleased() throws SQLException {

        System.out.println(idField.getText());
        if (Validator.isValidInt(idField)) {
            updateTable(new ImportDocuments().ImportDocument(Integer.valueOf(idField.getText())));
        } else {
            updateTable(new ImportDocuments().ImportDocumentList());
        }
    }

    @FXML
    private void onDilerFieldKeyReleased() throws SQLException {
        System.out.println(dilerField.getText());

        if (Validator.isValidString(dilerField)) {
            updateTable(new ImportDocuments().findByDilerName(dilerField.getText()));
        } else {
            updateTable(new ImportDocuments().ImportDocumentList());
        }
    }

}
