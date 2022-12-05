/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

/**
 *
 * @author Christian
 */
public class ClientMultliT {
    Socket socket;
    BufferedReader br;
    BufferedWriter bw;

    public ClientMultliT(Socket socket, BufferedReader br, BufferedWriter bw) {
        this.socket = socket;
        this.br = br;
        this.bw = bw;
    }
}
