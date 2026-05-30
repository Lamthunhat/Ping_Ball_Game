package controllers;

/**
 * IGameController interface defines the game control methods.
 */
public interface IGameController {
    void updateGame(); // Update game state

    void movePaddleLeft(); // Move paddle left

    void movePaddleRight(); // Move paddle right

    void startGame(); // Start new game

    void resetGame(); // Reset game

    void pauseGame();

    void resumeGame();

    void exitGame(); // Exit game

    void setGamePanel(views.GamePanel gamePanel); // Set interface panel
}