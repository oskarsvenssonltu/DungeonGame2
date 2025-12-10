package dungeongame2;

import java.util.ArrayList;

public class Room {

    // Beskrivning av rummet som spelaren är i.
    private String description;

    // Lista med alla dörrar som finns i rummet.
    private ArrayList<Door> doors = new ArrayList<>();

    // Skapar ett rum med en beskrivning.
    public Room(String description) {
        this.description = description;
    }

    // Lägger till en dörr i rummet.
    public void addDoor(Door door) {
        doors.add(door);
    }

    // Skriver ut rumsbeskrivningen och vilka dörrar spelaren kan använda.
    public void doNarrative() {
        System.out.println(description);

        // Går igenom alla dörrar och skriver ut riktningarna.
        for (Door d : doors) {
            switch (d.getDirection()) {
                case "n" -> System.out.println("Du kan gå norrut [n]");
                case "s" -> System.out.println("Du kan gå söderut [s]");
                case "v" -> System.out.println("Du kan gå västerut [v]");
                case "ö" -> System.out.println("Du kan gå österut [ö]");
            }
        }
    }

    // Försöker öppna en dörr i vald riktning.
    // Returnerar nästa rum om dörren finns, annars null.
    public Room tryDoor(String direction) {
        for (Door d : doors) {
            if (d.getDirection().equals(direction)) {
                return d.getNextRoom();
            }
        }
        return null;
    }
}
