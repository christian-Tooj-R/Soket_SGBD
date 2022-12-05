package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import fonction.Fonction;

public class Client {

    public static void main(String[] args) {

        try {
            Socket clientSocket;
            BufferedReader in;
            PrintWriter out;
            Scanner sc = new Scanner(System.in);
            clientSocket = new Socket(new Fonction().getIp(), new Fonction().getPort());
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread envoyer = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        System.out.print("\nChriSql>");
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }

            });
            envoyer.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while (msg != null) {
                            System.out.print("\n" + msg);
                            msg = in.readLine();
                        }
                        System.out.println("Serveur déconecté");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}