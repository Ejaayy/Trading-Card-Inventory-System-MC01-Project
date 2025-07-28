package com.tradingCardInventory.view.panels.ManageBindersView;

import com.tradingCardInventory.controllers.CollectionController;

import javax.swing.*;
import java.awt.*;

public class SellBinderPanel {
    public SellBinderPanel(CollectionController collectionController) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(650, 80));
    }
}
