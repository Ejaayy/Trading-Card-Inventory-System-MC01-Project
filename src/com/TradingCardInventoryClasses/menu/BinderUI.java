package com.TradingCardInventoryClasses.menu;

import java.util.Scanner;

//Uses manage Binder
public class BinderUI {

    //Properties


    //Methods

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
}
