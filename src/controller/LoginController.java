package controller;

import database.ConnectionDB;
import java.sql.*;

public class LoginController {


    public String login(String email, String password) {

        String role = null;

        try {

            Connection conn = ConnectionDB.connect();

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";


            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, email);
            pst.setString(2, password);


            ResultSet rs = pst.executeQuery();


            if(rs.next()){

                role = rs.getString("role");

            }


        } catch(Exception e){

            e.printStackTrace();

        }


        return role;

    }

}