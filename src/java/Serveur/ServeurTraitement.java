package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import fonction.Fonction;
import serveur.MyPrint;

public class ServeurTraitement extends Thread {
    Socket socket;
    String srt;

    public ServeurTraitement(Socket s) {
        super();
        this.socket = s;
    }

    public void run() {
        try {
            while (true) {
                InputStream input = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                OutputStream out = socket.getOutputStream();
                BufferedReader lire = new BufferedReader(reader);
                String req = lire.readLine();
                MyPrint response = new MyPrint(out);
                Fonction fonction = new Fonction(out);
                fonction.fonction(req);
                fonction.print("\n");
                fonction.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
