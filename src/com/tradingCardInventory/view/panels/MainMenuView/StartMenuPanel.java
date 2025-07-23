package com.tradingCardInventory.view.panels.MainMenuView;

import javax.swing.*;

public class StartMenuPanel extends JPanel {

    private JButton btnStart;
    private JButton btnExit;

    public StartMenuPanel() {
        // Configure the JFrame itself (this)

        setLayout(null);  // Avoid this if possible, but works for manual layout
        setSize(750, 750);

        // Initialize buttons
        btnStart = new JButton("Start");
        btnStart.setBounds(100, 100, 150, 40);
        add(btnStart);

        btnExit = new JButton("Exit");
        btnExit.setBounds(100, 160, 150, 40);
        add(btnExit);

    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

}
