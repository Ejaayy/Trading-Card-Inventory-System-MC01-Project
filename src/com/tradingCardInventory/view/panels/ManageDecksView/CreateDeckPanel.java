package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;
import com.tradingCardInventory.options.DeckType;

import javax.swing.*;
import java.awt.*;

public class CreateDeckPanel extends JPanel {

    private JTextField deckName;
    private JComboBox<String> deckType;
    private JButton submitButton;
    private final Image backgroundImage;

    public CreateDeckPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Create a New Deck", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));
        formPanel.setOpaque(false);

        JLabel deckNameLabel = new JLabel("Deck Name:");
        deckNameLabel.setForeground(Color.WHITE);
        formPanel.add(deckNameLabel);
        deckName = new JTextField();
        formPanel.add(deckName);

        JLabel deckTypeLabel = new JLabel("Deck Type:");
        deckTypeLabel.setForeground(Color.WHITE);
        formPanel.add(deckTypeLabel);
        String[] types = { "Normal", "Sellable"};
        deckType = new JComboBox<>(types);
        formPanel.add(deckType);

        deckName.setPreferredSize(new Dimension(200, 25));
        deckType.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Create Deck");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(650, 60));
        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- Action Listener ----------
        submitButton.addActionListener(e -> {
            String name = deckName.getText();
            String type = ((String) deckType.getSelectedItem());

            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a deck name.");
                return;
            }
            boolean status = deckController.createDeck(name, DeckType.valueOf(type));
            if (status ) {
                JOptionPane.showMessageDialog(this, "Deck created successfully!");
                deckName.setText(""); // Clear input
                deckType.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Deck creation failed. Maybe the name already exists?");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

