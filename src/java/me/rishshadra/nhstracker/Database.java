/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import javax.sql.DataSource;

/**
 *
 * @author rshad
 */
public class Database {

    static InitialContext initialContext;
    static DataSource dataSource;
    static boolean localConnection;

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            initialContext = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/nhsdb");
        } catch (NamingException ex) {
            System.out.println("Local connection detected.");
            localConnection = true;
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {
        //System.out.println("Returning Connection");
        if (localConnection) {
            return connectToLocalDB();
        } else {
            return dataSource.getConnection();
        }
    }

    public static Connection connectToLocalDB() {
        Connection c = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/nhsdb?user=root");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }
}
