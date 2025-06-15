# Trading Card Inventory System (TCIS)

## 📌 Update Log
- **2025-06-04**: Created Repo for MC01
- **2025-06-06**: Started the UML Diagram for the project
- **2025-06-10**: Merged Intellij Files to Repo, Made come some of the classes
- **2025-06-10**: Finished Classes, Finished Menus, Organized into a package
- **2025-06-11**: Changes to Card Class, Add Card Method in Collection Class, New Package for default Options
- **2025-06-12**: Sorted Packages, View Collection Feature
- **2025-06-13**: Added Sorting feature of Cards, Increment and Decrement
- **2025-06-14**: Cleaned up Collection Class, Sorted UI Classes, Commented on Collection UI & Collection Class, Ready for next features

## 📋 Overview
The Trading com.TradingCardInventoryClasses.model.Card Inventory System (TCIS) is a command-line application that helps card collectors manage their collections efficiently. It supports tracking of card details, managing binders and decks, and performing basic trades—all while adhering to object-based programming principles.

## ✨ Features
- **Add com.TradingCardInventoryClasses.model.Card**: Add new cards with details like name, rarity, variant, and base value.
- **Increase/Decrease com.TradingCardInventoryClasses.model.Card Count**: Manage the quantity of each card.
- **View com.TradingCardInventoryClasses.model.Collection**: Display all cards sorted alphabetically.
- **Manage Binders**:
  - Create, delete binders
  - Add/remove cards
  - Perform one-for-one trades
- **Manage Decks**:
  - Create, delete decks
  - Add/remove cards (max 1 copy per deck)
- **com.TradingCardInventoryClasses.model.Card Details**: View full details of any card.

## 🔧 Technical Info
- Language: Java
- Interface: Command Line Interface (CLI)
- Libraries: Only Java Standard API (no external libraries)
- Programming Concepts: Object-based design, encapsulation, data hiding

## 📁 Deliverables
- Source code with Javadoc documentation
- UML Class Diagram
- Demo video
- Declaration of Original Work
- Test Script (Appendix B Format)

## ⚠️ Notes
- Program follows the CCPROG3 MCO1 specs.
- GUI will be developed for MCO2.
- Trades must occur through binders, not directly from the collection.
