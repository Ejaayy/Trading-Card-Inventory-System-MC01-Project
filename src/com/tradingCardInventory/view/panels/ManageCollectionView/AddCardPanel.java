package com.tradingCardInventory.view.panels.ManageCollectionView;

import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class AddCardPanel extends JPanel {
    private JTextField cardName;
    private JComboBox<String> cardRarity;
    private JComboBox<String> cardVariant;
    private JTextField cardValue;
    private JButton submitButton;
    private Image backgroundImage;

    public AddCardPanel(CollectionController collectionController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // NORTH: Title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Add a New Card", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        titlePanel.setOpaque(false);
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 20));
        formPanel.setForeground(Color.WHITE);
        formPanel.setOpaque(true);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 180, 40));

        JLabel cardNameLabel = new JLabel("Card Name");
        cardNameLabel.setForeground(Color.WHITE);
        formPanel.add(cardNameLabel);
        cardName = new JTextField();
        formPanel.add(cardName);

        JLabel cardRarityLabel = new JLabel("Card Rarity");
        cardRarityLabel.setForeground(Color.WHITE);
        formPanel.add(cardRarityLabel);
        String[] rarities = { "COMMON", "UNCOMMON", "RARE", "LEGENDARY" };
        cardRarity = new JComboBox<>(rarities);
        formPanel.add(cardRarity);

        cardRarity.addActionListener(e -> {
            String selectedRarity = (String) cardRarity.getSelectedItem();
            boolean enableVariants = selectedRarity.equals("RARE") || selectedRarity.equals("LEGENDARY");

            // Enable or disable the cardVariant dropdown depending on the rarity
            cardVariant.setEnabled(enableVariants);

            //Sets card variant to none if rarity is not legendary nor rare
            //fixed
            if (!enableVariants) {
                cardVariant.setSelectedItem("NORMAL");
            }
        });

        JLabel cardVariantLabel = new JLabel("Card Variant");
        cardVariantLabel.setForeground(Color.WHITE);
        formPanel.add(cardVariantLabel);
        String[] variants = { "NORMAL", "EXTENDED_ART", "FULL_ART", "ALT_ART" };
        cardVariant = new JComboBox<>(variants);
        formPanel.add(cardVariant);

        JLabel cardValueLabel = new JLabel("Card Value");
        cardValueLabel.setForeground(Color.WHITE);
        formPanel.add(cardValueLabel);
        cardValue = new JTextField();
        formPanel.add(cardValue);

        cardName.setPreferredSize(new Dimension(200, 15));
        cardValue.setPreferredSize(new Dimension(200, 25));
        cardRarity.setPreferredSize(new Dimension(200, 25));
        cardVariant.setPreferredSize(new Dimension(200, 25));

        formPanel.setOpaque(false);
        add(formPanel, BorderLayout.CENTER);

        // SOUTH: Submit button
        submitButton = new JButton("Submit");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(650, 40));
        add(bottomPanel, BorderLayout.SOUTH);


        // Action Listener
        submitButton.addActionListener(e -> {
            String name = cardName.getText();
            String rarity = (String) cardRarity.getSelectedItem();
            String variant = (String) cardVariant.getSelectedItem();
            String valueText = cardValue.getText();

            try {
                double value = Double.parseDouble(valueText);
                boolean status = collectionController.addInputCard(name, rarity, variant, value);
                if(status){
                    JOptionPane.showMessageDialog(this, "Card added successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Add Card failed! Please enter valid inputs.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for card value.");
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
