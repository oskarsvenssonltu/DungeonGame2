package dungeongame2;

public class Player {

    // Spelarens namn.
    private String name;

    // Rummet som spelaren befinner sig i nu.
    private Room currentRoom;

    // Skapar en spelare med namn och startrum.
    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
    }
    
    // Hämtar spelarens namn. 
    public String getName() {
        return name;
    }

    // Hämtar rummet som spelaren befinner sig i.
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Flyttar spelaren till nästa rum.
    public void moveTo(Room room) {
        this.currentRoom = room;
    }
}
