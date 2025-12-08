package dungeongame2;

import java.util.ArrayList;

public class Room {
    private String description;
    private ArrayList<Door> doors = new ArrayList<>();

    public Room(String description) {
        this.description = description;
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public void doNarrative() {
        System.out.println(description);

        for (Door d : doors) {
            switch (d.getDirection()) {
                case "n" -> System.out.println("Du kan gå norrut [n]");
                case "s" -> System.out.println("Du kan gå söderut [s]");
                case "v" -> System.out.println("Du kan gå västerut [v]");
                case "ö" -> System.out.println("Du kan gå österut [ö]");
            }
        }
    }

    public Room tryDoor(String direction) {
        for (Door d : doors) {
            if (d.getDirection().equals(direction)) {
                return d.getNextRoom();
            }
        }
        return null;
    }
}


