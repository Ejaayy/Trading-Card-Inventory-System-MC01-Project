package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;

public class AddCardToBinderPanel extends JPanel {
    private JTextField binderName;
    private JTextField cardName;
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
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 20));
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();

        formPanel.add(new JLabel("Binder Name:"));
        binderName = new JTextField();
        formPanel.add(binderName);

        formPanel.add(new JLabel("Card Name to add:"));
        cardName = new JTextField();
        formPanel.add(cardName);

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
            String binder = binderName.getText().trim();
            String card = cardName.getText().trim();

            if (binder.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean success = bindersController.addCardToBinder(card, binder); // You must implement this in BindersController

            if (success) {
                JOptionPane.showMessageDialog(this, "Card successfully added to binder!");
                binderName.setText("");
                cardName.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add card. Binder or Card might not exist.");
            }
        });
    }
}
