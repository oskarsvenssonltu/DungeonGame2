package dungeongame2;

public class Door {
    private String direction;
    private Room nextRoom;

    public Door(String direction, Room nextRoom) {
        this.direction = direction;
        this.nextRoom = nextRoom;
    }

    public String getDirection() {
        return direction;
    }

    public Room getNextRoom() {
        return nextRoom;
    }
}

