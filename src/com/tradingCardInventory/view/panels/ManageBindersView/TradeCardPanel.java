package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;
import com.tradingCardInventory.controllers.CollectionController;
import com.tradingCardInventory.model.Binders.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
        binders.addItem("");
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }

        JLabel outgoingLabel = new JLabel("Outgoing Card Name:");
        outgoingLabel.setForeground(Color.WHITE);
        outgoingPanel.add(outgoingLabel);

        cards = new JComboBox<>();
        binders.addActionListener(e -> {
            cards.removeAllItems();
            if(!Objects.equals(binders.getSelectedItem(), "")) {
                for (String cardName : bindersController.getAllBinderCardNames((String) binders.getSelectedItem())) {
                    cards.addItem(cardName);
                }
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
        JLabel selectExistingCardLabel = new JLabel("Select Existing Card:");
        selectExistingCardLabel.setForeground(Color.WHITE);
        incomingPanel.add(selectExistingCardLabel);
        incomingPanel.add(existingCards);

        // Manual inputs (shown when checkbox is unchecked)
        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameLabel.setForeground(Color.WHITE);
        cardName = new JTextField();
        cardName.setForeground(Color.WHITE);
        cardName.setBackground(Color.DARK_GRAY);

        JLabel rarityLabel = new JLabel("Rarity:");
        rarityLabel.setForeground(Color.WHITE);
        String[] rarities = {"- Select Rarity -","COMMON", "UNCOMMON", "RARE", "LEGENDARY"};
        cardRarity = new JComboBox<>(rarities);
        cardRarity.setForeground(Color.WHITE);
        cardRarity.setBackground(Color.DARK_GRAY);

        JLabel variantLabel = new JLabel("Variant:");
        variantLabel.setForeground(Color.WHITE);
        String[] variants = {"- Select Variant -", "NORMAL", "EXTENDED_ART", "FULL_ART", "ALT_ART"};
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
                if ("- Select Rarity -".equals(cardRarity.getSelectedItem()) || "- Select Variant -".equals(cardVariant.getSelectedItem())){
                    JOptionPane.showMessageDialog(this, "Please select fill out required inputs.");
                    return;
                }
                boolean success = false;
                String selectedOutgoingCard = (String) cards.getSelectedItem();
                double outgoingValue = collectionController.getCollection().searchCard(selectedOutgoingCard).getActualValue();
                double incomingValue = checkBox.isSelected()
                        ? collectionController.getCollection().searchCard((String) existingCards.getSelectedItem()).getActualValue()
                        : Double.parseDouble(cardValue.getText().trim());

                if (Math.abs(outgoingValue - incomingValue) > 1.0) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Card value difference is more than 1. Proceed with trade?",
                            "Warning", JOptionPane.YES_NO_OPTION);
                    if (result != JOptionPane.YES_OPTION) return;
                }

                if(
                    ((Objects.equals(cardRarity.getSelectedItem(), "COMMON")   || (Objects.equals(cardRarity.getSelectedItem(), "UNCOMMON")))  && bindersController.getManageBinder().searchBinder((String) binders.getSelectedItem()) instanceof PauperBinder) ||
                    ((Objects.equals(cardRarity.getSelectedItem(), "RARE")     || (Objects.equals(cardRarity.getSelectedItem(), "LEGENDARY"))) && bindersController.getManageBinder().searchBinder((String) binders.getSelectedItem()) instanceof RaresBinder) ||
                    ((!Objects.equals(cardVariant.getSelectedItem(), "NORMAL"))                                                                    && bindersController.getManageBinder().searchBinder((String) binders.getSelectedItem()) instanceof LuxuryBinder) ||
                    ((!Objects.equals(cardVariant.getSelectedItem(), "NORMAL"))                                                                    && bindersController.getManageBinder().searchBinder((String) binders.getSelectedItem()) instanceof CollectorBinder) ||
                    (bindersController.getManageBinder().searchBinder((String) binders.getSelectedItem()) instanceof NonCuratedBinder)
                ){
                    if (checkBox.isSelected()) {
                        success = collectionController.increaseDecrease((String) existingCards.getSelectedItem(), 1);
                        success &= bindersController.removeCardFromBinder(selectedOutgoingCard, (String) binders.getSelectedItem());
                        success &= collectionController.increaseDecrease(selectedOutgoingCard, -1);
                        success &= bindersController.addCardToBinder((String) existingCards.getSelectedItem(), (String) binders.getSelectedItem());
                    } else {
                        success = collectionController.addInputCard(
                                cardName.getText().trim(),
                                (String) cardRarity.getSelectedItem(),
                                (String) cardVariant.getSelectedItem(),
                                incomingValue);
                        success &= bindersController.removeCardFromBinder(selectedOutgoingCard, (String) binders.getSelectedItem());
                        success &= collectionController.increaseDecrease(selectedOutgoingCard, -1);
                        success &= bindersController.addCardToBinder(cardName.getText().trim(), (String) binders.getSelectedItem());
                    }
                }

                JOptionPane.showMessageDialog(this, success ? "Trade successful!" : "Trade failed. Check your inputs.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Oops! Something went wrong: " + ex.getMessage());
            }
        });
    }

    private void updateCards(BindersController bindersController) {
        cards.removeAllItems();
        String selectedBinder = (String) binders.getSelectedItem();
        if (selectedBinder != null) {
            for (String cardName : bindersController.getAllBinderCardNames(selectedBinder)) {
                cards.addItem(cardName);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
