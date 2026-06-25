package view;

import javax.swing.*;
import controller.LoginController;

public class LoginForm extends JFrame {

    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginForm() {

        setTitle("Login Helpdesk IT");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

            LoginController controller = new LoginController();
            String role = controller.login(email, password);

            if (role != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login berhasil!"
                );

                if (role.equals("admin")) {

                    new DashboardAdmin().setVisible(true);

                } else {

                    new UserDashboard().setVisible(true);

                }

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Email atau Password salah!"
                );
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}