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

        setLayout(new BorderLayout());
        setSize(500, 800);
        setLocationRelativeTo(null); // center window
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init(); // setup components

        setVisible(true);
    }

    private void init() {
        // Title Panel
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(new Color(18, 52, 86));
        JLabel lblTitle = new JLabel("Trading Card - Main Menu");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        panelNorth.add(lblTitle);
        add(panelNorth, BorderLayout.NORTH);

        // Center Button Panel
        JPanel panelCenter = new JPanel();
        //panelCenter.setLayout(new GridLayout(4, 1, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        btnCollection = new JButton("Manage Collection");
        btnCollection.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // clean font
        btnCollection.setBackground(new Color(33, 150, 243));        // modern blue
        btnCollection.setForeground(Color.WHITE);                    // white text
        btnCollection.setFocusPainted(false);                        // remove focus outline
        btnCollection.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // padding

        btnBinders = new JButton("Manage Binders");
        btnBinders.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // clean font
        btnBinders.setBackground(new Color(33, 150, 243));        // modern blue
        btnBinders.setForeground(Color.WHITE);                    // white text
        btnBinders.setFocusPainted(false);                        // remove focus outline
        btnBinders.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // padding

        btnDecks = new JButton("Manage Decks");
        btnDecks.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // clean font
        btnDecks.setBackground(new Color(33, 150, 243));        // modern blue
        btnDecks.setForeground(Color.WHITE);                    // white text
        btnDecks.setFocusPainted(false);                        // remove focus outline
        btnDecks.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // padding

        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // clean font
        btnExit.setBackground(new Color(33, 150, 243));        // modern blue
        btnExit.setForeground(Color.WHITE);                    // white text
        btnExit.setFocusPainted(false);                        // remove focus outline
        btnExit.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // padding

        btnCollection.setMaximumSize(new Dimension(200, 40));
        btnBinders.setMaximumSize(new Dimension(200, 40));
        btnDecks.setMaximumSize(new Dimension(200, 40));
        btnExit.setMaximumSize(new Dimension(200, 40));

        panelCenter.add(btnCollection);
        panelCenter.add(btnBinders);
        panelCenter.add(btnDecks);
        panelCenter.add(btnExit);

        add(panelCenter, BorderLayout.CENTER);
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
