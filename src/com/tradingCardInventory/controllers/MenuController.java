package com.tradingCardInventory.controllers;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.MainMenuView.StartMenuPanel;
import com.tradingCardInventory.view.panels.MainMenuView.MainMenuCenterPanel;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;

import javax.swing.*;
import java.util.LinkedHashMap;
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
    private MainMenuCenterPanel mainMenuPanel;

    /*
     * Constructor initializes all components of the system and wires them together.
     * Pre-condition:
     * - All supporting classes (Collection, ManageBinders, etc.) must be defined.
     * Post-condition:
     * - Ready to run the menu-based system with all modules initialized.
     */
    public MenuController(){
        this.mainView = new MainView();
        this.collection = new Collection();
        this.manageBinder = new ManageBinders(collection);
        this.manageDeck= new ManageDeck(collection);
        this.collectionController = new CollectionController(collection, mainView, this);
        this.bindersController = new BindersController(manageBinder, mainView, this, collectionController);
        this.decksController = new DeckController(manageDeck, mainView, this, collectionController);

        this.startMenuPanel = new StartMenuPanel();
        this.mainMenuPanel = new MainMenuCenterPanel(collectionController);
    }

    public void run() {
        mainView.setPanel(startMenuPanel); // Show Start Menu first
        mainView.setVisible(true);

        startMenuPanel.getBtnStart().addActionListener(e -> {
            //Used LinkedHashMap so that it will be ordered in NavBar
            mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
                put("Manage Collection", ev -> collectionController.run());
                put("Manage Binders", ev -> bindersController.run());
                put("Manage Decks", ev -> decksController.run());
                put("Back", ev -> mainView.setPanel(startMenuPanel));
            }}));

            //Setup center panel content
            mainView.setCenterPanel(new MainMenuCenterPanel(collectionController));
        });

        startMenuPanel.getBtnExit().addActionListener(e ->{
            System.exit(0);
        });
    }

    public void loadMainMenu() {
        mainView.setLeftPanel(new NavigationPanel(new LinkedHashMap<>() {{
            put("Manage Collection", ev -> collectionController.run());
            put("Manage Binders", ev -> bindersController.run());
            put("Manage Decks", ev -> decksController.run());
            put("Back", ev -> mainView.setPanel(startMenuPanel));
        }}));

        //Setup center panel content
        mainView.setCenterPanel(new MainMenuCenterPanel(collectionController));
    }

    public MainMenuCenterPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    //DUMMY PANELS
    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel();
        return panel;
    }
}