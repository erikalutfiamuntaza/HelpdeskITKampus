package view;

import javax.swing.*;
import database.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TicketForm extends JFrame {

    JTextField txtJudul = new JTextField();
    JComboBox<String> cbKategori = new JComboBox<>();
    JTextArea txtDeskripsi = new JTextArea();

    JButton btnSubmit = new JButton("Submit");
    JButton btnKembali = new JButton("Kembali");

    public TicketForm() {

        setTitle("Buat Tiket");
        setSize(450, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Judul
        JLabel lblJudul = new JLabel("Judul Keluhan");
        lblJudul.setBounds(30,20,120,25);
        add(lblJudul);

        txtJudul.setBounds(30,45,350,25);
        add(txtJudul);

        // Kategori
        JLabel lblKategori = new JLabel("Kategori");
        lblKategori.setBounds(30,80,120,25);
        add(lblKategori);

        cbKategori.addItem("Jaringan");
        cbKategori.addItem("Hardware");
        cbKategori.addItem("Software");
        cbKategori.addItem("Lainnya");
        cbKategori.setBounds(30,105,350,25);
        add(cbKategori);

        // Deskripsi
        JLabel lblDeskripsi = new JLabel("Deskripsi");
        lblDeskripsi.setBounds(30,140,120,25);
        add(lblDeskripsi);

        JScrollPane scroll = new JScrollPane(txtDeskripsi);
        scroll.setBounds(30,165,350,100);
        add(scroll);

        // Tombol
        btnSubmit.setBounds(70,300,120,35);
        add(btnSubmit);

        btnKembali.setBounds(220,300,120,35);
        add(btnKembali);

        // Submit
        btnSubmit.addActionListener(e -> {

            String judul = txtJudul.getText();
            String kategori = cbKategori.getSelectedItem().toString();
            String deskripsi = txtDeskripsi.getText();

            if(judul.isEmpty() || deskripsi.isEmpty()){

                JOptionPane.showMessageDialog(this,
                        "Semua data harus diisi!");

            }else{

                try{

                    Connection conn = ConnectionDB.connect();

                    String sql = "INSERT INTO tickets (judul, kategori, deskripsi, status, tanggal, user_id) VALUES (?, ?, ?, ?, CURDATE(), ?)";

                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setString(1, judul);
                    pst.setString(2, kategori);
                    pst.setString(3, deskripsi);
                    pst.setString(4, "Open");
                    pst.setInt(5, 2);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this,
                            "Tiket berhasil dibuat!");

                    txtJudul.setText("");
                    txtDeskripsi.setText("");
                    cbKategori.setSelectedIndex(0);

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        });

        // Kembali
        btnKembali.addActionListener(e -> {
            dispose();
            new UserDashboard();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new TicketForm();
    }
}