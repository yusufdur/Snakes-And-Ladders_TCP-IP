/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcpserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author yusuf.dur
 */
public class Server implements Runnable {

    int port;
    static ServerSocket serverSocket;
    boolean isListening;

    public Server(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(this.port);
            this.isListening = false;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Start() {
        this.isListening = true;
        this.run();
    }

    public void Stop() {
        this.isListening = false;
        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            while (this.isListening) {

                // Listen for incoming client connections
                System.out.println("Waiting for client connection on port " + this.port + "...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client " + clientSocket.getInetAddress().toString() + " on port " + clientSocket.getPort());

                // Set up input stream for the client socket
//                OutputStream clientOutput = clientSocket.getOutputStream();
                InputStream clientInput = clientSocket.getInputStream();

                // Read the incoming data from the client
                //System.out.println("Reading data from client...");
                byte[] buffer = new byte[1024];
                int bytesRead;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((bytesRead = clientInput.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                baos.flush();
                byte[] receivedBytes = baos.toByteArray();

                // Convert the received bytes to a string using UTF-8 encoding
                String receivedString = new String(receivedBytes, StandardCharsets.UTF_8);
                System.out.println("Received data from client: " + receivedString);

//                clientOutput.close();
                clientInput.close();
                clientSocket.close();
            }

//            // Send a response back to the client
//            String responseString = "Hello from server";
//            byte[] responseBytes = responseString.getBytes(StandardCharsets.UTF_8);
//            clientOutput.write(responseBytes);
//            clientOutput.flush();
//            System.out.println("Sent response to client: " + responseString);
        } catch (IOException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        }
    }

    // ya böyle bişey yapcam ya da değişecek 
    public void handleGames() {

    }

}
