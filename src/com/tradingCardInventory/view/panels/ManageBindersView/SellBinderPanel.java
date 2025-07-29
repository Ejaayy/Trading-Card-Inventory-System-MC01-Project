package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;
import com.tradingCardInventory.controllers.CollectionController;
import com.tradingCardInventory.model.Binders.LuxuryBinder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.view.resources.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SellBinderPanel extends JPanel {
    private Image backgroundImage;
    private JComboBox<String> binders;
    private JPanel cardGridPanel;
    private JLabel luxuryPriceLabel;
    private JTextField luxuryPriceField;

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
        binders.addItem("");
        for (String binderName : bindersController.getAllSellableBinderNames()) {
            binders.addItem(binderName);
        }

        binders.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(binders);
        titlePanel.add(searchPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);

        luxuryPriceLabel = new JLabel("Luxury Price:");
        luxuryPriceField = new JTextField(10);

        luxuryPriceLabel.setVisible(false); // hidden by default
        luxuryPriceField.setVisible(false); // hidden by default

        searchPanel.add(luxuryPriceLabel);
        searchPanel.add(luxuryPriceField);

        binders.addActionListener(e -> {
            String selectedBinder = (String) binders.getSelectedItem();
            if (Objects.equals(selectedBinder, "")) {
                return;
            }

            boolean isLuxury = bindersController.getManageBinder().searchBinder(selectedBinder) instanceof LuxuryBinder;

            luxuryPriceLabel.setVisible(isLuxury);
            luxuryPriceField.setVisible(isLuxury);

            //Refresh the p
            searchPanel.revalidate();
            searchPanel.repaint();

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

                //Get the selected Binder
                String selectedBinder = (String) binders.getSelectedItem();
                if (Objects.equals(selectedBinder, "")) {
                    JOptionPane.showMessageDialog(SellBinderPanel.this, "No binder selected.");
                    return;
                }

                // Confirm first
                int confirm = JOptionPane.showConfirmDialog(SellBinderPanel.this,
                        "Are you sure you want to sell the binder \"" + selectedBinder + "\"?",
                        "Confirm Sell", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Only if it's luxury, parse the value from the text field
                    if (bindersController.getManageBinder().searchBinder(selectedBinder) instanceof LuxuryBinder luxuryBinder) {
                        String priceText = luxuryPriceField.getText().trim();
                        if (priceText.isEmpty()) {
                            JOptionPane.showMessageDialog(SellBinderPanel.this, "Please enter a luxury price.");
                            return;
                        }

                        try {
                            double customValue = Double.parseDouble(priceText);
                            if (customValue <= 0) throw new NumberFormatException();
                            //Set the price from the text field
                            boolean valid = luxuryBinder.setCustomValue(customValue);
                            if(!valid){
                                JOptionPane.showMessageDialog(SellBinderPanel.this, "Failed to Sell. Set price is lower than binder's actual value.");
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(SellBinderPanel.this, "Invalid luxury price.");
                            return;
                        }
                    }

                    // Sell the binder
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

