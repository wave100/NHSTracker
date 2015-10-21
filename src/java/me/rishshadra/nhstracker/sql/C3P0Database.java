/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish
 */
public class C3P0Database extends Database {

    static ComboPooledDataSource finalSource;

    static {
        System.out.println("C3P0 Pool Initializing..");
        try {
            ComboPooledDataSource c3combo = new ComboPooledDataSource();
            c3combo.setDriverClass("com.mysql.jdbc.Driver");
            c3combo.setJdbcUrl("jdbc:mysql://localhost:3306/nhsdb");
            c3combo.setUser("root");
            c3combo.setPassword("");
            c3combo.setMinPoolSize(5);
            c3combo.setInitialPoolSize(10);
            c3combo.setMaxPoolSize(15);
            c3combo.setAutomaticTestTable("c3test");
            c3combo.setMaxIdleTime(300);
            c3combo.setMaxConnectionAge(600);
            c3combo.setUnreturnedConnectionTimeout(360);
            c3combo.setDebugUnreturnedConnectionStackTraces(true);
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
