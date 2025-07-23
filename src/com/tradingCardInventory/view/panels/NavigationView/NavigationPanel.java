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
    public NavigationPanel(Map<String, ActionListener> navItems) {
        // Set vertical layout (top to bottom)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Set background color to a light gray for UI clarity
        setBackground(new Color(230, 230, 230));

        // Loop through each entry in the navigation map
        for (Map.Entry<String, ActionListener> entry : navItems.entrySet()) {
            String label = entry.getKey();            // Button label
            ActionListener action = entry.getValue(); // Button action

            // Create the button with label
            JButton button = new JButton(label);

            // Center the button horizontally in the panel
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Attach the provided action to the button
            button.addActionListener(action);

            // Set the button to stretch horizontally but have a fixed height
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            // Add vertical spacing before each button
            add(Box.createRigidArea(new Dimension(0, 10)));

            // Add the button to the panel
            add(button);
        }
    }
}
