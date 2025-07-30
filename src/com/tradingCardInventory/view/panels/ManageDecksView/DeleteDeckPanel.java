package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;

import javax.swing.*;
import java.awt.*;

public class DeleteDeckPanel extends JPanel {
    private JComboBox<String> decks;
    private JButton submitButton;
    private final Image backgroundImage;

    public DeleteDeckPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Delete Deck", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));
        formPanel.setOpaque(false);

        JLabel deckNameLabel = new JLabel("Deck Name:");
        deckNameLabel.setForeground(Color.WHITE);
        formPanel.add(deckNameLabel);
        decks = new JComboBox<>();
        decks.addItem("");
        for (String binderName : deckController.getAllDeckNames()) {
            decks.addItem(binderName);
        }
        formPanel.add(decks);

        decks.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Delete Deck");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(650, 60));
        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- Action Listener ----------
        submitButton.addActionListener(e -> {
            String name = (String) decks.getSelectedItem();

            assert name != null;
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a deck name.");
                return;
            }
            boolean status = deckController.deleteDeck(name);
            if (status ) {
                JOptionPane.showMessageDialog(this, "Deck deleted successfully!");
                decks.removeItem(name); // Clear input
            } else {
                JOptionPane.showMessageDialog(this, "Deck deletion failed. Maybe the name already exists?");
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
