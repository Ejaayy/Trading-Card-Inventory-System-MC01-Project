package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.controllers.DeckController;

import javax.swing.*;
import java.awt.*;

public class AddCardPanel extends JPanel {
    private JComboBox<String> decks;
    private JComboBox<String> cards;
    private JButton submitButton;
    private final Image backgroundImage;

    public AddCardPanel(DeckController deckController) {
        setLayout(new BorderLayout(20, 20));
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();
        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Add Card to a Deck", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        formPanel.setOpaque(false);

        //Drop down for all existing Decks
        JLabel deckLabel = new JLabel("Deck Name:");
        deckLabel.setForeground(Color.WHITE);
        formPanel.add(deckLabel);
        decks = new JComboBox<>();
        decks.addItem("");
        for (String binderName : deckController.getAllDeckNames()) {
            decks.addItem(binderName);
        }
        formPanel.add(decks);

        JLabel cardLabel = new JLabel("Card Name to Add:");
        cardLabel.setForeground(Color.WHITE);
        formPanel.add(cardLabel);
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
        bottomPanel.setOpaque(false);
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
                JOptionPane.showMessageDialog(this, "Failed to add card. Deck or Card might not exist or Added Card might be a duplicate.");
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
