/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.LoginModule;

import com.stockcontrol.App;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ramin
 */
class LoginCRUD {

    public boolean login(String UsrName, String Password) {
        try {
            ResultSet rs = App.DB.executeQuery("select * from User where name='" + UsrName + "' and password='" + Password + "'");

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean registration(String userName, String userPassword, String fullName, Integer status) {

        try {
            ////////////////////////////////////////////MYSQL/////////////////////////////////////////////

//            String SQLQuery = SQL.UserSQL.REGISTRATON(userName, userPassword, fullName, status);
//            UserOperationLogger.logSQL(SQLQuery);
//            DBUtil.mySQLExecuteUpdate(SQLQuery);
            ////////////////////////////////////////////MS ACCESS/////////////////////////////////////////////
            App.DB.executeUpdate("INSERT INTO User VALUES (null, '" + userName + "','" + userPassword + "'," + status + " );");

            return true;

        } catch (SQLException ex) {
            return false;
        }

    }

}
