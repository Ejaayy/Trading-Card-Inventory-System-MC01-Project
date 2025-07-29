package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RemoveCardFromBinderPanel extends JPanel {
    private final JComboBox<String> binders;
    private final JComboBox<String> cards;
    private JButton submitButton;

    public RemoveCardFromBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20));

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Remove Card to from Binder", SwingConstants.CENTER);
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

        formPanel.add(new JLabel("Card to Remove:"));
        cards = new JComboBox<>();
        binders.addActionListener(e -> {
            cards.removeAllItems();
            if(!Objects.equals(binders.getSelectedItem(), "")) {
                for (String cardName : bindersController.getAllBinderCardNames((String) binders.getSelectedItem())) {
                    cards.addItem(cardName);
                }
            }
        });
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
            String card = (String) cards.getSelectedItem();

            assert binder != null;
            assert card != null;
            if (binder.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean success = bindersController.removeCardFromBinder(card, binder);

            if (success) {
                JOptionPane.showMessageDialog(this, "Card successfully removed from binder!");
                cards.removeItem(card);
                if(cards.getItemCount() == 0){
                    cards.addItem("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove card. Binder or Card might not exist.");
            }
        });
    }
}
