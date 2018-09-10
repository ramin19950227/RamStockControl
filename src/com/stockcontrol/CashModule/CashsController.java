/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule;

import com.stockcontrol.CashModule.Cash.Cash;
import com.stockcontrol.CashModule.Cash.CashCRUD;
import com.stockcontrol.MainView.MainView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class CashsController implements Initializable {

    @FXML
    private ListView<Cash> listView;
    private final ObservableList<Cash> observableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listView.setItems(observableList);

        listView.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                try {
                    onMouceDoubleClicked();
                } catch (IOException ex) {
                    Logger.getLogger(CashsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        try {
            observableList.addAll(new CashCRUD().findAll());
        } catch (SQLException ex) {
            Logger.getLogger(CashsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void backButtonOnAction() throws IOException {
        new MainView().show();
    }

    void onMouceDoubleClicked() throws IOException {
        if (listView.getSelectionModel().getSelectedItem() != null) {

            listView.getSelectionModel().getSelectedItem().show();
        }
    }

}
