package com.tradingCardInventory.view.panels.ManageDecksView;

import com.tradingCardInventory.menu.DeckController;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.view.resources.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class ViewDeckPanel extends JPanel {
    private Image backgroundImage;
    private JComboBox<String> decks;
    private JPanel cardGridPanel;

    public ViewDeckPanel(DeckController deckController) {
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // ---------- NORTH: Title and Search ----------
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));

        JLabel titleLabel = new JLabel("View Decks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);

        decks = new JComboBox<>();
        for (String deckName : deckController.getAllDeckNames()) {
            decks.addItem(deckName);
        }

        decks.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(decks);
        titlePanel.add(searchPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);

        decks.addActionListener(e -> {
            String selectedBinder = (String) decks.getSelectedItem();
            if (selectedBinder == null){
                decks.addItem("None");
                return;
            }

            java.util.List<Card> binderCards = deckController.getCards(selectedBinder);
            cardGridPanel.removeAll();

            for (Card card : binderCards) {
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

                cardPanel.add(Box.createVerticalStrut(10));
                cardPanel.add(nameLabel);
                cardPanel.add(Box.createVerticalStrut(5));
                cardPanel.add(rarityLabel);
                cardPanel.add(variantLabel);
                cardPanel.add(valueLabel);
                cardPanel.add(Box.createVerticalGlue());

                cardGridPanel.add(cardPanel);
            }

            cardGridPanel.revalidate();
            cardGridPanel.repaint();
        });
        // ---------- CENTER: Card Grid Panel ----------
        cardGridPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
        cardGridPanel.setOpaque(false);
        cardGridPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Wrap grid in scroll pane
        JScrollPane scrollPane = new JScrollPane(cardGridPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
