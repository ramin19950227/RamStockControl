/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcontrol.CashModule.CashItem;

import com.ramlib.alert.MyAlert;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static com.stockcontrol.App.DB;

/**
 *
 * @author Ramin
 */
public class CashItemCRUD {

    public void INSERT(CashItem item) {

        try {
            DB.executeUpdate("INSERT INTO CashItem "
                    + "VALUES (NULL,"
                    + "" + item.getCashId() + ","
                    + "'" + item.getOperation() + "',"
                    + "" + item.getPrice() + ","
                    + "'" + item.getNote() + "',"
                    + "'" + item.getDate().getTime() + "');");

        } catch (SQLException ex) {
            MyAlert.alertContent(ex.getErrorCode(), ex.getMessage());
        }
    }

    public ArrayList<CashItem> findAll() throws SQLException {

        ArrayList<CashItem> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM CashItem ORDER BY `id` ASC");

        while (rs.next()) {

            list.add(new CashItem(
                    rs.getInt("ID"),
                    rs.getInt("cashId"),
                    rs.getString("operation"),
                    rs.getDouble("price"),
                    rs.getString("note"),
                    rs.getTimestamp("date"))
            );
        }

        return list;

    }

    public ArrayList<CashItem> findAllByCashID(int cashId) throws SQLException {

        ArrayList<CashItem> list = new ArrayList<>();

        ResultSet rs = DB.executeQuery("SELECT * FROM CashItem WHERE cashId=" + cashId);

        while (rs.next()) {

            list.add(new CashItem(
                    rs.getInt("ID"),
                    rs.getInt("cashId"),
                    rs.getString("operation"),
                    rs.getDouble("price"),
                    rs.getString("note"),
                    new Timestamp(Long.valueOf(rs.getString("date"))))
            );
        }

        return list;

    }

//    public CashItem find(int id) throws SQLException {
//
//        CashItem cashItem;
//
//        ResultSet rs = dbConnect.executeQuery("SELECT * FROM CashItem ORDER BY `id` ASC WHERE id=" + id);
//
//        while (rs.next()) {
//
//            cashItem = new CashItem(
//                    rs.getInt("ID"),
//                    rs.getInt("cashId"),
//                    rs.getString("operation"),
//                    rs.getDouble("price"),
//                    rs.getString("note"),
//                    rs.getDate("date")
//            );
//            return cashItem;
//
//        }else {
//            return new CashItem(-1, -1, "Not Find", -1, "Not Find", new Date());
//        }
//
//    }
}
