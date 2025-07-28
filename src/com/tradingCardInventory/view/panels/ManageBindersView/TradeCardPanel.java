package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class TradeCardPanel extends JPanel {
    TradeCardPanel(CollectionController collectionController) {


        // NORTH: Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));
        add(titlePanel, BorderLayout.NORTH);

        // CENTER: Card
        JPanel centerPanel = new JPanel(new GridLayout());

        JPanel outgoingPanel = new JPanel(new FlowLayout());

        centerPanel.add(outgoingPanel);


        JPanel incomingPanel = new JPanel(new FlowLayout());

        centerPanel.add(incomingPanel);

        // SOUTH: Submit Button Panel
        JButton submitButton = new JButton("Submit");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.gray);
        bottomPanel.setPreferredSize(new Dimension(650, 40));
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
