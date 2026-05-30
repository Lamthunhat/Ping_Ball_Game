package models;

import java.awt.Rectangle;

public class Ball {
    private double x, y; // Toa do
    private double dx, dy; // Toc do
    private int size; // Kich thuoc

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
        // Kiem tra va cham voi bien trai/phai
        if (x <= 0 || x + size >= panelWidth) {
            bounceHorizontal();
            x = Math.max(0, Math.min(x, panelWidth - size));
        }
        // Kiem tra va cham voi bien tre
        if (y <= 0) {
            bounceVertical();
            y = 0;
        }
        // Kiem tra neu bong ra ngoai bien duoi (mat bong)
        if (y + size >= panelHeight) {
            y = panelHeight - size;
        }
    }

    // Ham tinh huong va cham voi bien doc
    public void bounceHorizontal() {
        dx = -dx;
    }

    // Ham tinh huong va cham voi bien ngang
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