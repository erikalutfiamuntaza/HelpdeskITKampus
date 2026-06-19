package view;

import javax.swing.*;

public class DashboardAdmin extends JFrame {

    public DashboardAdmin() {
        setTitle("Dashboard Admin");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Dashboard Admin", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardAdmin();
    }
}