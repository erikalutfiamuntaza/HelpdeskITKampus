package view;

import database.ConnectionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserManagementForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public UserManagementForm() {

        setTitle("Kelola User");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Email");
        model.addColumn("Role");

        table = new JTable(model);

        loadData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);
    }

    private void loadData() {

        try {

            Connection conn = ConnectionDB.connect();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users");

            System.out.println("Query berhasil");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nama")
                );

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