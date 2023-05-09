package main_pjv;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Felisium");
        // Add to window gp
        GamePannel gamePanel = new GamePannel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }
}
