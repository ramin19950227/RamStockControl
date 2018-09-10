/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule.Cash;

import com.ramlib.alert.MyAlert;
import com.stockcontrol.App;
import com.stockcontrol.CashModule.CashItem.CashItemCRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class CashCRUD {

    public void insert(Cash cash) throws SQLException {
        App.SOON();
    }

    public ArrayList<Cash> findAll() throws SQLException {

        ArrayList<Cash> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM Cash ORDER BY `id` ASC");

        while (rs.next()) {

            list.add(new Cash(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new CashItemCRUD().findAllByCashID(rs.getInt("ID"))
            ));
        }
        return list;

    }

    public ArrayList<Cash> findAll(int id) throws SQLException {

        ArrayList<Cash> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM Cash WHERE id=" + id);

        while (rs.next()) {

            list.add(new Cash(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getString("note"),
                    rs.getDouble("totalPrice"),
                    new CashItemCRUD().findAllByCashID(rs.getInt("ID"))
            ));
        }
        return list;

    }

    public Cash find(int cashId) {

        try {
            ResultSet rs = DB.executeQuery("SELECT * FROM Cash WHERE id=" + cashId);

            if (rs.next()) {

                return new Cash(
                        rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("note"),
                        rs.getDouble("totalPrice"),
                        new CashItemCRUD().findAllByCashID(rs.getInt("ID"))
                );

            } else {
                return null;
            }
        } catch (SQLException ex) {
            MyAlert.alertContent(ex.getErrorCode(), ex.getMessage());
            return null;
        }
    }

    public void UPDATE_totalPrice(int id, double newValue) {
        try {
            DB.executeUpdate("UPDATE Cash SET "
                    + "`totalPrice`=" + newValue + ""
                    + " WHERE  `id`=" + id + ";"
            );
        } catch (SQLException ex) {
            MyAlert.alertContent(ex.getErrorCode(), ex.getMessage());
        }
    }

    public void UPDATE_note(int id, String newValue) {
        try {
            DB.executeUpdate("UPDATE Cash SET "
                    + "`note`='" + newValue + "'"
                    + " WHERE  `id`=" + id + ";"
            );
        } catch (SQLException ex) {
            MyAlert.alertContent(ex.getErrorCode(), ex.getMessage());
        }
    }

    public void UPDATE_name(int id, String newValue) {
        try {
            DB.executeUpdate("UPDATE Cash SET "
                    + "`name`='" + newValue + "'"
                    + " WHERE  `id`=" + id + ";"
            );
        } catch (SQLException ex) {
            MyAlert.alertContent(ex.getErrorCode(), ex.getMessage());
        }
    }

}
