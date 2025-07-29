package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;
import com.tradingCardInventory.options.DeckType;

import javax.swing.*;
import java.awt.*;

public class CreateDeckPanel extends JPanel {
    private JTextField deckName;
    private JComboBox<String> deckType;
    private JButton submitButton;

    public CreateDeckPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel

        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Create a New Deck", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));

        formPanel.add(new JLabel("Deck Name:"));
        deckName = new JTextField();
        formPanel.add(deckName);

        formPanel.add(new JLabel("Deck Type:"));
        String[] types = { "Normal", "Sellable"};
        deckType = new JComboBox<>(types);
        formPanel.add(deckType);

        deckName.setPreferredSize(new Dimension(200, 25));
        deckType.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Create Deck");
        JPanel bottomPanel = new JPanel();
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
}

