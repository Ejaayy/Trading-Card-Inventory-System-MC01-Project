package com.tradingCardInventory.view.panels.ManageBindersView;

import javax.swing.*;

public class ManageBindersPanel extends JPanel {
    public ManageBindersPanel() {
        setLayout(null);

        JLabel label = new JLabel("manage binders prototype", SwingConstants.CENTER);
        label.setBounds(50, 100, 400, 30); // x, y, width, height — adjust as needed

        add(label);
    }
}
