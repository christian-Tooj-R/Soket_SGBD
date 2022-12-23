package panel;

import javax.swing.*;

import mouse.MyMouse;
import java.awt.*;

public class InterfacePanel extends JPanel {

    JLabel lab = new JLabel("Executer une requete");
    JTextField requete = new JTextField();
    MyMouse ms;

    public InterfacePanel(MyMouse m) {
        this.setBounds(0, 0, 1000, 310);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.ms = m;
        this.setLayout(null);
        lab.setBounds(50, 50, 400, 50);

        requete.setBounds(50, 110, 700, 50);
        requete.setName("log");
        JButton button = new JButton("Executer");
        button.setBounds(50, 180, 150, 30);
        button.addMouseListener(this.ms);

        this.add(lab);
        this.add(requete);
        this.add(button);
    }

    public JLabel getLab() {
        return lab;
    }

    public void setLab(JLabel lab) {
        this.lab = lab;
    }

    public JTextField getRequete() {
        return requete;
    }

    public void setRequete(JTextField requete) {
        this.requete = requete;
    }

    public MyMouse getMs() {
        return ms;
    }

    public void setMs(MyMouse ms) {
        this.ms = ms;
    }
}
