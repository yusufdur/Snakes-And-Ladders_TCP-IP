/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme;

/**
 *
 * @author yusuf.dur
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {

    private int id;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;
    private boolean readyToPlay;

    public Player(int id, Socket socket, Server server) throws IOException {
        this.id = id;
        this.socket = socket;
        this.server = server;
        this.readyToPlay = false;

        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

         this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();
                System.out.println("reques : " + request);
                // handle request from the player
                // ...
                if (request.equals("READY")) {
                    // mark the player as ready to play
                    readyToPlay = true;

                    // add the player to the list of ready players
                    server.addReadyPlayer(Player.this);

                    // wait until there are enough players to start a game
                    while (!server.canStartGame()) {
                        Thread.sleep(1000);
                    }

                    // start a new game
                    server.startGame();

                    // remove the player from the list of ready players
                    server.removeReadyPlayer(Player.this);

                    // mark the player as not ready to play
                    readyToPlay = false;

                    // exit the thread
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Player " + id + " disconnected: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    // this is gonna be used in run() function 
    public String handleRequest() {
        // this is always gonna read and response 

        return "";
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing socket: " + e.getMessage());
        }
    }

    public boolean isReadyToPlay() {
        return readyToPlay;
    }

    public int getId() {
        return id;
    }

    public void sendResponse(String request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
