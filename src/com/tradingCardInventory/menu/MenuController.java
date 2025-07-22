package com.tradingCardInventory.menu;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.StartMenuPanel;
import com.tradingCardInventory.view.MainMenuPanel;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Menu class serves as the main controller that initializes all components of the system
 * and presents the main menu for navigating between the Collection, Binders, and Decks modules.
 */
public class MenuController {

    // Properties and Attributes
    Scanner scanner = new Scanner(System.in);

    private final Collection collection;
    private final ManageBinders manageBinder;
    private final ManageDeck manageDeck;
    private final CollectionController collectionController;
    private final BindersController bindersController;
    private final DeckController decksController;
    private MainView mainView;
    private StartMenuPanel startMenuPanel;
    private MainMenuPanel mainMenuPanel;

    /*
     * Constructor initializes all components of the system and wires them together.
     * Pre-condition:
     * - All supporting classes (Collection, ManageBinders, etc.) must be defined.
     * Post-condition:
     * - Ready to run the menu-based system with all modules initialized.
     */
    public MenuController(){
        this.collection = new Collection();
        this.manageBinder = new ManageBinders(collection);
        this.manageDeck= new ManageDeck(collection);
        this.collectionController = new CollectionController(collection, this.scanner);
        this.bindersController = new BindersController(manageBinder, this.scanner);
        this.decksController = new DeckController(manageDeck, this.scanner);
        this.mainView = new MainView();
        this.startMenuPanel = new StartMenuPanel();
        this.mainMenuPanel = new MainMenuPanel();
    }

    public void run() {
        mainView.setPanel(startMenuPanel); // Show Start Menu first
        mainView.setVisible(true);

        startMenuPanel.getBtnStart().addActionListener(e -> {
            mainView.setPanel(mainMenuPanel); // Switch to Main Menu
        });

        startMenuPanel.getBtnExit().addActionListener(e ->{
            System.exit(0);
        });
    }

    private void setupMainMenuListeners() {
        mainMenuPanel.getBtnManageCollection().addActionListener(e -> {
            // open collection module
            collectionController.run(); // or whatever method handles the logic
        });

        mainMenuPanel.getBtnManageBinders().addActionListener(e -> {
            bindersController.run();
        });

        mainMenuPanel.getBtnManageDecks().addActionListener(e -> {
            decksController.run();
        });

        mainMenuPanel.getBtnExit().addActionListener(e -> {
            System.exit(0); // Exit the program
        });
    }

}