package com.tradingCardInventory.view.panels.NavigationView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * A reusable panel for navigation buttons displayed vertically (typically on the left side).
 * Takes a map of button labels and their corresponding actions.
 */
public class NavigationPanel extends JPanel {

    /**
     * Constructs a NavigationPanel with the given navigation items.
     *
     * @param navItems A map where the key is the button label and the value is the ActionListener for that button.
     */
    private Image backgroundImage;

    public NavigationPanel(Map<String, ActionListener> navItems) {
        // Load background image
        backgroundImage = new ImageIcon(getClass().getResource("/com/tradingCardInventory/view/resources/navbg.png")).getImage();

        // Set BorderLayout instead of BoxLayout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 600));
        setBackground(new Color(230, 230, 230));
        setOpaque(false); // so background shows

        // Panel to hold navigation buttons vertically
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); //for spacing at top
        buttonPanel.setOpaque(false);

        for (Map.Entry<String, ActionListener> entry : navItems.entrySet()) {
            String label = entry.getKey();
            ActionListener action = entry.getValue();

            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // still center inside Y_AXIS panel
            button.setMaximumSize(new Dimension(200, 40));
            button.addActionListener(action);

            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
            buttonPanel.add(button);
        }

        // Add the button panel to the center or west — depending on your layout
        add(buttonPanel, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
