package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;
import com.tradingCardInventory.controllers.CollectionController;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.view.resources.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellBinderPanel extends JPanel {
    private Image backgroundImage;
    private JComboBox<String> binders;
    private JPanel cardGridPanel;

    public SellBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // ---------- NORTH: Title and Search ----------
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));

        JLabel titleLabel = new JLabel("Sell Binders", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);

        binders = new JComboBox<>();
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }

        binders.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(binders);
        titlePanel.add(searchPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);

        binders.addActionListener(e -> {
            String selectedBinder = (String) binders.getSelectedItem();
            if (selectedBinder == null) {
                binders.addItem("None");
                return;
            }

            java.util.List<Card> binderCards = bindersController.getCards(selectedBinder);
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

        JScrollPane scrollPane = new JScrollPane(cardGridPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);

        // ---------- SOUTH: Submit (Sell) Button ----------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton sellButton = new JButton("Sell Binder");
        sellButton.setFocusPainted(false);
        sellButton.setBackground(new Color(200, 50, 50));
        sellButton.setForeground(Color.WHITE);

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBinder = (String) binders.getSelectedItem();
                if (selectedBinder == null) {
                    JOptionPane.showMessageDialog(SellBinderPanel.this, "No binder selected.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(SellBinderPanel.this,
                        "Are you sure you want to sell the binder \"" + selectedBinder + "\"?",
                        "Confirm Sell", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = bindersController.sellBinder(selectedBinder);
                    if (success) {

                        binders.removeItem(selectedBinder);
                        cardGridPanel.removeAll();
                        cardGridPanel.revalidate();
                        cardGridPanel.repaint();
                        JOptionPane.showMessageDialog(SellBinderPanel.this, "Binder sold successfully!");

                    } else {
                        JOptionPane.showMessageDialog(SellBinderPanel.this, "Failed to sell binder.");
                    }
                }
            }
        });

        buttonPanel.add(sellButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

