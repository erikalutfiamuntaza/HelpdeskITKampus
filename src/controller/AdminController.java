package controller;

import database.ConnectionDB;
import java.sql.*;

public class AdminController {


    public void lihatSemuaTicket(){


        try {


            Connection conn =
                    ConnectionDB.connect();



            String sql =
                    "SELECT * FROM tickets";



            Statement st =
                    conn.createStatement();



            ResultSet rs =
                    st.executeQuery(sql);



            while(rs.next()){


                System.out.println(
                        "ID : " + rs.getInt("id")
                );


                System.out.println(
                        "Judul : " + rs.getString("judul")
                );


                System.out.println(
                        "Kategori : " + rs.getString("kategori")
                );


                System.out.println(
                        "Status : " + rs.getString("status")
                );


                System.out.println("----------------");

            }



        }catch(Exception e){

            e.printStackTrace();

        }


    }

}