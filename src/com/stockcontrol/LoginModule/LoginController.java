package com.stockcontrol.LoginModule;

import com.ramlib.customFields.CustomPf;
import com.ramlib.customFields.CustomTf;
import com.stockcontrol.App;
import com.stockcontrol.MainView.MainView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ramin
 */
public class LoginController implements Initializable {

    //esas AnchorPane . MainStyle buna yuklenir
    @FXML
    private AnchorPane apMother;

    @FXML
    private AnchorPane apDesignPane;

    //istifadeci adi yazilan TextField
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    private Button btnUserNameFieldClear;
    @FXML
    private Button btnUserPasswordFieldClear;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hlCreateAccount;

    @FXML
    private Hyperlink hlDatabase;

    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cTF.clearTextFieldByButton(userNameField, btnUserNameFieldClear);
        cPF.clearPassFieldByButton(userPasswordField, btnUserPasswordFieldClear);
        BooleanBinding boolenBinding = userNameField.textProperty().isEmpty()
                .or(userPasswordField.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);
        userNameField.requestFocus();

        try {
            /// biz indi ne edeceyik
            // demeli eger 1-eded qeydiyyat varsa bazada burdan qeydiyyat mumkun olmasin ve qeydiyyat linkini sondurek
            if ((App.DB.executeQuery(("SELECT ID FROM User ORDER BY Id ASC LIMIT 1"))).next()) {
                //demeli qeydiyyat var
                //ne edirik ? -> linki not managed edirik
                hlCreateAccount.setManaged(false);

                //hetta qeydiyyat varsa DB setup -u da sondurek
                //mence men qurrashdirdiqdan sonra istifadeci niye deyishsin ki
                hlDatabase.setManaged(false);
            } else {
                //hlDbOnAction(new ActionEvent()); mysql da lazim olacaq
                new LoginModule().showRegistrationView();
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {
        Boolean inputValid = isInputValid();
        Boolean isUserValid;

        if (inputValid) {

            isUserValid = new LoginCRUD().login(userNameField.getText(), userPasswordField.getText());

            if (isUserValid) {

                App.stage.show();
                new MainView().show();

                Stage oldStage = (Stage) btnLogin.getScene().getWindow();
                oldStage.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("İstifadəçi adı və ya Şifrə Yanlışdır");
                alert.setHeaderText("Xəta : İstifadəçi adı və ya Şifrə Yanlışdır");
                alert.setContentText("İstifadəçi adı və ya Şifrə Yanlışdır \nYenidən cəhd edin");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }

        } else {

        }
    }

    private boolean isInputValid() {
        boolean validCondition;
        if (userNameField.getText().trim().isEmpty()
                || userPasswordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Xəta :");
            alert.setHeaderText("Xəta !!");
            alert.setContentText("Zəhmət Olmasa İstifadəçi adı və Şifrəni Daxil edin");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void hlCreateAnAccount(ActionEvent event) throws IOException, SQLException {

        ResultSet rs = App.DB.executeQuery(("SELECT Id FROM User ORDER BY Id ASC LIMIT 1"));
        try {
            if (rs.next()) {
                System.out.println(rs.getString(1));
                apMother.setOpacity(0.7);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Yeni Istifadeci Qeydiyyati Mumkun deyil \n ");
                alert.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    apMother.setOpacity(1.0);
                }
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        new LoginModule().showRegistrationView();
    }

    @FXML
    private void hlDbOnAction(ActionEvent event) {
        //DBUtil.showmySQLServerConfigView();
    }

}
