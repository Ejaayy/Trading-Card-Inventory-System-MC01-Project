package com.tradingCardInventory.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JButton btnStart;
    private JButton btnExit;

    public MainView() {
        super("Trading Card Inventory - Main Menu");

        BackgroundPanel background = new BackgroundPanel("MainMenuBackground.png");
        background.setLayout(new BorderLayout());
        setContentPane(background);

        setSize(900, 600);
        setLocationRelativeTo(null); // center window
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init(); // setup components

        setVisible(true);
    }

    private void init() {
        // ----------- Create Buttons -----------
        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");

        // Style all buttons
        btnStart.setBackground(new Color(33, 150, 243));  // Material Blue 500
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnStart.setBorder(BorderFactory.createEmptyBorder(5, 75, 5, 75));
        btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnExit.setBackground(new Color(33, 150, 243));  // Material Blue 500
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btnExit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // ----------- NORTH TITLE BAR -----------
        JPanel panelNorth = new JPanel();
        panelNorth.setOpaque(false);
        JLabel lblTitle = new JLabel("Trading Card - Main Menu");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Archivo Black", Font.BOLD, 20));
        panelNorth.add(lblTitle);
        add(panelNorth, BorderLayout.NORTH);

        // ----------- CENTER PANEL (Button Row) -----------
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setOpaque(false);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50, 150, 80, 80)); // adjust top/left spacing

        // Horizontal Button Row
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.LEFT_ALIGNMENT); // line up with title

        panelCenter.add(Box.createVerticalStrut(230));
        panelCenter.add(buttonRow);

        buttonRow.add(btnStart);
        buttonRow.add(Box.createHorizontalStrut(20));

        add(panelCenter, BorderLayout.CENTER);

        // ----------- SOUTH PANEL (Exit Button) -----------
        JPanel panelSouth = new JPanel();
        panelSouth.setOpaque(false);
        panelSouth.add(btnExit);
        add(panelSouth, BorderLayout.SOUTH);
    }
}
