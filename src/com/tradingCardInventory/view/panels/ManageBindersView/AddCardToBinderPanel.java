package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;

public class AddCardToBinderPanel extends JPanel {
    private JComboBox<String> binders;
    private JComboBox<String> cards;
    private JButton submitButton;

    public AddCardToBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20));

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Add Card to a Binder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.gray);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        //Drop down for all existing Binders
        formPanel.add(new JLabel("Binder Name:"));
        binders = new JComboBox<>();
        binders.addItem("");
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }
        formPanel.add(binders);

        formPanel.add(new JLabel("Card Name to Add:"));
        cards = new JComboBox<>();
        cards.addItem("");
        for (String cardName : bindersController.getCollectionCardNames()) {
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
            String binder = (String) binders.getSelectedItem();
            String card = (String)  cards.getSelectedItem();

            assert binder != null;
            assert  card != null;
            if (binder.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean success = bindersController.addCardToBinder(card, binder);

            if (success) {
                JOptionPane.showMessageDialog(this, "Card successfully added to binder!");
                cards.removeAllItems();
                cards.addItem("");
                for (String cardName : bindersController.getCollectionCardNames()) {
                    cards.addItem(cardName);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add card. Binder or Card might not exist.");
            }
        });
    }
}
