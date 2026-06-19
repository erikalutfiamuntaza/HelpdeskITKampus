package view;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    JButton btnBuatTiket = new JButton("Buat Tiket");
    JButton btnTiketSaya = new JButton("Tiket Saya");
    JButton btnLogout = new JButton("Logout");

    public UserDashboard() {

        setTitle("Dashboard Mahasiswa");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel judul = new JLabel("DASHBOARD MAHASISWA");
        judul.setFont(new Font("Arial", Font.BOLD, 18));
        judul.setBounds(70, 20, 250, 30);
        add(judul);

        btnBuatTiket.setBounds(100, 80, 180, 35);
        add(btnBuatTiket);

        btnTiketSaya.setBounds(100, 130, 180, 35);
        add(btnTiketSaya);

        btnLogout.setBounds(100, 180, 180, 35);
        add(btnLogout);

        // Tombol Buat Tiket
        btnBuatTiket.addActionListener(e -> {
            dispose();
            new TicketForm();
        });

        // Tombol Tiket Saya
        btnTiketSaya.addActionListener(e -> {
            dispose();
            new MyTicketForm();
        });

        // Tombol Logout
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserDashboard();
    }
}