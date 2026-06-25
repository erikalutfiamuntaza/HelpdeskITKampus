package view;

import database.ConnectionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserManagementForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    JButton btnTambah = new JButton("Tambah");
    JButton btnEdit = new JButton("Edit");
    JButton btnHapus = new JButton("Hapus");
    JButton btnKembali = new JButton("Kembali");

    public UserManagementForm() {

        setTitle("Kelola User");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Email");
        model.addColumn("Role");

        table = new JTable(model);

        loadData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButton = new JPanel();

        panelButton.add(btnTambah);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnKembali);

        add(panelButton, BorderLayout.SOUTH);

        // TAMBAH USER
        btnTambah.addActionListener(e -> {

            String nama = JOptionPane.showInputDialog(
                    this,
                    "Masukkan nama:"
            );

            if (nama == null || nama.trim().isEmpty())
                return;

            String email = JOptionPane.showInputDialog(
                    this,
                    "Masukkan email:"
            );

            if (email == null || email.trim().isEmpty())
                return;

            String password = JOptionPane.showInputDialog(
                    this,
                    "Masukkan password:"
            );

            if (password == null || password.trim().isEmpty())
                return;

            String role = JOptionPane.showInputDialog(
                    this,
                    "Masukkan role (admin/user):"
            );

            if (role == null || role.trim().isEmpty())
                return;

            try {

                Connection conn =
                        ConnectionDB.connect();

                String sql =
                        "INSERT INTO users (nama,email,password,role) VALUES (?,?,?,?)";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, nama);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, role);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "User berhasil ditambahkan!"
                );

                model.setRowCount(0);
                loadData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // EDIT USER
        btnEdit.addActionListener(e -> {

            int baris = table.getSelectedRow();

            if (baris == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih user terlebih dahulu!"
                );
                return;
            }

            int id = (int) model.getValueAt(baris, 0);

            String namaLama =
                    String.valueOf(
                            model.getValueAt(baris, 1)
                    );

            String emailLama =
                    String.valueOf(
                            model.getValueAt(baris, 2)
                    );

            String roleLama =
                    String.valueOf(
                            model.getValueAt(baris, 3)
                    );

            String nama = JOptionPane.showInputDialog(
                    this,
                    "Nama baru:",
                    namaLama
            );

            if (nama == null || nama.trim().isEmpty())
                return;

            String email = JOptionPane.showInputDialog(
                    this,
                    "Email baru:",
                    emailLama
            );

            if (email == null || email.trim().isEmpty())
                return;

            String role = JOptionPane.showInputDialog(
                    this,
                    "Role baru:",
                    roleLama
            );

            if (role == null || role.trim().isEmpty())
                return;

            try {

                Connection conn =
                        ConnectionDB.connect();

                String sql =
                        "UPDATE users SET nama=?, email=?, role=? WHERE id=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, nama);
                pst.setString(2, email);
                pst.setString(3, role);
                pst.setInt(4, id);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "User berhasil diubah!"
                );

                model.setRowCount(0);
                loadData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // HAPUS USER
        btnHapus.addActionListener(e -> {

            int baris = table.getSelectedRow();

            if (baris == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih user terlebih dahulu!"
                );
                return;
            }

            int konfirmasi =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Yakin ingin menghapus user?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if (konfirmasi != JOptionPane.YES_OPTION)
                return;

            int id =
                    (int) model.getValueAt(baris, 0);

            try {

                Connection conn =
                        ConnectionDB.connect();

                String sql =
                        "DELETE FROM users WHERE id=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setInt(1, id);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "User berhasil dihapus!"
                );

                model.setRowCount(0);
                loadData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // KEMBALI
        btnKembali.addActionListener(e -> {
            dispose();
            new DashboardAdmin();
        });

        setVisible(true);
    }

    private void loadData() {

        try {

            Connection conn =
                    ConnectionDB.connect();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM users"
                    );

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("role")
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserManagementForm();
    }
}