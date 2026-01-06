package dungeongame2;

// Klassen representerar en dörr mellan två rum.
// En dörr har: Riktning (n, s, v, ö), Ett rum som den leder till, status låst eller olåst.

public class Door {

// Vilken riktning dörren går åt (n, s, v, ö).
    private String direction;

// Rummet som dörren leder till.
    private Room nextRoom;

// Om dörren är låst eller inte.
    private boolean locked;

// Konstruktor för olåst dörr.
     public Door(String direction, Room nextRoom) {
     this(direction, nextRoom, false);
    }

// Konstruktor för dörr som kan vara låst.
      public Door(String direction, Room nextRoom, boolean locked) {
        this.direction = direction;
        this.nextRoom = nextRoom;
        this.locked = locked;
    }

// Getters 

    public String getDirection() {
        return direction;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public boolean isLocked() {
        return locked;
    }

 // Spelare försöker låsa upp dörr.
 // Returnerar true om dörr blir upplåst eller redan är öppen.
     
    public boolean tryUnlock(Player player) {

 // Om dörren redan är öppen.
        if (!locked) {
            return true;
        }

 // Om spelaren har nyckel.
        if (player.hasKey()) {
            locked = false;
            return true;
        }

 // Dörren förblir låst.
        return false;
    }
}




            
