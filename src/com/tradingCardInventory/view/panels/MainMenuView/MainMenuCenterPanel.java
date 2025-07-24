package com.tradingCardInventory.view.panels.MainMenuView;

import javax.swing.*;
import java.awt.*;

public class MainMenuCenterPanel extends JPanel {
    private Image backgroundImage;

    public MainMenuCenterPanel() {
        setLayout(null);
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/MainMenuBG.png")).getImage();

        JLabel label = new JLabel("Welcome to the Trading Card Inventory!", SwingConstants.CENTER);
        label.setBounds(50, 100, 400, 30); // x, y, width, height — adjust as needed

        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
