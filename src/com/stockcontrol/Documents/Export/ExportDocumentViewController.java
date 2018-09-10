/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents.Export;

import com.ramlib.Converters.MyDateConverter;
import com.ramlib.alert.MyAlert;
import com.stockcontrol.Products.Product;
import com.stockcontrol.Products.Products;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class ExportDocumentViewController implements Initializable {

    private ExportDocument document;

    @FXML
    private TextField idField;

    @FXML
    private TextField clientField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea noteArea;

    // Add Product Line
    @FXML
    private TextField productNameField;

    @FXML
    private TextField productQtyField;

    @FXML
    private TextField productOutPriceField;

    // Down Line
    @FXML
    private TextField documentTotalPriceField;

    @FXML
    private TableView<ExportedProduct> table;

    @FXML
    private TableColumn<ExportedProduct, String> nameColumn;
    @FXML
    private TableColumn<ExportedProduct, Number> qtyColumn;
    @FXML
    private TableColumn<ExportedProduct, Number> outPriceColumn;
    @FXML
    private TableColumn<ExportedProduct, Number> totalPriceColumn;

    private final ObservableList<ExportedProduct> observableList = FXCollections.observableArrayList();

    Product product;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.initialize()");

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().productName());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qty());
        outPriceColumn.setCellValueFactory(cellData -> cellData.getValue().outPrice());
        totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPrice());

        table.setItems(observableList);

        productNameField.setDisable(true);

    }

    public void SelectedProduct(Product product) {
        this.product = product;
        productNameField.setText(product.name());
        productOutPriceField.setText(product.outPriceString());
        productQtyField.requestFocus();

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
        new ExportDocuments().show();
    }

    @FXML
    private void clientFieldOnKeyReleased() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.clientFieldOnKeyReleased()");

        new ExportDocumentChange(document).Client(clientField.getText());

    }

    @FXML
    private void datePickerOnAction() throws IOException, SQLException {
//        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.datePickerOnAction()");
//
//        LocalDate date = datePicker.getValue();
//        System.out.println("Selected LocalDate: " + date);
//        ;
//
//        // new ImportDocumentChange(document).Date(MyDateConverter.formatDate(date));
        MyAlert.alertContent(0, " Пока Что не Работает, В РАЗРАБОТКЕ! :))))");

    }

    @FXML
    private void noteAreaOnKeyReleased() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.ImportDocuments.EditViewController.noteAreaOnKeyReleased()");

        System.out.println("Note : " + noteArea.getText());
        new ExportDocumentChange(document).Note(noteArea.getText());

    }

    @FXML
    private void clearSelectedProductButtonOnAction() throws IOException {
        product = null;
        productNameField.clear();
        productQtyField.clear();
    }

    @FXML
    private void exportSelectedProductButtonOnAction() throws IOException, SQLException {
        if (isProductSelected() && isQtyValid() && isPriceValid() && isProductQtyValidForExport()) {
            new ExportedProducts().Create(document.id(), product.id(), product.name(), Integer.parseInt(productQtyField.getText()), Double.valueOf(productOutPriceField.getText()), (Double.valueOf(productOutPriceField.getText()) * Integer.parseInt(productQtyField.getText())));
            product.minusQty(Integer.parseInt(productQtyField.getText()));
            document.change().PlusTotalPrice(Double.valueOf(productOutPriceField.getText()) * Integer.parseInt(productQtyField.getText()));
            productNameField.clear();
            productQtyField.clear();

            initDocument(new ExportDocuments().ExportDocument(document.id()));
        }
    }

    @FXML
    private void selectProductButtonOnAction() throws IOException, SQLException {

        new Products().showExportSelectView(this);

    }

    @FXML
    private void menuReturnOnAction() throws IOException, SQLException {
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.menuReturnOnAction()");
        System.out.println(table.getSelectionModel().getSelectedItem().toString());

        MyAlert.alertContent(0, " Пока Что не Работает, В РАЗРАБОТКЕ! :))))");

    }

    private void initDocument(ArrayList<ExportDocument> documents) throws SQLException {
        if (!documents.isEmpty()) {

            document = documents.get(0);
            initDocument(document);

        }
    }

    public void initDocument(ExportDocument document) throws SQLException {
        this.document = document;

        idField.setText(document.idString());
        clientField.setText(document.client());
        datePicker.setValue(MyDateConverter.asLocalDate(document.date()));
        noteArea.setText(document.note());
        documentTotalPriceField.setText(document.totalPriceString());

        productNameField.clear();
        productOutPriceField.clear();
        productQtyField.clear();

        observableList.clear();
        observableList.addAll(new ExportedProducts().List(document.id()));
    }

    private boolean isQtyValid() {
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.isQtyValid()");
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

    private boolean isProductQtyValidForExport() {
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.isProductQtyValidForExport()");

        if (product.qty() < Integer.parseInt(productQtyField.getText())) {
            System.out.println("false");
            MyAlert.alertContent(0, " Нет В Анбаре такое Кол. Товара! НЕТ НЕТ НЕТ :))))");
            return false;
        } else {
            return true;
        }
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

    private boolean isPriceValid() {
        System.out.println("com.stockcontrol.Documents.Export.ExportDocumentViewController.isPriceValid()");

        if (productOutPriceField.getText().trim().isEmpty() || productOutPriceField.getText() == null || productOutPriceField.getText().trim().length() == 0) {
            System.out.println("false");
            MyAlert.alertContent(0, " Введите Цену! НЕТ НЕТ НЕТ :))))");

            return false;

        } else {
            System.out.println("try parsing");
            try {
                Double.valueOf(productOutPriceField.getText());
                System.out.println("parsing ok");
                System.out.println("true");

                if (Double.valueOf(productOutPriceField.getText()) < 0) {
                    MyAlert.alertContent(0, " Введите Цену Не Меньше Нуля ! НЕТ НЕТ НЕТ :))))");
                    return false;
                }

                return true;

            } catch (NumberFormatException e) {
                System.out.println("parsing fail");
                System.out.println("false");

                MyAlert.alertContent(0, " Введите Цену! НЕТ НЕТ НЕТ :))))");

                return false;
            }
        }

    }

}
