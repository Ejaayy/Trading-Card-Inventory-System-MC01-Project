package com.tradingCardInventory.view.panels.ManageBindersView;

import javax.swing.*;
import java.awt.*;

public class ManageBindersMenuPanel extends JPanel {

    private Image backgroundImage;

    public ManageBindersMenuPanel() {
        setLayout(null);
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/ManageBindersBG.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
