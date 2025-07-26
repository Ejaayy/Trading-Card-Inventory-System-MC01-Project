package com.tradingCardInventory.view.panels.ManageCollectionView;

import javax.swing.*;
import java.awt.*;


public class ManageCollectionMenuPanel extends JPanel {

    private Image backgroundImage;

    public ManageCollectionMenuPanel() {
        setLayout(null);
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/ManageCollectionBG.png")).getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
