package cvut.fel.pjv.pimenol1.main;

import cvut.fel.pjv.pimenol1.main.Felisium;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        for (String arg : args) {
            if (arg.equals("true")) {
                Felisium.loggerOff = true;
                break;
            }
        }
        // Create window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Felisium");
        // Add to window gp
        Felisium gamePanel = new Felisium();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
