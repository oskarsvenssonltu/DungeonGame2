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

/**
 * Returnerar en sträng med alla riktningar som finns tillgängliga i rummet.
 * Exempel: "n/s/ö".
 *
 * Metoden går igenom alla dörrar i rummet och bygger en text som visar
 * vilka riktningar spelaren kan välja. Mellan varje riktning sätts ett "/".
 *
 * @return En sträng med alla dörrars riktningar.
 */

public String getAvailableDirections() {

         
 // StringBuilder används för att effektivt bygga ihop en text steg för steg
        StringBuilder sb = new StringBuilder();

 // Gå igenom alla dörrar i rummet
        for (Door d : doors) {

  // Om detta inte är den första riktningen, lägg till en "/" som avgränsare
            if (sb.length() > 0) {
                sb.append("/");
            }

  // Lägg till dörrens riktning (t.ex. "n", "s", "v", "ö")
            sb.append(d.getDirection());
        }

  // Gör om StringBuilder till vanlig String och returnera resultatet
        return sb.toString();
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


