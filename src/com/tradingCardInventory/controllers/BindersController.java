package com.tradingCardInventory.controllers;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.model.Binders.Binder;
import com.tradingCardInventory.model.Card;
import com.tradingCardInventory.options.BinderType;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.ManageBindersView.*;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.*;

/*
 * BindersController is responsible for handling user input and interactions
 * related to managing binders. It serves as the controller in the MVC pattern,
 * communicating between the user interface and the ManageBinders logic.
 */
public class BindersController {

    //Properties
   private final ManageBinders manageBinder;
   private Scanner scanner =  new Scanner(System.in);
   private MainView mainView;
   private MenuController menuController;
   private CollectionController collectionController;
   //private ManageBindersMenuPanel manageBindersPanel;
    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageBinder reference to the ManageBinders logic class
     * @param scanner input reader for user interaction
     */
    public BindersController(ManageBinders manageBinder, MainView mainView, MenuController menuController, CollectionController collectionController) {
        this.manageBinder = manageBinder;
        this.mainView = mainView;
        this.menuController = menuController;
        this.collectionController = collectionController;
    }

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
            put("Sell Binder", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
            put("Back", ev -> menuController.loadMainMenu());
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new ManageBindersMenuPanel());

    }

    //DUMMY PANELS
    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel();
        return panel;
    }

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

    /*
     * Handles user input to add a card from the collection to a binder.
     * Displays a success/failure message based on the result.
     */
    public boolean addCardToBinder(String cardName, String binderName){

        return manageBinder.addCardToBinder(cardName, binderName);

    }

    /*
     * Handles user input to remove a card from a binder.
     * Displays a success/failure message based on the result.
     */
    public boolean removeCardFromBinder(String cardName, String binderName){
        return manageBinder.removeCardFromBinder(cardName, binderName);

    }

    public List<String> getAllBinderNames(){
        List<String> binderNames = new ArrayList<>();

        for(Binder binder: manageBinder.getBinders()){
            binderNames.add(binder.getName());
        }

        return binderNames;
    }

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

    public List<Card> getCards(String binderName){
        return manageBinder.getCards(binderName);
    }
}