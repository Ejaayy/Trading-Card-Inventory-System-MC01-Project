package com.tradingCardInventory.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;

    public MainView() {
        setTitle("Trading Card Inventory System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public void setTopPanel(JPanel panel) {
        //removes old panel
        if (topPanel != null) {
            getContentPane().remove(topPanel);
        }

        //replaces new panel
        topPanel = panel;
        getContentPane().add(topPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void setLeftPanel(JPanel panel) {
        if (leftPanel != null) {
            getContentPane().remove(leftPanel);
        }
        leftPanel = panel;
        getContentPane().add(leftPanel, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    public void setCenterPanel(JPanel panel) {
        if (centerPanel != null) {
            getContentPane().remove(centerPanel);
        }
        centerPanel = panel;
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setPanel(JPanel panel) {
        getContentPane().removeAll();
        topPanel = null;
        leftPanel = null;
        centerPanel = panel;
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
