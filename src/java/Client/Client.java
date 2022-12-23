package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import frame.*;
import table.*;
import fonc.Fonction;
import java.io.*;

public class Client {
    Socket s;
    BufferedReader in;
    PrintWriter out;
    ObjectInputStream oi;
    Table tab;

    public Client() {
        try {
            s = new Socket(new Fonction().getIp(), new Fonction().getPort());
            oi = new ObjectInputStream(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            Client client = new Client();

            Thread envoyer = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        System.out.print("\nChriSql>");
                        msg = sc.nextLine();
                        if (msg.equalsIgnoreCase("isAdmin")) {
                            new MyFrame(client);
                        }
                        client.getOut().println(msg + "///" + "no");
                        client.getOut().flush();
                    }

                }

            });
            envoyer.start();

            String msg = client.getIn().readLine();
            while (msg != null) {
                System.out.print("\n" + msg);
                msg = client.getIn().readLine();
            }
            System.out.println("Serveur déconnecté");
            client.getOut().close();
            client.getS().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public ObjectInputStream getOi() {
        return oi;
    }

    public void setOi(ObjectInputStream oi) {
        this.oi = oi;
    }

    public Table getTab() {
        return tab;
    }

    public void setTab(Table tab) {
        this.tab = tab;
    }

}