package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.model.Binder;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.ManageBindersView.*;
import com.tradingCardInventory.view.panels.ManageDecksView.AddCardPanel;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

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
   //private ManageBindersMenuPanel manageBindersPanel;
    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageBinder reference to the ManageBinders logic class
     * @param scanner input reader for user interaction
     */
    public BindersController(ManageBinders manageBinder, MainView mainView, MenuController menuController){
        this.manageBinder = manageBinder;
        this.mainView = mainView;
        this.menuController = menuController;
    }

    public void run(){
        BindersController bindersController = this;
        //Used LinkedHashMap so that it will be ordered in NavBar
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Create Binder", ev ->  mainView.setCenterPanel(new CreateBinderPanel(bindersController)));
            put("Delete Binder", ev -> mainView.setCenterPanel(new DeleteBinderPanel(bindersController)));
            put("Add Card to Binder", ev -> mainView.setCenterPanel(new AddCardToBinderPanel(bindersController)));
            put("Remove Card from Binder", ev -> mainView.setCenterPanel(new RemoveCardFromBinderPanel(bindersController)));
            put("TradeCard", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
            put("View Binder", ev -> mainView.setCenterPanel(createPlaceholderPanel("Manage Decks")));
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

    public boolean createBinder(String binderName){
        Binder found = manageBinder.searchBinder(binderName);;
        if(found == null){ //if it doesnt exist
            manageBinder.createBinder(binderName);
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
     * Main loop that processes user interaction with the binder management system.
     * Allows users to manage binders and perform card operations.
     */
    public void manageBinderMenu(){

        boolean running = true;
        String binderName;
        String cardName;

        //Continues to run until the user  chooses to exit ( Case 0 )
        while(running) {

            //Calls Binder Menu template
            //int input = manageBinderMenuTemplate();

            switch (1) {
                case 1:
                    //Create New Binder
                    System.out.println("[0. Exit]");
                    System.out.print("Enter name of new Binder: ");
                    String nameCreate = scanner.nextLine();

                    if(!nameCreate.equals("0")){

                        //Checks if binder already exists
                        if(manageBinder.searchBinder(nameCreate) != null){
                            System.out.println("Binder already exists!");
                        }else{
                            //Creates unique binder
                            manageBinder.createBinder(nameCreate);
                            System.out.println("Success! Binder created successfully!");
                        }
                    }
                    break;
                case 2:
                    //Delete a Binder
                    System.out.println("[0. Exit]");
                    System.out.print("Enter name of Binder to be removed: ");
                    String nameDelete = scanner.nextLine();

                    if(!nameDelete.equals("0")){
                        boolean deleteStatus = manageBinder.deleteBinder(nameDelete);
                        if(deleteStatus){
                            System.out.println("Success! Binder deleted successfully!");
                        }
                        else{
                            System.out.println("Attempt to delete Binder Failed");
                        }
                    }
                    break;
                case 3:
                    //Add card to a Binder
                    System.out.println("[0. Exit]");
                    //this.addCardToBinder();
                    break;
                case 4:
                    //Remove card from a Binder
                    System.out.println("[0. Exit]");
                    //this.removeCardFromBinder();
                    break;
                case 5:
                    //Trade Card

                    System.out.println("[0. Exit]");
                    //Prompts for Binder in Binder List
                    System.out.print("Enter Binder Name to choose the card from: ");
                    binderName = scanner.nextLine();

                    if(!binderName.equals("0")){
                        //Checks if that binder exists
                        if(manageBinder.searchBinder(binderName) != null) {

                            //Shows cards in that binder
                            manageBinder.viewSpecificBinder(binderName);

                            //Prompts user to choose a card in that binder
                            System.out.print("Choose card to offer: ");
                            cardName = scanner.nextLine();

                            if(!cardName.equals("0")){
                                //Checks for validity of card and that Binder
                                if(manageBinder.removeCardFromBinder(cardName, binderName)){
                                    //Prompt user for incoming card
                                    System.out.println("Input details for Incoming Card: ");
                                    manageBinder.tradeCard(cardName, binderName);
                                } else {
                                    System.out.println("Card not found.");
                                }
                            }

                        }else{
                            System.out.println("Error: Binder not found");
                        }
                    }
                    break;
                case 6:
                    //View Binder
                    System.out.println("[0. Exit]");
                    System.out.print("Enter Binder Name to View: ");
                    binderName = scanner.nextLine();

                    if(!binderName.equals("0")){
                        //Calls view specific binder and necessary error handling messages
                        boolean viewBinder = manageBinder.viewSpecificBinder(binderName);
                        if(!viewBinder){
                            System.out.println("Binder not found");
                        }
                    }
                    break;
                case 0:
                    //exit
                    running = false;
                    break;
                default:
                    //Error handling message
                    System.out.println("Invalid option. Please choose between 0 and 6.\n");
            }
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
}