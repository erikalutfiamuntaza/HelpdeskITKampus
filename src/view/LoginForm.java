package view;

import javax.swing.*;
import java.sql.*;
import database.ConnectionDB;
import view.DashboardAdmin;
import view.UserManagementForm;

public class LoginForm extends JFrame {

    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginForm() {

        setTitle("Login Helpdesk IT");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 20, 80, 20);
        add(emailLabel);

        emailField.setBounds(100, 20, 150, 20);
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 20);
        add(passLabel);

        passwordField.setBounds(100, 60, 150, 20);
        add(passwordField);

        loginButton.setBounds(100, 100, 100, 30);
        add(loginButton);

        loginButton.addActionListener(e -> {

            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Connection conn = ConnectionDB.connect();

                String sql = "SELECT * FROM users WHERE email=? AND password=?";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, email);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    String role = rs.getString("role");

                    JOptionPane.showMessageDialog(this, "Login berhasil!");

                    if (role.equals("admin")) {
                        new DashboardAdmin().setVisible(true);
                    } else {
                        new UserManagementForm().setVisible(true);
                    }

                    this.dispose(); // nutup login form

                } else {
                    JOptionPane.showMessageDialog(this, "Login gagal!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}