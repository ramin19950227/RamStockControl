/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.LoginModule;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ramin
 */
public class LoginModule {

    public void showLoginView() throws IOException {

        Stage nStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        nStage.setScene(new Scene(root));
        nStage.setMaximized(true);
        nStage.setTitle("Qeydiyyat");
        nStage.show();

    }

    public void showRegistrationView() throws IOException {

        Stage nStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        nStage.setScene(new Scene(root));
        nStage.setMaximized(true);
        nStage.setTitle("Qeydiyyat");
        nStage.show();

    }
}
