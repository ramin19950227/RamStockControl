/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.Documents;

import com.stockcontrol.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Ramin
 */
public class DocumentsView {

    public void show() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DocumentsView.fxml"));
        App.show(root);

    }
}
