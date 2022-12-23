package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import table.*;
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
                ObjectOutputStream objout = new ObjectOutputStream(socket.getOutputStream());
                InputStream input = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                OutputStream out = socket.getOutputStream();
                BufferedReader lire = new BufferedReader(reader);
                String req = lire.readLine();
                Fonction fonction = new Fonction(out);

                fonction.fonction(req.split("///")[0], req.split("///")[1]);
                fonction.print("\n");
                fonction.flush();
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
