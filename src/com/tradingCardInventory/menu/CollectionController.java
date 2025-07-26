package com.tradingCardInventory.menu;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.ManageCollectionView.AddCardPanel;
import com.tradingCardInventory.view.panels.ManageCollectionView.DisplayCollectionPanel;
import com.tradingCardInventory.view.panels.ManageCollectionView.EditCardCountPanel;
import com.tradingCardInventory.view.panels.ManageCollectionView.ManageCollectionMenuPanel;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/*
 * CollectionController handles user interaction for managing the main card collection.
 * It serves as the "Controller" in the MVC architecture and interacts with the Collection model.
 *
 * It displays menus for collection-related actions, accept and validate user input
 * and call appropriate methods on the Collection model
 */
public class CollectionController{

    //Properties
    private final Collection collection;
    private MainView mainView;
    private MenuController menuController;

    private Scanner scanner = new Scanner(System.in);
    //Methods

    /*
     * Constructs a CollectionController with a given collection and scanner.
     *
     * @param collection the Collection model to be managed
     * @param scanner the Scanner object for user input
     *
     * Pre-condition:
     * - 'collection' and 'scanner' should not be null.
     *
     * Post-condition:
     * - Initializes the controller with references to the collection and scanner.
     */

    public CollectionController(Collection collection, MainView mainView, MenuController menuController) {
        this.collection = collection;
        this.mainView = mainView;
        this.menuController = menuController;
    }

    public void run(){
        CollectionController controller = this;

        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Add Card", ev ->  mainView.setCenterPanel(new AddCardPanel(controller)));
            put("Edit Card Count", ev ->  mainView.setCenterPanel(new EditCardCountPanel(controller)));
            put("Display Collection", ev ->  mainView.setCenterPanel(new DisplayCollectionPanel(controller)));
            put("Sell Card", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageCollectionMenuPanel());

    }

    //DUMMY PANELS
    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel();
        return panel;
    }

    public Collection getCollection() {
        return collection;
    }

    /*
     * Menu logic for handling increases and decreases in specific Cards.
     *
     * Pre-condition:
     * - Scanner and Collection instance must be initialized.
     * - Collection must have methods: increaseCardCount(String), decreaseCardCount(String).
     *
     * Post-condition:
     * - Updates the count of a card based on user input until user exits the menu.
     */
    public boolean increaseDecrease(String cardName, int count) {

        return this.collection.changeCardCount(cardName, count);

    }

    public boolean addInputCard(String cardName, String rarityInput, String variantInput, double valueInput) {
        if (cardName == null || cardName.isEmpty()) {
            return false;
        }

        Card existing = collection.searchCard(cardName);
        if (existing != null) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Card already exists! Increase count?", "Duplicate Card", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                existing.incrementCount(1);
                JOptionPane.showMessageDialog(null, "Card incremented!");
            }
            return true;
        }

        try {
            Rarity rarity = Rarity.valueOf(rarityInput);
            Variant variant = Variant.valueOf(variantInput.toUpperCase());
            double value = valueInput;
            if (value <= 0) {
                JOptionPane.showMessageDialog(null, "Value must be greater than 0.");
                return false;
            }

            collection.addCard(cardName, rarity, variant, value);
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}