package com.tradingCardInventory.controllers;

import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.options.Rarity;
import com.tradingCardInventory.options.Variant;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.ManageCollectionView.*;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code CollectionController} class is responsible for handling user interactions
 * related to managing the card collection in the application.
 * It serves as the controller in the MVC (Model-View-Controller) architecture,
 * interacting with the {@link Collection} model and updating the {@link MainView}.
 *
 * <p>Responsibilities include adding, modifying, selling, and displaying cards, as well as
 * updating the view and triggering appropriate UI components.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class CollectionController{

    //Properties
    private final Collection collection;
    private MainView mainView;
    private MenuController menuController;

    //Methods

    /**
     * Constructs a {@code CollectionController} with references to the collection,
     * view, and menu controller.
     *
     * @param collection the card collection model
     * @param mainView the application's main view
     * @param menuController the controller for the main menu
     */
    public CollectionController(Collection collection, MainView mainView, MenuController menuController) {
        this.collection = collection;
        this.mainView = mainView;
        this.menuController = menuController;
    }

    /**
     * Initializes the UI by setting up the left navigation panel with options
     * related to managing the collection, and sets the center panel to the collection menu.
     */
    public void run(){
        CollectionController controller = this;

        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Add Card", ev ->  mainView.setCenterPanel(new AddCardPanel(controller)));
            put("Edit Card Count", ev ->  mainView.setCenterPanel(new EditCardCountPanel(controller)));
            put("Display Collection", ev ->  mainView.setCenterPanel(new DisplayCollectionPanel(controller)));
            put("Sell Card", ev -> mainView.setCenterPanel(new SellCardPanel(controller, menuController)));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageCollectionMenuPanel());

    }

    /**
     * Gets the {@code Collection} instance managed by this controller.
     *
     * @return the {@code Collection} object
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * Returns a list of all card names in the collection.
     *
     * @return a {@code List<String>} of card names
     */
    public List<String> getAllCardNames(){
        List<String> cardNames = new ArrayList<>();

        for(Card card: collection.getAllCards()){
            cardNames.add(card.getName());
        }

        return cardNames;
    }

    /**
     * Changes the count of a specified card in the collection.
     *
     * @param cardName the name of the card to modify
     * @param count the number to increase/decrease the count by
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    public boolean increaseDecrease(String cardName, int count) {

        return this.collection.changeCardCount(cardName, count);

    }

    /**
     * Adds a new card to the collection or increments the count if it already exists.
     *
     * @param cardName the name of the card
     * @param rarityInput the rarity of the card as a string (must match {@link Rarity})
     * @param variantInput the variant of the card as a string (must match {@link Variant})
     * @param valueInput the value of the card (must be > 0)
     * @return {@code true} if the card was successfully added or updated, {@code false} otherwise
     */
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

    /**
     * Sells a card from the collection by reducing its count and increasing the user's balance.
     * Also updates the main menu's balance display.
     *
     * @param cardName the name of the card to sell
     * @param menuController the controller managing the main menu (used to update UI)
     * @return {@code true} if the sale was successful, {@code false} if the card does not exist
     */
    public boolean sellCard(String cardName, MenuController menuController) {
        Card card = collection.searchCard(cardName);

        if(card == null) {
            return false;
        }

        collection.changeAmount(card.getActualValue());
        card.incrementCount(-1);
        menuController.getMainMenuPanel().updateBalanceDisplay();
        System.out.println("Sold the card for" + card.getActualValue());
        System.out.println(collection.getAmount());
        return true;
    }
}