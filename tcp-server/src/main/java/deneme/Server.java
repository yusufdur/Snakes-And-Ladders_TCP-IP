/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme;

/**
 *
 * @author yusuf.dur
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    private static final int PORT = 5000;
    private static final int MAX_PLAYERS = 10;

    private ServerSocket serverSocket;
    private List<Player> waitingPlayers;
    private List<Player> readyPlayers;
    private List<Game> games;
    boolean isListening = false;

    public Server() {
        waitingPlayers = new ArrayList<>();
        readyPlayers = new ArrayList<>();
        games = new ArrayList<>();
    }

    public void start() {
        this.isListening = true;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (this.isListening) {

                System.out.println("Waiting for players...");
                Socket socket = serverSocket.accept();
                System.out.println("Player connected: " + socket);

                // create a new player thread and add it to the list of waitingPlayers
                Player player = new Player(waitingPlayers.size() + 1, socket, this);
                waitingPlayers.add(player);
                new Thread(player).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        this.isListening = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing server socket: " + e.getMessage());
        }

        for (Player player : waitingPlayers) {
            player.close();
        }
    }

    public void removePlayer(Player player) {
        waitingPlayers.remove(player);
        System.out.println("Player " + player.getId() + " disconnected.");

        // remove the player from the list of ready waitingPlayers
        removeReadyPlayer(player);

        // check if there are enough waitingPlayers to start a new game
        if (canStartGame()) {
            startGame();
        }
    }

    public boolean canStartGame() {
        return waitingPlayers.size() >= 2;
    }

    public void startGame() {
        /*
        // choose two random waitingPlayers from the list of waitingPlayers
        Random random = new Random();
        int index1 = random.nextInt(readyPlayers.size());
        int index2 = random.nextInt(readyPlayers.size());
        while (index2 == index1) {
            index2 = random.nextInt(readyPlayers.size());
        }
        Player player1 = waitingPlayers.get(index1);
        Player player2 = waitingPlayers.get(index2);

        // create a new game thread and add it to the list of games
        
         Game game = new Game(player1, player2, this);
        games.add(game);
        new Thread(game).start();
        
        // remove the waitingPlayers from the list of waitingPlayers
        waitingPlayers.remove(player1);
        waitingPlayers.remove(player2);

        System.out.println("Game started between players " + player1.getId() + " and " + player2.getId());
*/
    }

    public void removeGame(Game game) {
        games.remove(game);
        System.out.println("Game ended.");

        // check if there are enough waitingPlayers to start a new game
        if (canStartGame()) {
            startGame();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    void removeReadyPlayer(Player aThis) {

    }

    void addReadyPlayer(Player aThis) {
        this.readyPlayers.add(aThis);
    }
}
