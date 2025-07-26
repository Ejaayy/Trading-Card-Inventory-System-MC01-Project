package com.tradingCardInventory.view.panels.ManageDecksView;

import javax.swing.*;
import java.awt.*;

public class ManageDecksMenuPanel extends JPanel {

    private Image backgroundImage;

    public ManageDecksMenuPanel(){
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/ManageDecksBG.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
