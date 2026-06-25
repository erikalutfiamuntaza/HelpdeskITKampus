package view;

import javax.swing.*;

public class DashboardAdmin extends JFrame {

    JButton userButton = new JButton("Kelola User");
    JButton statusButton = new JButton("Update Status Tiket");
    JButton logoutButton = new JButton("Logout");

    public DashboardAdmin() {

        setTitle("Dashboard Admin");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        userButton.setBounds(100, 30, 200, 30);
        statusButton.setBounds(100, 80, 200, 30);
        logoutButton.setBounds(100, 130, 200, 30);

        add(userButton);
        add(statusButton);
        add(logoutButton);

        userButton.addActionListener(e -> {
            new UserManagementForm();
        });

        statusButton.addActionListener(e -> {
            new UpdateStatusForm();
        });

        logoutButton.addActionListener(e -> {

            int pilihan = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin logout?",
                    "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (pilihan == JOptionPane.YES_OPTION) {
                dispose();
                new LoginForm();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardAdmin();
    }
}