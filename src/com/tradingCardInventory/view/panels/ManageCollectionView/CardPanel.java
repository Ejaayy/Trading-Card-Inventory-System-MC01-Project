package com.tradingCardInventory.view.panels.ManageCollectionView;

import javax.swing.*;
import java.awt.*;

class CardPanel extends JPanel {
    private Image bgImage;

    public CardPanel(String bgPath) {
        bgImage = new ImageIcon(getClass().getResource(bgPath)).getImage();
        setOpaque(false); // transparent so paintComponent controls bg
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(170, 220));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
