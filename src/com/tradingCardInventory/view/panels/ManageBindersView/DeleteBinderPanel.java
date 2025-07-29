package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;

public class DeleteBinderPanel extends JPanel {
    private JComboBox<String> binders;
    private JButton submitButton;

    public DeleteBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel

        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Delete a Binder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));

        formPanel.add(new JLabel("Binder Name:"));
        binders = new JComboBox<>();
        binders.addItem("");
        for (String binderName : bindersController.getAllBinderNames()) {
            binders.addItem(binderName);
        }
        formPanel.add(binders);

        binders.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Delete Binder");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(650, 60));
        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- Action Listener ----------
        submitButton.addActionListener(e -> {
            String name = (String)  binders.getSelectedItem();

            assert name != null;
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a binder name.");
                return;
            }

            boolean status = bindersController.deleteBinder(name);
            if (status ) {
                JOptionPane.showMessageDialog(this, "Binder deleted successfully!");
                binders.removeItem(name);
            } else {
                JOptionPane.showMessageDialog(this, "Binder deletion failed. Binder may not exist.");
            }
        });
    }
}
