package com.TradingCardInventoryClasses.menu;

import com.TradingCardInventoryClasses.manager.ManageDeck;
import com.TradingCardInventoryClasses.model.Collection;
import com.TradingCardInventoryClasses.utils.CardUtils;

import java.util.Scanner;

//Uses DeckUI
public class DeckUI {

    // Properties
    private ManageDeck manageDeck;
    private CardUtils cardUtils;
    private Scanner scanner;

    //Methods

    //Instantiate necessary properties in constructor
    public DeckUI(ManageDeck manageDeck, Scanner scanner){
        this.manageDeck = manageDeck;
        cardUtils = new CardUtils();
        this.scanner = scanner;

    }

    public int manageDeckMenuTemplate(){
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("MCO1 - Manage Deck Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Create New Deck");
        System.out.println("2. Delete a Deck");
        System.out.println("3. Add card to a deck");
        System.out.println("4. Remove card from a deck");
        System.out.println("5. View Deck");
        System.out.println("6. Exit");
        System.out.print("Enter Choice: ");
        input = scanner.nextInt();
        System.out.println("\n-------------------------------------------");

        return input;
    }

    public void manageDeckMenu(){

        boolean running = true;
        int input = 0;
        while(running) {
            input = manageDeckMenuTemplate();
            switch (input) {
                case 1:
                    //Create New Deck
                    break;
                case 2:
                    //Delete a Deck
                    break;
                case 3:
                    //Add card to a deck
                    break;
                case 4:
                    //Remove card to a deck
                    break;
                case 5:
                    //View Deck
                    break;
                case 6:
                    running = false;
                    break;
            }
        }
    }
}
