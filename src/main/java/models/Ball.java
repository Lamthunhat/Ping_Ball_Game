package models;

import java.awt.Rectangle;

public class Ball {
    private double x, y; // Coordinates
    private double dx, dy; // Speed / Velocity
    private int size; // Size

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = GameConstants.BALL_SPEED;
        this.dy = GameConstants.BALL_SPEED;
        this.size = GameConstants.BALL_SIZE;
    }

    public void move(int panelWidth, int panelHeight) {
        x += dx;
        y += dy;
        // Check collision with left/right borders
        if (x <= 0 || x + size >= panelWidth) {
            bounceHorizontal();
            x = Math.max(0, Math.min(x, panelWidth - size));
        }
        // Check collision with top border
        if (y <= 0) {
            bounceVertical();
            y = 0;
        }
        // Check if the ball goes out of the bottom border (lose ball)
        if (y + size >= panelHeight) {
            y = panelHeight - size;
        }
    }

    // Reverse horizontal direction upon border collision
    public void bounceHorizontal() {
        dx = -dx;
    }

    // Reverse vertical direction upon border collision
    public void bounceVertical() {
        dy = -dy;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

    public void reset() {
        this.x = GameConstants.GAME_WIDTH / 2 - size / 2;
        this.y = 0;
        this.size = GameConstants.BALL_SIZE;
        this.dx = GameConstants.BALL_SPEED;
        this.dy = GameConstants.BALL_SPEED;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(final double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(final double dy) {
        this.dy = dy;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }
}