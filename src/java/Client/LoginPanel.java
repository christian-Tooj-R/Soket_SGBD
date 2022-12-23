package panel;

import javax.swing.JPanel;

import frame.MyFrame;

import javax.swing.*;

public class LoginPanel extends JPanel {
    MyFrame mf;
    JTextField login = new JTextField("Username");
    JTextField password = new JTextField("Password");

    public LoginPanel(MyFrame m) {
        this.mf = m;
        this.setLayout(null);
        JLabel lab = new JLabel("Admin_Login");
        lab.setBounds(10, 0, 200, 30);
        login.setBounds(10, 50, 200, 30);
        login.setName("log");
        // login.addMouseListener(mf.getMs());
        password.setBounds(10, 100, 200, 30);
        password.setName("mdp");
        // password.addMouseListener(mf.getMs());
        JButton button = new JButton("Connexion");
        button.setBounds(10, 150, 150, 20);
        button.addMouseListener(mf.getMs());

        this.add(button);
        this.add(lab);
        this.add(login);
        this.add(password);
    }

    public MyFrame getMf() {
        return mf;
    }

    public void setMf(MyFrame mf) {
        this.mf = mf;
    }

    public JTextField getLogin() {
        return login;
    }

    public void setLogin(JTextField login) {
        this.login = login;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

}