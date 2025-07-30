package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.BindersController;

import javax.swing.*;
import java.awt.*;

public class CreateBinderPanel extends JPanel {
    private JTextField binderName;
    private JComboBox<String> binderType;
    private JButton submitButton;
    private final Image backgroundImage;

    public CreateBinderPanel(BindersController bindersController) {
        setLayout(new BorderLayout(20, 20)); // Set BorderLayout for main panel
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/NormalBG.png")).getImage();
        // ---------- NORTH: Title ----------
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Create a New Binder", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // ---------- CENTER: Form Panel ----------
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 100, 40));
        formPanel.setOpaque(false);

        JLabel binderNameLabel = new JLabel("Binder Name:");
        binderNameLabel.setForeground(Color.WHITE);
        formPanel.add(binderNameLabel);

        binderName = new JTextField();
        formPanel.add(binderName);

        JLabel binderTypeLabel = new JLabel("Binder Type:");
        binderTypeLabel.setForeground(Color.WHITE);
        formPanel.add(binderTypeLabel);
        String[] types = { "NonCurated", "Pauper", "Rares", "Luxury", "Collector"};
        binderType = new JComboBox<>(types);
        formPanel.add(binderType);

        binderName.setPreferredSize(new Dimension(200, 25));
        binderType.setPreferredSize(new Dimension(200, 25));

        add(formPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Submit Button ----------
        submitButton = new JButton("Create Binder");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setOpaque(false);
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

            boolean status = bindersController.createBinder(name, type);
            if (status ) {
                JOptionPane.showMessageDialog(this, "Binder created successfully!");
                binderName.setText(""); // Clear input
                binderType.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Binder creation failed. Maybe the name already exists?");
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
