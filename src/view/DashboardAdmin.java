package view;

import javax.swing.*;
import controller.AdminController;


public class DashboardAdmin extends JFrame {


    JButton userButton = new JButton("Kelola User");

    JButton statusButton = new JButton("Update Status Tiket");


    public DashboardAdmin() {


        setTitle("Dashboard Admin");

        setSize(400,200);

        setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);



        userButton.setBounds(100,30,200,30);

        statusButton.setBounds(100,80,200,30);



        add(userButton);

        add(statusButton);




        userButton.addActionListener(e -> {


            new UserManagementForm();


        });





        statusButton.addActionListener(e -> {


            AdminController ac =
                    new AdminController();



            ac.lihatSemuaTicket();



            JOptionPane.showMessageDialog(
                    this,
                    "Data tiket tampil di console"
            );


        });




        setVisible(true);


    }




    public static void main(String[] args){


        new DashboardAdmin();


    }

}