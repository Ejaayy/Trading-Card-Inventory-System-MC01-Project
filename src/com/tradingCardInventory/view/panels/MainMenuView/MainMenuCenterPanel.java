package com.tradingCardInventory.view.panels.MainMenuView;

import javax.swing.*;

public class MainMenuCenterPanel extends JPanel {

    public MainMenuCenterPanel() {
        setLayout(null);

        JLabel label = new JLabel("Welcome to the Trading Card Inventory!", SwingConstants.CENTER);
        label.setBounds(50, 100, 400, 30); // x, y, width, height — adjust as needed

        add(label);
    }
}
