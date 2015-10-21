/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Rish
 */
public class C3P0Database extends Database {

    static ComboPooledDataSource finalSource;

    static {
        try {
            ComboPooledDataSource c3combo = new ComboPooledDataSource();
            c3combo.setDriverClass("com.mysql.jdbc.Driver");
            c3combo.setJdbcUrl("jdbc:mysql://localhost:3306/nhsdb");
            c3combo.setUser("root");
            c3combo.setPassword("");
            c3combo.setMinPoolSize(20);
            c3combo.setMaxPoolSize(100);
            c3combo.setAutomaticTestTable("c3test");
            c3combo.setTestConnectionOnCheckin(true);
            c3combo.setTestConnectionOnCheckout(true);

            finalSource = c3combo;
        } catch (PropertyVetoException ex) {
            Logger.getLogger(C3P0Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return finalSource.getConnection();
    }
}
