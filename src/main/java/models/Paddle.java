package models;

import java.awt.Rectangle;

public class Paddle {
    private int x, y;
    private int width, height;
    private int speed;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = GameConstants.PADDLE_WIDTH;
        this.height = GameConstants.PADDLE_HEIGHT;
        this.speed = GameConstants.PADDLE_SPEED;
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight(int panelWidth) {
        if (panelWidth <= 0) {
            return;
        }
        x += speed;
        if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void reset() {
        x = GameConstants.PADDLE_START_X;
        y = GameConstants.PADDLE_START_Y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
