package view;

import database.ConnectionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateStatusForm extends JFrame {

    JTable table;
    DefaultTableModel model;

    JButton updateButton = new JButton("Update Status");
    JButton btnKembali = new JButton("Kembali");

    public UpdateStatusForm() {

        setTitle("Update Status Tiket");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("Status");

        table = new JTable(model);

        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        panel.add(updateButton);
        panel.add(btnKembali);

        add(panel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updateStatus());

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
                    st.executeQuery("SELECT * FROM tickets");

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getString("kategori"),
                        rs.getString("status")
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih tiket terlebih dahulu!"
            );
            return;
        }

        int id = (int) table.getValueAt(row, 0);

        String[] pilihan = {
                "Open",
                "In Progress",
                "Resolved"
        };

        String statusBaru =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Pilih status baru:",
                        "Update Status",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        pilihan,
                        pilihan[0]
                );

        if (statusBaru == null) {
            return;
        }

        try {

            Connection conn =
                    ConnectionDB.connect();

            Statement st =
                    conn.createStatement();

            st.executeUpdate(
                    "UPDATE tickets SET status='"
                            + statusBaru +
                            "' WHERE id=" + id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Status berhasil diupdate!"
            );

            model.setRowCount(0);
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateStatusForm();
    }
}