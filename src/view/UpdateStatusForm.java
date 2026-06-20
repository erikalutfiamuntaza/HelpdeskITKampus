package view;

import database.ConnectionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateStatusForm extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton updateButton = new JButton("Update Status");

    public UpdateStatusForm() {

        setTitle("Update Status Tiket");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Kategori");
        model.addColumn("Status");

        table = new JTable(model);

        loadData();

        add(new JScrollPane(table));

        add(updateButton, java.awt.BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updateStatus());

        setVisible(true);
    }

    private void loadData() {

        try {

            Connection conn = ConnectionDB.connect();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM tickets");

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
            JOptionPane.showMessageDialog(this, "Pilih tiket terlebih dahulu!");
            return;
        }

        int id = (int) table.getValueAt(row, 0);

        String statusBaru = JOptionPane.showInputDialog(
                this,
                "Masukkan status baru (OPEN / PROCESS / DONE)"
        );

        try {

            Connection conn = ConnectionDB.connect();

            Statement st = conn.createStatement();

            st.executeUpdate(
                    "UPDATE tickets SET status='" +
                            statusBaru +
                            "' WHERE id=" + id
            );

            JOptionPane.showMessageDialog(this, "Status berhasil diupdate!");

            dispose();
            new UpdateStatusForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}