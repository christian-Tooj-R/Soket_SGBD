package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import fonction.Fonction;
import java.util.Scanner;

public class Serveur extends Thread {
    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ServerSocket ss = new ServerSocket(new Connecte().getPort());
            System.out.println("En attente de Client...");
            while (true) {
                Socket s = ss.accept();
                System.out.println("client connecte");
                new ServeurTraitement(s).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] test) {
        new Serveur().start();
    }
}