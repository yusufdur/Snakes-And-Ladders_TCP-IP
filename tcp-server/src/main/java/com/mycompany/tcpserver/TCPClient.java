/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcpserver;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yusuf.dur
 */
public class TCPClient {

    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 5000;
        try ( Socket clientSocket = new Socket(serverHost, serverPort)) {

            // Set up input and output streams for the client socket
            OutputStream clientOutput = clientSocket.getOutputStream();
            InputStream clientInput = clientSocket.getInputStream();

            // Send data to the server
            String message = "READY";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            System.out.println("Sending message to server: " + message);
            clientOutput.write(messageBytes);
            clientOutput.flush();
            
            Thread.sleep(5000);
            
//            // Read the response from the server
//            System.out.println("Waiting for response from server...");
//            byte[] buffer = new byte[1024];
//            int bytesRead = clientInput.read(buffer);
//            if (bytesRead == -1) {
//                System.out.println("Connection closed by server");
//            } else {
//                String response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
//                System.out.println("Received response from server: " + response);
//            }

        } catch (IOException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
