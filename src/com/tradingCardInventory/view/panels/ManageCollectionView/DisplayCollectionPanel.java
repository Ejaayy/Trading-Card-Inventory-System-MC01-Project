package com.tradingCardInventory.view.panels.ManageCollectionView;

import com.tradingCardInventory.controllers.CollectionController;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.view.resources.*;

import javax.swing.*;
import java.awt.*;

public class DisplayCollectionPanel extends JPanel {
    private JTextField cardName;
    private Image backgroundImage;

    public DisplayCollectionPanel(CollectionController collectionController) {
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // ---------- NORTH: Title and Search ----------
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
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
        JPanel cardGridPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
        cardGridPanel.setOpaque(false);
        cardGridPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margin

        for (Card card : collectionController.getCollection().getAllCards()) {
            JPanel cardPanel = new JPanel();
            cardPanel.setPreferredSize(new Dimension(170, 220));
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
            countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        scrollPane.setOpaque(false); // The JScrollPane itself
        scrollPane.getViewport().setOpaque(false); // The viewport holding the panel

        add(scrollPane, BorderLayout.CENTER);

        searchTitle.addActionListener(e -> {
            String findCard = cardName.getText();
            Card card = collectionController.getCollection().searchCard(findCard);
            if(card != null) {
                cardGridPanel.removeAll(); //Clear all cards

                //Rebuild panel with the found card
                JPanel cardPanel = new JPanel();
                cardPanel.setPreferredSize(new Dimension(170, 220));
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
                countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                cardPanel.add(Box.createVerticalStrut(10));
                cardPanel.add(nameLabel);
                cardPanel.add(Box.createVerticalStrut(5));
                cardPanel.add(rarityLabel);
                cardPanel.add(variantLabel);
                cardPanel.add(valueLabel);
                cardPanel.add(countLabel);
                cardPanel.add(Box.createVerticalGlue());

                cardGridPanel.add(cardPanel);

                cardGridPanel.revalidate(); //Update layout
                cardGridPanel.repaint();
            }else{
                JOptionPane.showMessageDialog(this, "Card does not exist!");
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
