package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageBinders;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * BindersController is responsible for handling user input and interactions
 * related to managing binders. It serves as the controller in the MVC pattern,
 * communicating between the user interface and the ManageBinders logic.
 */
public class BindersController {

    //Properties
   private final ManageBinders manageBinder;
   private Scanner scanner;

    //Methods

    /*
     * Instantiates necessary properties in the constructor.
     *
     * @param manageBinder reference to the ManageBinders logic class
     * @param scanner input reader for user interaction
     */
    public BindersController(ManageBinders manageBinder, Scanner scanner){
        this.manageBinder = manageBinder;
        this.scanner = scanner;
    }

    /*
     * Displays the Manage Binder menu and prompts the user for input.
     *
     * @return the user's selected menu option
     */
    public int manageBinderMenuTemplate(){

        //
        int input = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("MCO1 - Manage Binder Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Binder");
        System.out.println("2. Delete a Binder");
        System.out.println("3. Add card to a Binder");
        System.out.println("4. Remove card from a Binder");
        System.out.println("5. Trade Card");
        System.out.println("6. View Binder");
        System.out.println("0. Exit");
        System.out.print("Enter Choice: ");

        //Error handling for Binders Menu
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear the wrong input
        }
        System.out.println("-------------------------------------------");

        return input;
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
            int input = manageBinderMenuTemplate();

            switch (input) {
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
                    this.addCardToBinder();
                    break;
                case 4:
                    //Remove card from a Binder
                    System.out.println("[0. Exit]");
                    this.removeCardFromBinder();
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
    public void addCardToBinder(){
        //Input and search for Card in Collection
        System.out.print("Enter Card Name from Collection: ");
        String cardName = scanner.nextLine();

        if(!cardName.equals("0")){
            //Input and search Binder in Binders list
            System.out.print("Enter which Binder to add card in: ");
            String binderName = scanner.nextLine();

            if(!binderName.equals("0")){
                //Calls to add that specific card to that specific binder
                boolean status = manageBinder.addCardToBinder(cardName, binderName);
                if(status){
                    System.out.printf("Success! Card: %s has been added to Binder: %s\n", cardName, binderName);
                }
                else{
                    System.out.println("Failed to add card to binder");
                }
            }
        }
    }

    /*
     * Handles user input to remove a card from a binder.
     * Displays a success/failure message based on the result.
     */
    public void removeCardFromBinder(){
        //Input and search for Card in Collection
        System.out.print("Enter Card Name to remove: ");
        String cardName = scanner.nextLine();

        if(!cardName.equals("0")){
            //Input and search Binder in Binders list
            System.out.print("Enter which Binder to remove card in: ");
            String binderName = scanner.nextLine();

            if(!binderName.equals("0")){
                //Calls to remove that specific card to that specific binder
                boolean status = manageBinder.removeCardFromBinder(cardName, binderName);
                if(status){
                    System.out.printf("Success! Card: %s has been removed from Binder: %s\n", cardName, binderName);
                }
                else{
                    System.out.println("Failed to remove card from binder");
                }
            }
        }
    }
}