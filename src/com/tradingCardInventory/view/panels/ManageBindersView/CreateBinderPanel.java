package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.menu.BindersController;

import javax.swing.*;
import java.awt.*;

public class CreateBinderPanel extends JPanel {
    private JTextField binderName;
    private JComboBox<String> binderType;
    private JButton submitButton;

    public CreateBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel

        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Create a New Binder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));

        formPanel.add(new JLabel("Binder Name:"));
        binderName = new JTextField();
        formPanel.add(binderName);

        formPanel.add(new JLabel("Binder Type:"));
        String[] types = { "type1", "type2", "type3", "type4" };
        binderType = new JComboBox<>(types);
        formPanel.add(binderType);

        binderName.setPreferredSize(new Dimension(200, 25));
        binderType.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Create Binder");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(650, 60));
        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- Action Listener ----------
        submitButton.addActionListener(e -> {
            String name = binderName.getText();
            String type = (String) binderType.getSelectedItem();

            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a binder name.");
                return;
            }

            boolean status = bindersController.createBinder(name);
            if (status ) {
                JOptionPane.showMessageDialog(this, "Binder created successfully!");
                binderName.setText(""); // Clear input
                binderType.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Binder creation failed. Maybe the name already exists?");
            }
        });
    }
}
