package com.tradingCardInventory.controllers;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.model.Binders.*;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.ManageBindersView.*;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import java.util.*;

/**
 * The {@code BindersController} class is responsible for handling user interactions
 * related to managing trading card binders in the application.
 * It acts as a controller in the MVC (Model-View-Controller) pattern, coordinating
 * actions between the view and the underlying logic in {@code ManageBinders}.
 *
 * <p>It enables users to create, delete, update, view, and sell binders, as well
 * as add or remove cards from them through the GUI.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class BindersController {

    //Properties
   private final ManageBinders manageBinder;
   private MainView mainView;
   private MenuController menuController;
   private CollectionController collectionController;


    /**
     * Constructs a new {@code BindersController} and initializes dependencies.
     *
     * @param manageBinder the logic handler for binder operations
     * @param mainView the main view used for rendering UI components
     * @param menuController controller used for returning to the main menu
     * @param collectionController controller managing the user’s card collection
     */
    public BindersController(ManageBinders manageBinder, MainView mainView, MenuController menuController, CollectionController collectionController) {
        this.manageBinder = manageBinder;
        this.mainView = mainView;
        this.menuController = menuController;
        this.collectionController = collectionController;
    }

    /**
     * Initializes the left-side navigation panel and sets the default center panel.
     */
    public void run(){
        BindersController bindersController = this;
        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Create Binder", ev ->  mainView.setCenterPanel(new CreateBinderPanel(bindersController)));
            put("Delete Binder", ev -> mainView.setCenterPanel(new DeleteBinderPanel(bindersController)));
            put("Add Card to Binder", ev -> mainView.setCenterPanel(new AddCardToBinderPanel(bindersController)));
            put("Remove Card from Binder", ev -> mainView.setCenterPanel(new RemoveCardFromBinderPanel(bindersController)));
            put("TradeCard", ev -> mainView.setCenterPanel(new TradeCardPanel(collectionController, bindersController)));
            put("View Binder", ev -> mainView.setCenterPanel(new ViewBinderPanel(bindersController)));
            put("Sell Binder", ev -> mainView.setCenterPanel(new SellBinderPanel(bindersController)));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageBindersMenuPanel());

    }

    /**
     * Creates a binder with a given name and type if it does not already exist.
     *
     * @param binderName the name of the new binder
     * @param binderType the type of binder (must match {@link BinderType})
     * @return {@code true} if creation was successful, {@code false} if the binder already exists
     */
    public boolean createBinder(String binderName, String binderType){
        Binder found = manageBinder.searchBinder(binderName);;
        if(found == null){ //if it doesnt exist
            manageBinder.createBinder(binderName, BinderType.valueOf(binderType));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Deletes a binder if it exists.
     *
     * @param binderName the name of the binder to delete
     * @return {@code true} if deletion was successful, {@code false} if not found
     */
    public boolean deleteBinder(String binderName){
        Binder found = manageBinder.searchBinder(binderName);;
        if(found != null){ //if it exists
            manageBinder.deleteBinder( binderName);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds a card to a specified binder.
     *
     * @param cardName the name of the card to add
     * @param binderName the name of the target binder
     * @return {@code true} if the operation succeeded, {@code false} otherwise
     */
    public boolean addCardToBinder(String cardName, String binderName){
        return manageBinder.addCardToBinder(cardName, binderName);
    }

    /**
     * Removes a card from a specified binder.
     *
     * @param cardName the name of the card to remove
     * @param binderName the name of the target binder
     * @return {@code true} if successful, {@code false} otherwise
     */
    public boolean removeCardFromBinder(String cardName, String binderName){
        return manageBinder.removeCardFromBinder(cardName, binderName);

    }

    /**
     * Retrieves a list of all binder names currently managed.
     *
     * @return a {@code List<String>} of binder names
     */
    public List<String> getAllBinderNames(){
        List<String> binderNames = new ArrayList<>();

        for(Binder binder: manageBinder.getBinders()){
            binderNames.add(binder.getName());
        }

        return binderNames;
    }

    /**
     * Retrieves the names of binders that are instances of {@code SellableBinder}.
     *
     * @return a list of names for sellable binders
     */
    public List<String> getAllSellableBinderNames(){
        List<String> binderNames = new ArrayList<>();
        for(Binder binder: manageBinder.getBinders()){
            if(binder instanceof  SellableBinder){
                binderNames.add(binder.getName());
            }
        }
        return binderNames;
    }

    /**
     * Gets all card names stored in a specific binder.
     *
     * @param binderName the name of the binder to inspect
     * @return a list of card names, or {@code null} if binder not found
     */
    public List<String> getAllBinderCardNames(String binderName){
        List<String> binderNames = new ArrayList<>();

        Binder found = manageBinder.searchBinder(binderName);

        if(found != null){
            for(Card card: found.getCards()){
                binderNames.add(card.getName());
            }
            return binderNames;
        }

        return null;
    }

    /**
     * Gets all card names from the user's collection that are available (count > 0).
     *
     * @return a list of available card names in the collection
     */
    public List<String> getCollectionCardNames(){
        List<String> collectionCardNames = new ArrayList<>();
        for(String cardName: collectionController.getAllCardNames()){
            if(collectionController.getCollection().getCard(cardName).getCount() > 0){
                collectionCardNames.add(cardName);
            }
        }
        return collectionCardNames;
    }

    /**
     * Gets a list of {@code Card} objects stored in the specified binder.
     *
     * @param binderName the name of the binder
     * @return a list of {@code Card} objects, or an empty list if not found
     */
    public List<Card> getCards(String binderName){
        return manageBinder.getCards(binderName);
    }

    /**
     * Sells the binder if it meets sellable criteria.
     *
     * @param binderName the name of the binder to sell
     * @return {@code true} if the sale was successful, {@code false} otherwise
     */
    public boolean sellBinder(String binderName) {
       return manageBinder.sellBinder(binderName);
    }

    /**
     * Returns the instance of {@code ManageBinders} being used by this controller.
     *
     * @return the {@code ManageBinders} reference
     */
    public ManageBinders getManageBinder() {
        return manageBinder;
    }
}