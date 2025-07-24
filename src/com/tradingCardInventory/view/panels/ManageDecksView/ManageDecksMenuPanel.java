package com.tradingCardInventory.view.panels.ManageDecksView;

import javax.swing.*;

public class ManageDecksMenuPanel extends JPanel {
    public ManageDecksMenuPanel(){
        setLayout(null);

        JLabel label = new JLabel("Decks protoype", SwingConstants.CENTER);
        label.setBounds(50, 100, 400, 30); // x, y, width, height — adjust as needed

        add(label);
    }
}
