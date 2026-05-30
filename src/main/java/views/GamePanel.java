package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import controllers.GameController;
import models.*;

public class GamePanel extends JPanel {
    private final Ball ball;
    private BufferedImage ballImage, paddleImage; // Contains sprites for the grenade (ball) and player (paddle)

    private BufferedImage spriteImg;

    private final Paddle paddle;
    private final GameState gameState;
    private final GameController controller;
    private final GameWindow gameWindow;

    /**
     * Initializes GamePanel with game objects.
     *
     * @param ball       The ball
     * @param paddle     The paddle
     * @param gameState  The game state
     * @param controller The game controller
     */
    public GamePanel(Ball ball, Paddle paddle, GameState gameState, GameController controller, GameWindow gameWindow) {
        this.ball = ball;
        this.paddle = paddle;
        this.gameState = gameState;
        this.controller = controller;
        this.gameWindow = gameWindow;

        setPreferredSize(new Dimension(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        requestFocusInWindow();

        try {
            this.spriteImg = ImageIO.read(getClass().getResourceAsStream("/pic_transparent.png"));
            if (this.spriteImg == null) {
                System.err.println("Không tải được hình ảnh: /pic_transparent.png");
            }
            ballImage = spriteImg.getSubimage(0, 0, 400, 576);
            paddleImage = spriteImg.getSubimage(400, 0, 524, 576);
        } catch (Exception e) {
            System.err.println("Lỗi khi tải hình ảnh");
            e.printStackTrace();
        }

        // Add FocusListener to request focus back when lost
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                requestFocusInWindow();
            }
        });

        // Add keyboard event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    controller.movePaddleLeft();
                } else if (key == KeyEvent.VK_RIGHT) {
                    controller.movePaddleRight();
                } else if (key == KeyEvent.VK_P) {
                    if (gameState.isPaused() == false)
                        controller.pauseGame();
                    else
                        controller.resumeGame();
                } else if (key == KeyEvent.VK_SPACE && gameState.isGameOver()) {
                    controller.startGame();
                } else if (key == KeyEvent.VK_ESCAPE && gameState.isGameOver()) {
                    controller.resetGame(); // Reset game state
                    gameWindow.showMenu(); // Return to main menu
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int realWidth = getWidth();
        int realHeight = getHeight();

        // Background
        GradientPaint backgroundGradient = new GradientPaint(
                0, 0, new Color(44, 62, 80),
                0, getHeight(), new Color(52, 152, 219));
        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Calculate ratio based on original size
        double scaleX = realWidth / (double) GameConstants.GAME_WIDTH;
        double scaleY = realHeight / (double) GameConstants.GAME_HEIGHT;

        // Scale when drawing ball and paddle to prevent distortion
        double scale = Math.min(scaleX, scaleY);

        // Apply scaling when drawing paddle
        g2d.drawImage(paddleImage, (int) (paddle.getX() * scaleX),
                (int) (paddle.getY() * scaleY),
                (int) (paddle.getWidth() * scale),
                (int) (paddle.getHeight() * scale * 2), null);

        // Draw grenade (ball)
        g2d.drawImage(ballImage,
                (int) (ball.getX() * scaleX),
                (int) (ball.getY() * scaleY),
                (int) (ball.getSize() * scale),
                (int) (ball.getSize() * scale),
                null);

        // Draw score
        g2d.setColor(Color.green);
        g2d.setFont(new Font("Arial", Font.PLAIN, (int) (15 * scale)));
        g2d.drawString("Score: " + gameState.getScore(), (int) (10 * scaleX), (int) (25 * scaleY));
        g2d.drawString("High Score: " + gameState.getHighScore(), (int) (10 * scaleX), (int) (45 * scaleY));
        g2d.drawString("Lives: " + controller.getLives(), (int) (10 * scaleX), (int) (65 * scaleY));

        // Game over
        if (gameState.isGameOver()) {
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Arial", Font.BOLD, (int) (45 * scaleY)));
            g2d.drawString("GAME OVER", (int) (180 * scaleX), (int) (180 * scaleY));
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, (int) (20 * scaleY)));
            g2d.drawString("Press SPACE to restart", (int) (210 * scaleX), (int) (260 * scaleY));
            g2d.drawString("Press ESC to return to menu", (int) (200 * scaleX), (int) (290 * scaleY));
        }

        // Pause
        if (gameState.isPaused() && !gameState.isGameOver()) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, (int) (40 * scaleY)));
            g2d.drawString("PAUSED", (int) ((GameConstants.GAME_WIDTH / 2 - 70) * scaleX),
                    (int) ((GameConstants.GAME_HEIGHT / 2) * scaleY));
            g2d.setFont(new Font("Arial", Font.PLAIN, (int) (20 * scaleY)));
            g2d.drawString("Press P to resume", (int) ((GameConstants.GAME_WIDTH / 2 - 70) * scaleX),
                    (int) ((GameConstants.GAME_HEIGHT / 2 + 20) * scaleY));
        }
    }

    /**
     * Update game interface.
     */
    public void refresh() {
        repaint();
    }
}
