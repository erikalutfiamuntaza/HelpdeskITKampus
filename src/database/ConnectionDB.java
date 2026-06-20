package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    public static Connection connect() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/helpdesk_it",
                    "root",
                    ""
            );

            System.out.println("Koneksi berhasil");

        } catch (Exception e) {

            System.out.println("Koneksi gagal: " + e.getMessage());

        }

        return conn;
    }
}