package com.tradingCardInventory.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        setTitle("Trading Card Inventory System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());
        //setLocationRelativeTo(null); // center window
    }

    public void setPanel(JPanel panel) {
        getContentPane().removeAll();  // remove current panel
        getContentPane().add(panel, BorderLayout.CENTER); // add new panel
        revalidate();
        repaint();
    }
}