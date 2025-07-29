package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;

import javax.swing.*;
import java.awt.*;

public class AddCardPanel extends JPanel {
    private JComboBox<String> decks;
    private JComboBox<String> cards;
    private JButton submitButton;

    public AddCardPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20));

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Add Card to a Deck", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.gray);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        //Drop down for all existing Decks
        formPanel.add(new JLabel("Binder Name:"));
        decks = new JComboBox<>();
        decks.addItem("");
        for (String binderName : deckController.getAllDeckNames()) {
            decks.addItem(binderName);
        }
        formPanel.add(decks);

        formPanel.add(new JLabel("Card Name to Add:"));
        cards = new JComboBox<>();
        cards.addItem("");
        for (String cardName : deckController.getCollectionCardNames()) {
            cards.addItem(cardName);
        }
        formPanel.add(cards);

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
            String deck = (String) decks.getSelectedItem();
            String card = (String) cards.getSelectedItem();

            assert deck != null;
            assert card != null;
            if (deck.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean success = deckController.addCardToDeck(deck, card);

            if (success) {
                String message = String.format("Card successfully added to %s!", decks.getSelectedItem());
                JOptionPane.showMessageDialog(this, message);
                cards.removeAllItems();
                cards.addItem("");
                for (String cardName : deckController.getCollectionCardNames()) {
                    cards.addItem(cardName);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add card. Deck or Card might not exist.");
            }
        });
    }
}
