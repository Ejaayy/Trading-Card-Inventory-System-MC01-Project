package com.tradingCardInventory.view;

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
    // Style all buttons
   // btnStart.setBackground(new Color(33, 150, 243));  // Material Blue 500
    //btnStart.setForeground(Color.WHITE);
   //btnStart.setFocusPainted(false);
    //btnStart.setFont(new Font("Segoe UI", Font.BOLD, 20));
    //btnStart.setBorder(BorderFactory.createEmptyBorder(5, 75, 5, 75));
    //btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


}
