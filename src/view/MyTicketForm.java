package view;

import database.ConnectionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyTicketForm extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton btnEdit = new JButton("Edit");
    JButton btnKembali = new JButton("Kembali");

    public MyTicketForm() {

        setTitle("Tiket Saya");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel judul = new JLabel("DAFTAR TIKET SAYA", SwingConstants.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 18));
        add(judul, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("Deskripsi");
        model.addColumn("Status");
        model.addColumn("Tanggal");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bawah = new JPanel();
        bawah.add(btnEdit);
        bawah.add(btnKembali);
        add(bawah, BorderLayout.SOUTH);

        // Ambil data dari database
        try {

            Connection conn = ConnectionDB.connect();

            String sql = "SELECT id, judul, kategori, deskripsi, status, tanggal FROM tickets WHERE user_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 2); // sementara user mahasiswa

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getString("kategori"),
                        rs.getString("deskripsi"),
                        rs.getString("status"),
                        rs.getDate("tanggal")
                });

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnEdit.addActionListener(e -> {

            int baris = table.getSelectedRow();

            if (baris == -1) {
                JOptionPane.showMessageDialog(this,
                        "Pilih tiket yang ingin diedit!");
                return;
            }

            int id = (int) model.getValueAt(baris, 0);
            String status = model.getValueAt(baris, 4).toString();

            if (!status.equals("Open")) {
                JOptionPane.showMessageDialog(this,
                        "Tiket tidak bisa diedit karena sudah diproses!");
                return;
            }

            new EditTicketForm(id);

        });

        btnKembali.addActionListener(e -> {
            dispose();
            new UserDashboard();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyTicketForm();
    }
}