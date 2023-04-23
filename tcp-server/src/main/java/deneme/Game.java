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
import java.util.List;

public class Game implements Runnable {

    
    private Player player1;
    private Player player2;
    private Server server;

    public Game( Player player1 , Player player2 , Server server) {

        this.player1 = player1;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("Game 1" +  " started with players " + player1.getId() + " and " + player2.getId());

        Player player1 = this.player1;
        Player player2 = this.player2;

        try {
            while (true) {
                String request1 = player1.handleRequest();
                this.handleRequest(player1, request1);

                if (isGameOver()) {
                    endGame();
                    break;
                }

                String request2 = player2.handleRequest();
                this.handleRequest(player2, request2);

                if (isGameOver()) {
                    endGame();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Player disconnected: " + e.getMessage());
            endGame();
        }
    }

    private void handleRequest(Player player, String request) throws IOException {
        // handle request from the player
        // ...

        // send response to the player
        String response = "";
        player.sendResponse(response);
    }

    private boolean isGameOver() {
        // determine if the game is over based on the game state
        // ...

        return false;
    }

    private void endGame() {
        // send game over message to the players
        player1.sendResponse("Game over");
        player2.sendResponse("Game over");

        // remove the game from the list of active games
        server.removeGame(this);

        // close the player sockets
        player1.close();
        player2.close();
    }
}
