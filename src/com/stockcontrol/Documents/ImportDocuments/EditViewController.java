/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.ImportDocuments;

import com.ramlib.Converters.MyDateConverter;
import com.ramlib.alert.MyAlert;
import com.stockcontrol.Products.Product;
import com.stockcontrol.Products.Products;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class EditViewController implements Initializable {

    private int documentId;
    private ImportDocument document;

    @FXML
    private TextField idField;

    @FXML
    private TextField dilerField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea noteArea;

    // Add Product Line
    @FXML
    private TextField productNameField;

    @FXML
    private TextField productQtyField;

    // Down Line
    @FXML
    private TextField documentTotalPriceField;

    @FXML
    private TableView<ImportedProduct> table;

    @FXML
    private TableColumn<ImportedProduct, String> nameColumn;
    @FXML
    private TableColumn<ImportedProduct, Number> qtyColumn;
    @FXML
    private TableColumn<ImportedProduct, Number> inPriceColumn;
    @FXML
    private TableColumn<ImportedProduct, Number> totalPriceColumn;

    private final ObservableList<ImportedProduct> observableList = FXCollections.observableArrayList();

    Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.initialize()");

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().productName());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qty());
        inPriceColumn.setCellValueFactory(cellData -> cellData.getValue().inPrice());
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPrice());

        table.setItems(observableList);

    }

    public void documentId(int documentId) throws SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.documentId()");
        this.documentId = documentId;

        initImportDocument(new ImportDocuments().ImportDocument(documentId));

    }

    public void SelectedProduct(Product product) {
        this.product = product;
        productNameField.setText(product.name());
        productNameField.setDisable(true);
        productQtyField.requestFocus();

    }

    private boolean isQtyValid() {
        System.out.println("com.stockcontrol.Products.CreateViewController.isInPriceValid()");
        if (productQtyField.getText().trim().isEmpty() || productQtyField.getText() == null || productQtyField.getText().trim().length() == 0) {
            System.out.println("false");
            MyAlert.alertContent(0, " Введите Количество! НЕТ НЕТ НЕТ :))))");

            return false;

        } else {
            System.out.println("try parsing");
            try {
                Integer.parseInt(productQtyField.getText());
                System.out.println("parsing ok");
                System.out.println("true");

                if (Integer.parseInt(productQtyField.getText()) == 0) {
                    MyAlert.alertContent(0, " Введите Количество Больше Нуля ! НЕТ НЕТ НЕТ :))))");
                    return false;
                }

                return true;

            } catch (NumberFormatException e) {
                System.out.println("parsing fail");
                System.out.println("false");

                MyAlert.alertContent(0, " Введите Число! НЕТ НЕТ НЕТ :))))");

                return false;
            }
        }
    }

    private void initImportDocument(ArrayList<ImportDocument> ImportDocumentList) throws SQLException {
        if (!ImportDocumentList.isEmpty()) {

            document = ImportDocumentList.get(0);
            initImportDocument(document);

        }
    }

    private void initImportDocument(ImportDocument importDocument) throws SQLException {

        idField.setText(importDocument.idString());
        dilerField.setText(importDocument.diler());
        datePicker.setValue(MyDateConverter.asLocalDate(importDocument.date()));
        noteArea.setText(importDocument.note());
        documentTotalPriceField.setText(importDocument.totalPriceString());

        observableList.clear();
        observableList.addAll(new ImportedProducts().ImportedProductListByDocumentId(importDocument.id()));
    }

//    
//    // DatePicker-e bugunku tarixi set edek
//        datePicker.setValue(LocalDate.now());
//        datePicker.setOnAction(event -> {
//            //for Testing
////            LocalDate date = datePicker.getValue();
////            //LOGWriter.println("Selected LocalDate: " + date);
////            Date d = MyDateConverter.asDate(date);
////            //LOGWriter.println("Selected util.Date: " + d);
//
//        });
//                    datePicker.requestFocus();
//                        LocalDate localDate = datePicker.getValue();
//            Date date = MyDateConverter.asDate(localDate);
    @FXML
    private void backButtonOnAction() throws IOException {
        new ImportDocuments().show();
    }

    @FXML
    private void dilerFieldOnKeyReleased() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.dilerFieldOnKeyReleased()");
        System.out.println("Diler: " + dilerField.getText());

        document.change().Diler(dilerField.getText());

        //dilerField.setStyle("-fx-border-color:green");
    }

    @FXML
    private void datePickerOnAction() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.datePickerOnAction()");

        LocalDate date = datePicker.getValue();
        System.out.println("Selected LocalDate: " + date);
        ;

        MyAlert.alertContent(0, " Пока Что не Работает, В РАЗРАБОТКЕ! :))))");

    }

    @FXML
    private void noteAreaOnKeyReleased() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.noteAreaOnKeyReleased()");

        System.out.println("Note : " + noteArea.getText());
        document.change().Note(noteArea.getText());

    }

    @FXML
    private void clearSelectedProductButtonOnAction() throws IOException {
        product = null;

        productNameField.clear();
        productQtyField.clear();
    }

    @FXML
    private void importSelectedProductButtonOnAction() throws IOException, SQLException {
        if (isProductSelected() && isQtyValid()) {
            new ImportedProducts().Create(documentId, product.id(), product.name(), Integer.parseInt(productQtyField.getText()), product.inPrice(), (product.inPrice() * Integer.parseInt(productQtyField.getText())));
            product.incQty(Integer.parseInt(productQtyField.getText()));

            document.change().PlusTotalPrice(product.inPrice() * Integer.parseInt(productQtyField.getText()));

            productNameField.clear();
            productQtyField.clear();

            initImportDocument(new ImportDocuments().ImportDocument(documentId));
        }
    }

    @FXML
    private void selectProductButtonOnAction() throws IOException, SQLException {

        new Products().showSelectView(this);

    }

    private boolean isProductSelected() {
        //product != null
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.isProductSelected()");

        if (product == null) {
            System.out.println("false");
            MyAlert.alertContent(0, " Товар не выбран! НЕТ НЕТ НЕТ :))))");
            return false;
        } else {
            return true;
        }
    }

}
