package com.tradingCardInventory.view.panels.ManageCollectionView;

import javax.swing.*;


public class ManageCollectionPanel extends JPanel {

    public ManageCollectionPanel() {
        setLayout(null);

        JLabel label = new JLabel("Collection prototype", SwingConstants.CENTER);
        label.setBounds(50, 100, 400, 30); // x, y, width, height — adjust as needed

        add(label);
    }

}
