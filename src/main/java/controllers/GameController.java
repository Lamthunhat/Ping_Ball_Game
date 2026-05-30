package controllers;

import javax.swing.*;
import models.*;
import views.GamePanel;
import utils.SoundPlayer;

/**
 * The GameController class controls the main game logic, including movement,
 * collision, and game state.
 */
public class GameController implements IGameController {
    private Ball ball;
    private Paddle paddle;
    private GameState gameState;
    private GamePanel gamePanel;
    private int lives = 3;

    /**
     * Initializes GameController with game objects.
     * 
     * @param ball      The ball
     * @param paddle    The paddle
     * @param gameState The game state
     * @param gamePanel The game interface panel
     */
    public GameController(Ball ball, Paddle paddle, GameState gameState, GamePanel gamePanel) {
        this.ball = ball;
        this.paddle = paddle;
        this.gameState = gameState;
        this.gamePanel = gamePanel;
    }

    /**
     * Sets the game interface panel.
     * 
     * @param gamePanel The game interface panel
     */
    @Override
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        if (this.gamePanel == null) {
            throw new IllegalArgumentException("GamePanel không được phép null");
        }
    }

    /**
     * Updates game state, including ball movement and collision checks.
     */
    @Override
    public void updateGame() {
        if (!gameState.isGameOver() && !gameState.isPaused()) {
            // Move ball and check boundaries
            ball.move(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);

            // Check collision with paddle
            if (ball.getBounds().intersects(paddle.getBounds())) {

                ball.setY(paddle.getY() - ball.getSize());

                ball.bounceVertical();
                if (ball.getDx() > 0)
                    ball.setDx(ball.getDx() + 0.5);
                if (ball.getDx() < 0)
                    ball.setDx(ball.getDx() - 0.5);
                if (ball.getDy() > 0)
                    ball.setDy(ball.getDy() + 0.5);
                if (ball.getDy() < 0)
                    ball.setDy(ball.getDy() - 0.5);
                gameState.setScore(gameState.getScore() + 1);
                System.out.println("x: " + ball.getDx());
                System.out.println("y: " + ball.getDy());

                SoundPlayer.playSound("/hit.wav");
            }

            // Check game over
            if (ball.getY() + ball.getSize() >= GameConstants.GAME_HEIGHT) {
                lives--;
                ball.reset();
                if (gameState.getScore() % 5 == 0) {
                    gameState.setScore(getLives() + 1);
                }
                if (lives == 0) {
                    gameState.setGameOver(true);
                    gameState.checkHighScore();
                    SoundPlayer.playSound("/game_over.wav");
                    SoundPlayer.stopBackgroundMusic(); // Stop background music when game over
                }
            }
        }
    }

    /**
     * Moves the paddle to the left.
     */
    @Override
    public void movePaddleLeft() {
        if (!gameState.isPaused()) {
            paddle.moveLeft();
        }
    }

    @Override
    public void movePaddleRight() {
        if (!gameState.isPaused()) {
            paddle.moveRight(GameConstants.GAME_WIDTH);
        }
    }

    /**
     * Starts a new game.
     */
    @Override
    public void startGame() {
        gameState.setScore(0);
        gameState.setGameOver(false);
        ball.reset();
        paddle.reset();
        lives = 3;
        // Do not call playBackgroundMusic as background music is already playing from GameWindow
    }

    /**
     * Resets the game to return to the menu.
     */
    @Override
    public void resetGame() {
        gameState.setGameOver(true);
        gameState.checkHighScore();
        ball.reset();
        paddle.reset();
        gamePanel.refresh();
        SoundPlayer.stopBackgroundMusic(); // Stop background music when returning to menu
    }

    /**
     * Exits the game after confirmation.
     */
    @Override
    public void exitGame() {
        int response = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát game?", "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            SoundPlayer.stopBackgroundMusic(); // Stop background music when exiting
            System.exit(0);
        }
    }

    @Override
    public void pauseGame() {
        if (!gameState.isPaused()) {
            gameState.setPaused(true);
            gamePanel.refresh();
            SoundPlayer.stopBackgroundMusic(); // Stop background music when paused
        }
    }

    @Override
    public void resumeGame() {
        if (gameState.isPaused()) {
            gameState.setPaused(false);
            gamePanel.refresh();
            SoundPlayer.playBackgroundMusic("/opensound.wav"); // Resume background music if sound is enabled
        }
    }

    public int getLives() {
        return lives;
    }
}