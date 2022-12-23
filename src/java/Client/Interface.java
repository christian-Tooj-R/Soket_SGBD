package frame;

import javax.swing.JFrame;

import mouse.MyMouse;
import panel.InterfacePanel;
import panel.PanelTab;

import java.awt.*;

public class Interface extends JFrame {
    MyMouse ms;
    InterfacePanel panel;

    public Interface(MyMouse m) {
        this.ms = m;
        panel = new InterfacePanel(this.ms);
        this.setLayout(null);
        this.setSize(1000, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();
        this.setFocusable(true);
        this.add(panel);
        this.setVisible(true);
    }

    public MyMouse getMs() {
        return ms;
    }

    public void setMs(MyMouse ms) {
        this.ms = ms;
    }

    public InterfacePanel getPanel() {
        return panel;
    }

    public void setPanel(InterfacePanel panel) {
        this.panel = panel;
    }
}
