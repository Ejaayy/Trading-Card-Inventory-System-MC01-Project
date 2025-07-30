package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RemoveCardFromBinderPanel extends JPanel {
    private final JComboBox<String> binders;
    private final JComboBox<String> cards;
    private JButton submitButton;
    private final Image backgroundImage;

    public RemoveCardFromBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20));
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();

        // NORTH: Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Remove Card to from Binder", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        formPanel.setOpaque(false);

        //Drop down for all existing Binders
        JLabel nameBinder =  new JLabel("Binder Name");
        nameBinder.setForeground(Color.WHITE);
        formPanel.add(nameBinder);
        binders = new JComboBox<>();
        binders.addItem("");
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }
        formPanel.add(binders);

        JLabel cardRemove =  new JLabel("Card to Remove");
        cardRemove.setForeground(Color.WHITE);
        formPanel.add(cardRemove);
        cards = new JComboBox<>();
        binders.addActionListener(e -> {
            cards.removeAllItems();
            cards.addItem("");
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
        bottomPanel.setOpaque(false);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
