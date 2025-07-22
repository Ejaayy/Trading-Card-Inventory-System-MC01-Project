package com.tradingCardInventory.view;

import javax.swing.*;

public class MainMenuPanel extends JPanel {
    public JButton btnManageCollection = new JButton("Manage Collection");
    public JButton btnManageBinders = new JButton("Manage Binders");
    public JButton btnManageDecks = new JButton("Manage Decks");

    public MainMenuPanel() {
        setLayout(null);
        btnManageCollection.setBounds(250, 200, 200, 40);
        btnManageBinders.setBounds(250, 260, 200, 40);
        btnManageDecks.setBounds(250, 320, 200, 40);

        add(btnManageCollection);
        add(btnManageBinders);
        add(btnManageDecks);
    }
}
