package main;

import javax.swing.*;
import views.*;

public class Main {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}
