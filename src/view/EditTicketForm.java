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
    JButton btnBatal = new JButton("Batal");

    int idTicket;

    public EditTicketForm(int id) {

        idTicket = id;

        setTitle("Edit Tiket");
        setSize(400, 320);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl1 = new JLabel("Judul");
        lbl1.setBounds(20, 20, 100, 25);
        add(lbl1);

        txtJudul.setBounds(20, 45, 300, 25);
        add(txtJudul);

        JLabel lbl2 = new JLabel("Deskripsi");
        lbl2.setBounds(20, 80, 100, 25);
        add(lbl2);

        JScrollPane sp = new JScrollPane(txtDeskripsi);
        sp.setBounds(20, 105, 300, 80);
        add(sp);

        btnSimpan.setBounds(60, 220, 120, 30);
        add(btnSimpan);

        btnBatal.setBounds(200, 220, 120, 30);
        add(btnBatal);

        try {

            Connection conn = ConnectionDB.connect();

            String sql =
                    "SELECT * FROM tickets WHERE id=?";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setInt(1, idTicket);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtJudul.setText(
                        rs.getString("judul")
                );

                txtDeskripsi.setText(
                        rs.getString("deskripsi")
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnSimpan.addActionListener(e -> {

            String judul =
                    txtJudul.getText().trim();

            String deskripsi =
                    txtDeskripsi.getText().trim();

            if (judul.isEmpty() ||
                    deskripsi.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semua data harus diisi!"
                );

                return;
            }

            try {

                Connection conn =
                        ConnectionDB.connect();

                String sql =
                        "UPDATE tickets SET judul=?, deskripsi=? WHERE id=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, judul);
                pst.setString(2, deskripsi);
                pst.setInt(3, idTicket);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Tiket berhasil diupdate!"
                );

                dispose();
                new MyTicketForm();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        btnBatal.addActionListener(e -> {
            dispose();
            new MyTicketForm();
        });

        setVisible(true);
    }
}