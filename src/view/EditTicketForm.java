package view;

import database.ConnectionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditTicketForm extends JFrame {

    JTextField txtJudul = new JTextField();
    JTextArea txtDeskripsi = new JTextArea();
    JButton btnSimpan = new JButton("Simpan");

    int idTicket;

    public EditTicketForm(int id) {

        idTicket = id;

        setTitle("Edit Tiket");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbl1 = new JLabel("Judul");
        lbl1.setBounds(20,20,100,25);
        add(lbl1);

        txtJudul.setBounds(20,45,300,25);
        add(txtJudul);

        JLabel lbl2 = new JLabel("Deskripsi");
        lbl2.setBounds(20,80,100,25);
        add(lbl2);

        JScrollPane sp = new JScrollPane(txtDeskripsi);
        sp.setBounds(20,105,300,80);
        add(sp);

        btnSimpan.setBounds(120,210,120,30);
        add(btnSimpan);

        try {

            Connection conn = ConnectionDB.connect();

            String sql = "SELECT * FROM tickets WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1,idTicket);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){

                txtJudul.setText(rs.getString("judul"));
                txtDeskripsi.setText(rs.getString("deskripsi"));

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        btnSimpan.addActionListener(e->{

            try{

                Connection conn = ConnectionDB.connect();

                String sql = "UPDATE tickets SET judul=?, deskripsi=? WHERE id=?";

                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, txtJudul.getText());
                pst.setString(2, txtDeskripsi.getText());
                pst.setInt(3, idTicket);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Tiket berhasil diupdate!");

                dispose();

            }catch(Exception ex){
                ex.printStackTrace();
            }

        });

        setVisible(true);

    }
}