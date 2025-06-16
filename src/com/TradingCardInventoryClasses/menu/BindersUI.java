package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageBinders;
import com.TradingCardInventoryClasses.utils.CardUtils;
import com.TradingCardInventoryClasses.model.Collection;

import java.util.Scanner;

//Uses manage Binder
public class BindersUI {

    //Properties
   private ManageBinders manageBinder;
   private CardUtils cardUtils;
   private Scanner scanner;

    //Methods

    //Instantiate necessary properties in constructor
    public BindersUI(ManageBinders manageBinder, Scanner scanner){
        this.manageBinder = manageBinder;
        cardUtils = new CardUtils();
        this.scanner = scanner;
    }

    public int manageBinderMenuTemplate(){
        int input = 0;
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
        int input = 0;
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
                    manageBinder.deleteBinder(nameDelete);

                    break;
                case 3:
                    //Add card to a Binder
                    break;
                case 4:
                    //Remove card from a Binder
                    break;
                case 5:
                    //Trade Card
                    break;
                case 6:
                    //View Binder
                    break;
                case 7:
                    //exit
                    running = false;
                    break;
            }
        }
    }
}
