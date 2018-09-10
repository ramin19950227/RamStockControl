/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule.Cash;

import com.ramlib.Validator.Validator;
import com.ramlib.alert.MyAlert;
import com.stockcontrol.CashModule.CashItem.CashItem;
import com.stockcontrol.CashModule.Cashs;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class CashController implements Initializable {

    private Cash cash;

    @FXML
    private TextField cashId;

    @FXML
    private TextField cashName;

    @FXML
    private TextField cashTotalPrice;

    @FXML
    private TextArea cashNote;

    @FXML
    private TextField operationPrice;

    @FXML
    private TextField operationNote;

    @FXML
    private TableView<CashItem> table;

    @FXML
    private TableColumn<CashItem, Number> idColumn;
    @FXML
    private TableColumn<CashItem, String> operationColumn;
    @FXML
    private TableColumn<CashItem, Number> priceColumn;
    @FXML
    private TableColumn<CashItem, String> noteColumn;
    @FXML
    private TableColumn<CashItem, String> dateColumn;

    private final ObservableList<CashItem> observableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        operationColumn.setCellValueFactory(cellData -> cellData.getValue().operationProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        //bURDA QALDIM.
        //ELEMEK ISTEDIYIM PRIMECANIY SUTUNU ILE OPISANIYA SUTUNUNU AYIRMAQDIR
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        table.setItems(observableList);
        table.setEditable(true);

        noteColumn.setOnEditCommit((TableColumn.CellEditEvent<CashItem, String> t) -> {

            CashItem cashItem = t.getRowValue();
            System.out.println(t.getNewValue());

            // cedvelimizi bazadan yeniledikden sonra mehsulumuzu 
            //  select edirik, yani secirik ki Edit panelinede dushsun yeni melumatlar
            table.getSelectionModel().select(t.getRowValue());
        });

    }

    void Cash(Cash cash) {
        this.cash = cash;

        //cashId.setText(String.valueOf(cash.getId()));
        cashName.setText(String.valueOf(cash.getName()));
        //cashNote.setText(String.valueOf(cash.getNote()));
        cashTotalPrice.setText(String.valueOf(cash.getTotalPrice()));

        observableList.clear();
        observableList.addAll(cash.getCashItems());

    }

    @FXML
    private void backButtonOnAction() throws IOException {
        new Cashs().show();
    }

    @FXML
    private void importButtonOnAction() throws IOException, SQLException {
        if (!isInputsValid()) {
            MyAlert.alertContent(-1, "Kecerli MEbleg Daxil Edin");
            return;
        }

        cash.Import(new CashItem(0, cash.getId(), "ПРИХОД", Double.valueOf(operationPrice.getText()), operationNote.getText(), new Date()));

        Cash(new CashCRUD().findAll(cash.getId()).get(0));
    }

    @FXML
    private void exportButtonOnAction() throws IOException, SQLException {
        if (!isInputsValid()) {
            MyAlert.alertContent(-1, "Kecerli MEbleg Daxil Edin");
            return;
        }

        if (cash.getTotalPrice() - Double.valueOf(operationPrice.getText()) < 0) {
            MyAlert.alertContent(-1, "Kassada O Qeder Pul Yoxdur");

        } else {
            cash.Export(new CashItem(
                    0,
                    cash.getId(),
                    "РАСХОД",
                    Double.valueOf(operationPrice.getText()),
                    operationNote.getText(),
                    new Date()));
        }

        Cash(new CashCRUD().findAll(cash.getId()).get(0));
    }

    @FXML
    private void onNameFieldKeyReleased() {

        new CashCRUD().UPDATE_name(cash.getId(), cashName.getText());
    }

    @FXML
    private void onNoteFieldKeyReleased() {
        new CashCRUD().UPDATE_note(cash.getId(), cashNote.getText());
    }

    boolean isInputsValid() {
        if (Validator.isValidDouble(operationPrice) && Double.valueOf(operationPrice.getText()) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
