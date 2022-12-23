/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mouse;

import frame.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import cli.*;

import fonc.Fonction;

/**
 *
 * @author Christian
 */
public class MyMouse implements MouseListener {
    MyFrame mf;
    Interface frame;
    String message;
    Client client;

    public MyMouse(MyFrame m) {
        this.mf = m;
    }

    /*** pressing && handling */
    public MyMouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (((JButton) e.getSource()).getText().equals("Connexion")) {
            if (mf.getLp().getLogin().getText().equalsIgnoreCase("admin")
                    && mf.getLp().getPassword().getText().equals("admin")) {
                frame = new Interface(this.mf.getMs());
            }
        }
        if (((JButton) e.getSource()).getText().equals("Executer")) {
            try {
                client.getOut().println(this.frame.getPanel().getRequete().getText() + "///" + "yes");
                client.getOut().flush();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public MyFrame getMf() {
        return mf;
    }

    public void setMf(MyFrame mf) {
        this.mf = mf;
    }

    public Interface getFrame() {
        return frame;
    }

    public void setFrame(Interface frame) {
        this.frame = frame;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}