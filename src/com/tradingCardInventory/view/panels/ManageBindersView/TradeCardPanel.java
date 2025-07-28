package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;
import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class TradeCardPanel extends JPanel {

    private JComboBox<String> binders;
    private JComboBox<String> cards;
    private JComboBox<String> existingCards;

    private JTextField cardName;
    private JComboBox<String> cardRarity;
    private JComboBox<String> cardVariant;
    private JTextField cardValue;

    private JTextField outgoingCardName;
    private JButton submitButton;

    private Image backgroundImage;

    public TradeCardPanel(CollectionController collectionController, BindersController bindersController) {
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(650, 80));
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Trade a Card");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Two Side Panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.setOpaque(false);

        // LEFT: Outgoing Card Panel
        JPanel outgoingPanel = new JPanel(new GridLayout(4, 1, 5, 20));
        outgoingPanel.setOpaque(false);

        JLabel binderLabel = new JLabel("Binder Name:");
        binderLabel.setForeground(Color.WHITE);
        outgoingPanel.add(binderLabel);

        //Drop down for all existing Binders
        binders = new JComboBox<>();
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }

        JLabel outgoingLabel = new JLabel("Outgoing Card Name:");
        outgoingLabel.setForeground(Color.WHITE);
        outgoingPanel.add(outgoingLabel);

        cards = new JComboBox<>();
        binders.addActionListener(e -> {

            for (String cardName : bindersController.getAllBinderCardNames(binders.getItemAt(0))) {
                cards.addItem(cardName);
            }

        });
        outgoingPanel.add(binders);
        outgoingPanel.add(cards);
        outgoingPanel.add(new JLabel("")); // spacer
        outgoingPanel.add(new JLabel("")); // spacer

        centerPanel.add(outgoingPanel);

        // RIGHT: Incoming Card Panel
        JPanel incomingPanel = new JPanel(new GridLayout(6, 2, 5, 20));
        incomingPanel.setOpaque(false);

        JCheckBox checkBox = new JCheckBox("Card already exists in your collection");
        checkBox.setForeground(Color.WHITE);
        checkBox.setOpaque(false);
        incomingPanel.add(checkBox);
        incomingPanel.add(new JLabel("")); // spacer

        // Existing card dropdown
        existingCards = new JComboBox<>();
        for (String cardName : collectionController.getAllCardNames()) {
            existingCards.addItem(cardName);
        }
        incomingPanel.add(new JLabel("Select Existing Card:"));
        incomingPanel.add(existingCards);

        // Manual inputs (shown when checkbox is unchecked)
        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameLabel.setForeground(Color.WHITE);
        cardName = new JTextField();
        cardName.setForeground(Color.WHITE);
        cardName.setBackground(Color.DARK_GRAY);

        JLabel rarityLabel = new JLabel("Rarity:");
        rarityLabel.setForeground(Color.WHITE);
        String[] rarities = {"COMMON", "UNCOMMON", "RARE", "LEGENDARY"};
        cardRarity = new JComboBox<>(rarities);
        cardRarity.setForeground(Color.WHITE);
        cardRarity.setBackground(Color.DARK_GRAY);

        JLabel variantLabel = new JLabel("Variant:");
        variantLabel.setForeground(Color.WHITE);
        String[] variants = {"NORMAL", "EXTENDED_ART", "FULL_ART", "ALT_ART"};
        cardVariant = new JComboBox<>(variants);
        cardVariant.setForeground(Color.WHITE);
        cardVariant.setBackground(Color.DARK_GRAY);

        JLabel valueLabel = new JLabel("Card Value:");
        valueLabel.setForeground(Color.WHITE);
        cardValue = new JTextField();
        cardValue.setForeground(Color.WHITE);
        cardValue.setBackground(Color.DARK_GRAY);

        // Add manual fields
        incomingPanel.add(cardNameLabel);
        incomingPanel.add(cardName);
        incomingPanel.add(rarityLabel);
        incomingPanel.add(cardRarity);
        incomingPanel.add(variantLabel);
        incomingPanel.add(cardVariant);
        incomingPanel.add(valueLabel);
        incomingPanel.add(cardValue);

        // Handle variant enabling based on rarity
        cardRarity.addActionListener(e -> {
            String selectedRarity = (String) cardRarity.getSelectedItem();
            boolean enableVariants = selectedRarity.equals("RARE") || selectedRarity.equals("LEGENDARY");
            cardVariant.setEnabled(enableVariants);
            if (!enableVariants) {
                cardVariant.setSelectedItem("NORMAL");
            }
        });

        // Checkbox toggles visibility
        checkBox.addActionListener(e -> {
            boolean exists = checkBox.isSelected();
            existingCards.setVisible(exists);
            cardName.setVisible(!exists);
            cardRarity.setVisible(!exists);
            cardVariant.setVisible(!exists);
            cardValue.setVisible(!exists);
            cardNameLabel.setVisible(!exists);
            rarityLabel.setVisible(!exists);
            variantLabel.setVisible(!exists);
            valueLabel.setVisible(!exists);
        });

        // Initial state
        existingCards.setVisible(false);

        centerPanel.add(incomingPanel);
        add(centerPanel, BorderLayout.CENTER);

        // SOUTH: Submit Button
        submitButton = new JButton("Submit");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(650, 40));
        bottomPanel.add(submitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Submit logic
        submitButton.addActionListener(e -> {
            try {

                boolean success = false;

                if (checkBox.isSelected()) {
                    //Card already exists
                    collectionController.increaseDecrease((String) existingCards.getSelectedItem(), 1); //increment count in Collection
                    bindersController.removeCardFromBinder((String) cards.getSelectedItem(), (String) binders.getSelectedItem());
                    collectionController.increaseDecrease((String) cards.getSelectedItem(), -1);
                    bindersController.addCardToBinder((String) existingCards.getSelectedItem(), (String) binders.getSelectedItem());

                    success = true;
                } else {
                    //Card does not exist yet
                    collectionController.addInputCard(cardName.getText(), (String) cardRarity.getSelectedItem(), (String) cardVariant.getSelectedItem(), Double.parseDouble(cardValue.getText()));
                    bindersController.removeCardFromBinder((String) cards.getSelectedItem(), (String )binders.getSelectedItem());
                    collectionController.increaseDecrease((String) cards.getSelectedItem(), -1);
                    bindersController.addCardToBinder( collectionController.getCollection().searchCard(cardName.getText()).getName(), (String) binders.getSelectedItem());
                    success = true;
                }



                if (success) {
                    JOptionPane.showMessageDialog(this, "Trade successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Trade failed. Check your inputs.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Card value must be a valid number.");
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
