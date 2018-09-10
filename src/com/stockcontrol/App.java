/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol;

import com.ramlib.alert.MyAlert;
import com.ramlib.database.access.AccessDB;
import com.stockcontrol.LoginModule.LoginModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ramin
 */
public class App extends Application {

    public static Stage stage;
    private static AppSceneController sceneController;
    public static AccessDB DB = new AccessDB("db.accdb");

    @Override
    public void start(Stage stage) throws Exception {
        App.stage = stage;

        App.stage.setTitle("Print Deluxe - GOLD Toner - Biznes Kontrol Sistemi");
        //App.stage.setFullScreen(true);
        App.stage.setMaximized(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppScene.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        sceneController = (AppSceneController) loader.getController();

        new LoginModule().showLoginView();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void SOON() {
        MyAlert.alertContent(0, " Пока Что не Работает, В РАЗРАБОТКЕ! :))))");
    }

    public static void show(Parent root) {
        sceneController.setCenter(root);
    }

}
