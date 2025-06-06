MERMAID CHART EDIT LINK: 
https://www.mermaidchart.com/app/projects/bf7d6599-8639-46c6-b055-21a148b0eb20/diagrams/d8263eb1-9862-4fec-9693-a64c70a4b5da/share/invite/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkb2N1bWVudElEIjoiZDgyNjNlYjEtOTg2Mi00ZmVjLTk2OTMtYTY0YzcwYTRiNWRhIiwiYWNjZXNzIjoiRWRpdCIsImlhdCI6MTc0OTIxOTQyN30.mpedJpaNDNlL4J0wj1Xd_Z7FyEvdsVdW8XhL4biZcTI

    class Main {

        +main() static void

        %% Assumed that the whole program is your collection

    }
    
    class Menu {
        +displayMainMenu() void
        +displayBinderMenu() void
        +displayDeckMenu() void
    }

    class Card {

        %% Attributes / Properties
            -name : String %% Primary Key
            -count : int
            -rarity : String
                %% common
                %% uncommon
                %% rare
                %% legendary
            -variant : String
                %% Normal – no increase in value.
                %% Extended-art – value is increased by 50%.
                %% Full-art – value is increased by 100%.
                %% Alt-art – value is increased by 200%
            -value : float
        
        %% Methods
            +getName() String
            +getCount() int
            +getRarity() String
            +getVariant() String
            +getVariant() float

            +setName(String name) void
            +setCount(int number) void 
            +setRarity(String rarity) void
            +setVariant(String variant) void
            +setValue(int value) void


    }

    class ManageBinder {
        
        %% Attributes / Properties
        -binders : ArrayList<Binder>

        %% Methods
        + manageBinder()
            %% ArrayList<Binder> binders = new ArrayList<>(); 

        + deleteBinder(Binder binder) void
        + createBinder(String name) void
        + addCard(Card card) void
       
    }

    class Binder {

        %% Attributes / Properties
        -name : String
        -cards : Card[]

        %% Methods
        +Binder()
            %% this.cards = new Card[20]
        
        


    }

    class ManageDeck{
        
    }

    class Deck{
        %% Attributes / Properties
        -cards : Card[]
        
        %% Methods
        +Deck()
        +addCard(Card card) void
        +removeCard(Card card) void
        +viewDeck() void
    }
