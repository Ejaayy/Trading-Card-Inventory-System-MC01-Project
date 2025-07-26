package com.tradingCardInventory.view.panels.MainMenuView;

import javax.swing.*;
import java.awt.*;

public class StartMenuPanel extends JPanel {

    private JButton btnStart;
    private JButton btnExit;
    private Image backgroundImage;

    public StartMenuPanel() {
        // Configure the JFrame itself (this)

        setLayout(null);  // Avoid this if possible, but works for manual layout
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/TradingCardMenuBG.png")).getImage();

        // Initialize buttons
        btnStart = new JButton("Start");
        btnStart.setBounds(100, 310, 200, 40);
        add(btnStart);

        btnExit = new JButton("Exit");
        btnExit.setBounds(350, 310, 150, 40);
        add(btnExit);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

}
