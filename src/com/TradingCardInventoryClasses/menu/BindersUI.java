package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageBinders;
import com.TradingCardInventoryClasses.utils.CardUtils;

import java.util.Scanner;

//Uses manage Binder
public class BindersUI {

    //Properties
   private ManageBinders manageBinder;
   private Scanner scanner;
   private CardUtils cardUtils;
    //Methods

    //Instantiate necessary properties in constructor
    public BindersUI(ManageBinders manageBinder, Scanner scanner){
        this.manageBinder = manageBinder;
        cardUtils = new CardUtils();
        this.scanner = scanner;
    }

    public int manageBinderMenuTemplate(){
        int input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Manage Binder Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Binder");
        System.out.println("2. Delete a Binder");
        System.out.println("3. Add card to a Binder");
        System.out.println("4. Remove card from a Binder");
        System.out.println("5. Trade Card");
        System.out.println("6. View Binder");
        System.out.println("7. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");

        return input;
    }

    public void manageBinderMenu(){

        boolean running = true;
        int input;
        String binderName;
        while(running) {
            input = manageBinderMenuTemplate();
            switch (input) {
                case 1:
                    //Create New Binder
                    System.out.print("Enter name of new Binder: ");
                    String nameCreate = scanner.nextLine();
                    manageBinder.createBinder(nameCreate);
                    break;
                case 2:
                    //Delete a Binder
                    System.out.print("Enter name of Binder to be removed: ");
                    String nameDelete = scanner.nextLine();
                    Boolean deleteStatus = manageBinder.deleteBinder(nameDelete);
                    if(deleteStatus){
                        System.out.println("Binder deleted succesfully!");
                    }
                    else{
                        System.out.println("Attempt to delete Binder Failed");
                    }
                    break;
                case 3:
                    //Add card to a Binder
                    this.addCardtoBinder();
                    break;
                case 4:
                    //Remove card from a Binder
                    this.removeCardtoBinder();
                    break;
                case 5:
                    //Trade Card
                    System.out.print("Enter Binder Name to choose the card from: ");
                    // binderName = scanner.nextLine();
                    //manageBinder.tradeCard(binderName);
                    break;
                case 6:
                    //View Binder
                    System.out.print("Enter Binder Name to View: ");
                    binderName = scanner.nextLine();
                    manageBinder.viewSpecificBinder(binderName);
                    break;
                case 7:
                    //exit
                    running = false;
                    break;
            }
        }
    }

    public void addCardtoBinder(){
        //Input and search for Card in Collection
        System.out.print("Enter Card Name from Collection: ");
        String cardName = scanner.nextLine();

        //Input and search Binder in Binders list
        System.out.print("Enter which Binder to add card in: ");
        String binderName = scanner.nextLine();

        boolean status = manageBinder.addCardToBinder(cardName, binderName);
        if(status){
            System.out.printf("Success! Card: %s has been added to Binder: %s\n", cardName, binderName);
        }

    }

    public void removeCardtoBinder(){
        //Input and search for Card in Collection
        System.out.print("Enter Card Name from Collection: ");
        String cardName = scanner.nextLine();

        //Input and search Binder in Binders list
        System.out.println("Enter which Binder to add card in: ");
        String binderName = scanner.nextLine();

        manageBinder.removeCardFromBinder(cardName, binderName);
    }
}
