package com.tradingCardInventory.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton btnCollection;
    private JButton btnBinders;
    private JButton btnDecks;
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
        btnCollection = new JButton("Manage Collection");
        btnBinders = new JButton("Manage Binders");
        btnDecks = new JButton("Manage Decks");
        btnExit = new JButton("Exit");

        // Style all buttons
        JButton[] buttons = {btnCollection, btnBinders, btnDecks, btnExit};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setBackground(new Color(33, 150, 243));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
            btn.setPreferredSize(new Dimension(160, 40)); // Size without forcing layout issues
        }

        // ----------- NORTH TITLE BAR -----------
        JPanel panelNorth = new JPanel();
        panelNorth.setOpaque(false);
        JLabel lblTitle = new JLabel("Trading Card - Main Menu");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        panelNorth.add(lblTitle);
        add(panelNorth, BorderLayout.NORTH);

        // ----------- CENTER PANEL (Button Row) -----------
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setOpaque(false);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50, 80, 0, 80)); // adjust top/left spacing

        // Horizontal Button Row
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.LEFT_ALIGNMENT); // line up with title

        panelCenter.add(Box.createVerticalStrut(230));

        buttonRow.add(btnCollection);
        buttonRow.add(Box.createHorizontalStrut(20));
        buttonRow.add(btnBinders);
        buttonRow.add(Box.createHorizontalStrut(20));
        buttonRow.add(btnDecks);

        panelCenter.add(buttonRow);
        panelCenter.add(Box.createVerticalGlue()); // pushes everything up

        add(panelCenter, BorderLayout.CENTER);

        // ----------- SOUTH PANEL (Exit Button) -----------
        JPanel panelSouth = new JPanel();
        panelSouth.setOpaque(false);
        panelSouth.add(btnExit);
        add(panelSouth, BorderLayout.SOUTH);
    }

    // ---------- Listener Setters (for Controller) ----------

    public void addCollectionListener(ActionListener listener) {
        btnCollection.addActionListener(listener);
    }

    public void addBindersListener(ActionListener listener) {
        btnBinders.addActionListener(listener);
    }

    public void addDecksListener(ActionListener listener) {
        btnDecks.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }
}
