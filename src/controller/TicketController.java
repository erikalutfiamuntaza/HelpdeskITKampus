package controller;

import database.ConnectionDB;
import java.sql.*;

public class TicketController {


    public boolean createTicket(
            String judul,
            String kategori,
            String deskripsi,
            int userId
    ){

        boolean berhasil = false;


        try {


            Connection conn =
                    ConnectionDB.connect();



            String sql =
                    "INSERT INTO tickets " +
                            "(judul,kategori,deskripsi,status,tanggal,user_id) " +
                            "VALUES (?,?,?,?,?,?)";



            PreparedStatement pst =
                    conn.prepareStatement(sql);



            pst.setString(1, judul);

            pst.setString(2, kategori);

            pst.setString(3, deskripsi);

            pst.setString(4, "Pending");


            pst.setDate(
                    5,
                    new java.sql.Date(
                            System.currentTimeMillis())
            );


            pst.setInt(6, userId);



            pst.executeUpdate();



            berhasil = true;



            System.out.println(
                    "Ticket berhasil dibuat"
            );



        } catch(Exception e){


            e.printStackTrace();


        }


        return berhasil;


    }

}