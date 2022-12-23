/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.Border;

import cli.Client;
import mouse.MyMouse;
import panel.*;

/**
 *
 * @author Christian
 */
public class MyFrame extends JFrame {
    MyMouse ms = new MyMouse(this);
    LoginPanel lp = new LoginPanel(this);

    public MyFrame(Client cl) {
        ms.setClient(cl);
        this.setSize(300, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();
        this.setFocusable(true);
        this.add(lp);
        this.setVisible(true);
    }

    public MyMouse getMs() {
        return ms;
    }

    public void setMs(MyMouse ms) {
        this.ms = ms;
    }

    public LoginPanel getLp() {
        return lp;
    }

    public void setLp(LoginPanel lp) {
        this.lp = lp;
    }

}
