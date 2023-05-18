package cvut.fel.pjv.pimenol1.main_pjv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPage extends JPanel implements Page{
    private JButton startButton;
    private JButton settingsButton;
    private JButton exitButton;
    private Image backgroundImage;

    public MainMenuPage() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        startButton = new JButton("Start");
        exitButton = new JButton("Exit");

        add(startButton);
        add(exitButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Действия при нажатии на кнопку 1
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Действия при нажатии на кнопку 2
            }
        });


//        ImageIcon startIcon = new ImageIcon("/Button/Button-1.png");
//        ImageIcon exitIcon = new ImageIcon("/Button/Button-2.png");
//        startButton.setIcon(startIcon);
//        exitButton.setIcon(exitIcon);

        backgroundImage = Toolkit.getDefaultToolkit().createImage("/background/mainMenu.png");
    }

    public void addStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    protected void paintComponent(Graphics2D g2) {
        super.paintComponent(g2);

        // Draw the background image
        g2.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HIGH, this);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }
}

