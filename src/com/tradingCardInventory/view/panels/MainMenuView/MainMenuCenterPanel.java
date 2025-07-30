package com.tradingCardInventory.view.panels.MainMenuView;

import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class MainMenuCenterPanel extends JPanel {
    private Image backgroundImage;
    private JLabel currencyLabel;
    private CollectionController collectionController;

    public MainMenuCenterPanel(CollectionController collectionController) {
        this.collectionController = collectionController;

        setLayout(null);
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/MainMenuBG.png")).getImage();

        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        currencyPanel.setOpaque(false);
        currencyPanel.setBounds(225, 20, 200, 30);

        currencyLabel = new JLabel("Balance: " + collectionController.getCollection().getAmount());
        currencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currencyLabel.setForeground(Color.WHITE);
        currencyPanel.add(currencyLabel);

        add(currencyPanel);
    }

    public void updateBalanceDisplay() {
        currencyLabel.setText("Balance: " + collectionController.getCollection().getAmount());
        repaint(); // optional, helps visually
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
