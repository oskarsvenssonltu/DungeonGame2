package dungeongame2;

// Klassen Door representerar en dörr mellan två rum.
// Varje dörr leder i en viss riktning och pekar på nästa rum.
public class Door {

    // Vilken riktning dörren går åt: n, s, v eller ö.
    private String direction;

    // Rummet som dörren leder till.
    private Room nextRoom;    

    // Skapar en dörr med vald riktning och vilket rum den kopplas till.
    public Door(String direction, Room nextRoom) {
        this.direction = direction;
        this.nextRoom = nextRoom;
    }

    // Hämtar vilken riktning dörren har.
    public String getDirection() {
        return direction;
    }

    // Hämtar rummet som dörren leder till.
    public Room getNextRoom() {
        return nextRoom;
    }
}

