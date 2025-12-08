package dungeongame2;

public class Player {
    private String name;
    private Room currentRoom;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveTo(Room room) {
        this.currentRoom = room;
    }
}
