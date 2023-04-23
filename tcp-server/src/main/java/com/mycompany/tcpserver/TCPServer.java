package com.mycompany.tcpserver;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

/**
 *
 * @author yusuf.dur
 */
public class TCPServer {

    public static void main(String[] args) {
        Server s = new Server(5000);
        s.Start();

        /*
        int port = 5000;
        try ( ServerSocket serverSocket = new ServerSocket(port)) {

            while (!serverSocket.isClosed()) {
                
                // Listen for incoming client connections
                System.out.println("Waiting for client connection on port " + port + "...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client " + clientSocket.getInetAddress().toString() + " on port " + clientSocket.getPort());

                // Set up input and output streams for the client socket
                OutputStream clientOutput = clientSocket.getOutputStream();
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
                
                
                clientOutput.close();
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
        */

         
    }

}
