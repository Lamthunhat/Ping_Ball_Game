package models;

public class GameState {
    private int score;
    private boolean isGameOver;
    private int highScore;
    private boolean isGamePause;

    public GameState() {
        this.score = 0;
        this.isGameOver = false;
        this.highScore = 0;
        this.isGamePause = false;
    }

    public void checkHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    public void reset() {
        score = 0;
        isGameOver = false;
    }

    public boolean isPaused() {
        return isGamePause;
    }

    public void setPaused(boolean paused) {
        this.isGamePause = paused;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}