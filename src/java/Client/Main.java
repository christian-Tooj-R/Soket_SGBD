package main;

import frame.Interface;
import frame.MyFrame;
import mouse.MyMouse;

public class Main {
    public static void main(String[] args) {
        // new MyFrame();
        new Interface(new MyMouse());
    }
}