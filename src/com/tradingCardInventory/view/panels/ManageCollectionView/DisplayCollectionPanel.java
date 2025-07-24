package com.tradingCardInventory.view.panels.ManageCollectionView;

import com.tradingCardInventory.menu.CollectionController;
import com.tradingCardInventory.model.Card;

import javax.swing.*;
import java.awt.*;

public class DisplayCollectionPanel extends JPanel {
    private JTextField cardName;

    public DisplayCollectionPanel(CollectionController collectionController) {
        setLayout(new BorderLayout());

        // ---------- NORTH: Title and Search ----------
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));

        JLabel titleLabel = new JLabel("View Collection", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);

        JButton searchTitle = new JButton("Search Card: ");
        cardName = new JTextField(20);
        cardName.setToolTipText("Search Card Name...");

        searchPanel.add(searchTitle);
        searchPanel.add(cardName);
        titlePanel.add(searchPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Card Grid Panel ----------
        JPanel cardGridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // 3 columns, gaps
        cardGridPanel.setBackground(Color.DARK_GRAY);
        cardGridPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margin

        for (Card card : collectionController.getCollection().getAllCards()) {
            JPanel cardPanel = new JPanel();
            cardPanel.setPreferredSize(new Dimension(150, 200));
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(card.getName(), SwingConstants.CENTER);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel rarityLabel = new JLabel("Rarity: " + card.getRarity());
            rarityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel variantLabel = new JLabel("Variant: " + card.getVariant());
            variantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel valueLabel = new JLabel("Value: " + card.getActualValue());
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel countLabel = new JLabel("Count: " + card.getCount());
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            cardPanel.add(Box.createVerticalStrut(10));
            cardPanel.add(nameLabel);
            cardPanel.add(Box.createVerticalStrut(5));
            cardPanel.add(rarityLabel);
            cardPanel.add(variantLabel);
            cardPanel.add(valueLabel);
            cardPanel.add(countLabel);
            cardPanel.add(Box.createVerticalGlue());

            cardGridPanel.add(cardPanel);
        }

        // Wrap grid in scroll pane
        JScrollPane scrollPane = new JScrollPane(cardGridPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }
}
