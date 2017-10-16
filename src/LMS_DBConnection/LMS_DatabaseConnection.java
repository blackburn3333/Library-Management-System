/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CRAZY
 */
public class LMS_DatabaseConnection {

    public static Connection DBConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Connection");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/readwithus", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

}
