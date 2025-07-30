package com.tradingCardInventory.controllers;

import com.tradingCardInventory.manager.ManageBinders;
import com.tradingCardInventory.manager.ManageDeck;
import com.tradingCardInventory.model.Collection;
import com.tradingCardInventory.view.MainView;
import com.tradingCardInventory.view.panels.MainMenuView.StartMenuPanel;
import com.tradingCardInventory.view.panels.MainMenuView.MainMenuCenterPanel;
import com.tradingCardInventory.view.panels.NavigationView.NavigationPanel;
import java.util.LinkedHashMap;

/**
 * The {@code MenuController} class serves as the central controller for the application,
 * coordinating the initialization and interaction of all major modules:
 * Collection, Binders, and Decks. It sets up the GUI navigation and loads the appropriate
 * views using the {@link MainView} interface.
 *
 * <p>This controller acts as the entry point of the application and handles switching
 * between the start menu and the main menu, as well as delegating control to
 * individual module controllers like {@code CollectionController},
 * {@code BindersController}, and {@code DeckController}.</p>
 *
 * <p>Follows the MVC architecture.</p>
 *
 * @author Edriene Paingan & Franz Magbitang
 * @version 2.0
 */
public class MenuController {

    // Properties and Attributes
    private final Collection collection;
    private final ManageBinders manageBinder;
    private final ManageDeck manageDeck;
    private final CollectionController collectionController;
    private final BindersController bindersController;
    private final DeckController decksController;
    private MainView mainView;
    private StartMenuPanel startMenuPanel;
    private MainMenuCenterPanel mainMenuPanel;

    /**
     * Constructs the {@code MenuController} and initializes all major system components.
     *
     * <p>Pre-condition:
     * - Supporting classes like {@code Collection}, {@code ManageBinders}, etc., must be defined.</p>
     *
     * <p>Post-condition:
     * - All modules are initialized and ready to be run via {@code run()}.</p>
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

    /**
     * Launches the application window and sets up the start menu, including button listeners
     * for "Start" and "Exit".
     */
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

    /**
     * Loads the main menu navigation bar and center panel, allowing users to
     * choose between managing the collection, binders, or decks.
     */
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

    /**
     * Gets the {@link MainMenuCenterPanel} instance managed by this controller.
     *
     * @return the main menu panel used in the GUI
     */
    public MainMenuCenterPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

}