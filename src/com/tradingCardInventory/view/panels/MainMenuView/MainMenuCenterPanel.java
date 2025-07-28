package com.tradingCardInventory.view.panels.MainMenuView;

import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class MainMenuCenterPanel extends JPanel {
    private Image backgroundImage;

    public MainMenuCenterPanel(CollectionController collectionController) {
        setLayout(null);
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/MainMenuBG.png")).getImage();

        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new  FlowLayout(FlowLayout.CENTER));
        currencyPanel.setOpaque(false);
        currencyPanel.setBounds(225, 20, 200, 30);

        JLabel currencyLabel = new JLabel("Balance: " + collectionController.getCollection().getAmount());
        currencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currencyLabel.setForeground(Color.WHITE);
        currencyPanel.add(currencyLabel);

        add(currencyPanel);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
