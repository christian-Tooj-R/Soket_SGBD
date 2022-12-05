package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTraitement extends Thread {
    Socket socket;

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
                // PrintWriter response = new PrintWriter(out);
                System.out.println(req);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
