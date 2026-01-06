package dungeongame2;

import java.util.ArrayList;

// Klass för spelaren. Spelaren har namn, hälsopoäng, skada, väska (inv), currentRoom.
 
public class Player {

    // Spelarens namn.
    private String name;

    // Spelarens hälsa (startar på 10).
    private int healthPoints;

    // Spelarens grundskada (1 hälsopoäng).
    private int damage;

    // Rummet som spelaren befinner sig i nu.
    private Room currentRoom;

    // Spelarens inventory (väska).
    private ArrayList<Item> inventory = new ArrayList<>();
    
    // Spelarens guld
       private int gold;


    // Konstruktor som skapar spelare med namn och startrum.
    // standardvärden för HP och damage.
     
    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.healthPoints = 10;
        this.damage = 1;
    }

 // Getters.

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public int getGold() {
    return gold;
}


//Setters.

    //Flyttar spelare till nytt rum.
     
    public void moveTo(Room room) {
        this.currentRoom = room;
    }
   
    // Spelare tar skada (minskar HP).
     
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }


    // Returnerar true om spelaren lever.
     
     public boolean isAlive() {
        return healthPoints > 0;
    }

  // Inventory.

   public void addItem(Item item) {
    inventory.add(item);

    // Om spelaren plockar upp ett vapen ökar skada.
    if (item instanceof Weapon weapon) {
        increaseDamage(weapon.getIncreaseDamage());
        System.out.println(" Du utrustar " + weapon.getName()
                + " (+" + weapon.getIncreaseDamage() + " skada)");
    }

    // Om spelaren plockar upp en skatt ökar guld
    if (item instanceof Treasure treasure) {
        addGold(treasure.getGoldValue());
        System.out.println(" Du plockar upp en skatt värd "
                + treasure.getGoldValue() + " guld!");
    }
}


    // Kollar om spelaren har en nyckel i inventory.
  
    public boolean hasKey() {
        for (Item item : inventory) {
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }

    // Skriver ut spelarens inventory.
     
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Din väska är tom.\n");
            return;
        }

        System.out.println("Du bär på:");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
        System.out.println();
    }

    // Ökar spelarens skada.
    public void increaseDamage(int amount) {
        damage += amount;
    }
    // Lägger till guld för spelare.
    public void addGold(int amount) {
    gold += amount;
    
    }
}



    
