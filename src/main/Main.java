package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        ImageIcon img = new ImageIcon("D:\\game-development\\My2DGame\\res\\icon\\bunny.png");
        
        window.setIconImage(img.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bunny Bounty");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        //window display at the center of screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
