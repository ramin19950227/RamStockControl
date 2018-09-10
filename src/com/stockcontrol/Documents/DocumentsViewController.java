/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents;

import com.stockcontrol.Documents.Export.ExportDocuments;
import com.stockcontrol.Documents.ImportDocuments.ImportDocuments;
import com.stockcontrol.MainView.MainView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class DocumentsViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backButtonOnAction() throws IOException {
        new MainView().show();

    }

    @FXML
    private void importDocumentsButtonOnAction() throws IOException {
        new ImportDocuments().show();

    }

    @FXML
    private void exportDocumentsButtonOnAction() throws IOException {
        new ExportDocuments().show();

    }
}
