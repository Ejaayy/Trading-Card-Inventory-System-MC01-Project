package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;

import javax.swing.*;
import java.awt.*;

public class RemoveCardPanel extends  JPanel {
    private JTextField deckName;
    private JTextField cardName;
    private JButton submitButton;

    public RemoveCardPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20));

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Remove Card to from Deck", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.gray);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        formPanel.add(new JLabel("Deck Name:"));
        deckName = new JTextField();
        formPanel.add(deckName);

        formPanel.add(new JLabel("Card Name to remove:"));
        cardName = new JTextField();
        formPanel.add(cardName);

        add(formPanel, BorderLayout.CENTER);

        // SOUTH: Submit Button Panel
        submitButton = new JButton("Submit");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.gray);
        bottomPanel.setPreferredSize(new Dimension(650, 40));
        add(bottomPanel, BorderLayout.SOUTH);

        // ACTION LISTENER
        submitButton.addActionListener(e -> {
            String deck = deckName.getText().trim();
            String card = cardName.getText().trim();

            if (deck.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean success = deckController.removeCardFromDeck(deck, card);

            if (success) {
                JOptionPane.showMessageDialog(this, "Card successfully removed from deck!");
                deckName.setText("");
                cardName.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove card. Deck or Card might not exist.");
            }
        });
    }
}
