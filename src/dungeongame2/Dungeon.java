package dungeongame2;

import java.util.ArrayList;
import java.util.Scanner;

// Klassen bygger spelet med rum, dörrar och föremål. Kör spelets huvudloop.
public class Dungeon {

    // Lista med alla rum i spelet.
    private ArrayList<Room> rooms = new ArrayList<>();

    // Startrum för spelaren.
    private Room startRoom;

    // Slutrum som avslutar spelet.
    private Room lastRoom;

    // Skapar spelets rum och kopplar med dörrar.
    private void setupGame() {

        // Skapar alla rum och lägger dem i listan.
        rooms.add(new Room("Dörren har rasat bakom dig, du kan bara gå en väg.\n"));
        rooms.add(new Room("Du kommer in i ett mörkt rum, här vill du inte vara kvar.\n"));
        rooms.add(new Room("Du ser ett konstigt ljus genom dörren till söder,\nvågar du gå in eller tar du den säkra vägen tillbaka?\n"));

        // Skattkammare första gången och när man kommer tillbaka.
        rooms.add(new Room(
                "Du är nu i Skattkammaren.\nDet starka ljuset kommer från en kista på golvet.\n",
                "Du är tillbaka i Skattkammaren.\nHär ekar det tyst mellan väggarna.\n"
        ));

        // Drakaltare första gången och när man kommer tillbaka.
        rooms.add(new Room(
                "Du kommer in till Drakarnas altare.\nDet luktar bränt och luften känns tung.\n",
                "Du står åter i Drakarnas altare.\nBrännmärken täcker golvet och röken hänger kvar.\n"
        ));

        rooms.add(new Room("Du tog dig förbi draken. Till öster ser du en dörr i slutet av\nen lång och farlig hängbro, vågar du gå över bron?\n"));
        rooms.add(new Room("Sista rummet\n"));

        // Beskrivande namn på rum.
        Room ingång = rooms.get(0);
        Room mörkSal = rooms.get(1);
        Room ljusKorridor = rooms.get(2);
        Room skattkammare = rooms.get(3);
        Room drakaltare = rooms.get(4);
        Room hängbro = rooms.get(5);
        Room utgång = rooms.get(6);

        // Sätter draken i rum drakaltare.
        drakaltare.setMonster(new Dragon());

        // Lägg ett svärd i mörkSal
        mörkSal.addItem(new Weapon("Svärd", 2));

        // Kopplar samman rummen med dörrar i olika riktningar.

        // Ingång <-> Mörk sal.
        ingång.addDoor(new Door("s", mörkSal));
        mörkSal.addDoor(new Door("n", ingång));

        // Mörk sal <-> Ljus korridor.
        mörkSal.addDoor(new Door("s", ljusKorridor));
        ljusKorridor.addDoor(new Door("n", mörkSal));

        // Ljus korridor <-> Skattkammare.
        ljusKorridor.addDoor(new Door("s", skattkammare, true));
        skattkammare.addDoor(new Door("n", ljusKorridor));

        // Mörk sal <-> Drakaltare.
        mörkSal.addDoor(new Door("ö", drakaltare));
        drakaltare.addDoor(new Door("v", mörkSal));

        // Drakaltare <-> Hängbro.
        drakaltare.addDoor(new Door("s", hängbro));
        hängbro.addDoor(new Door("n", drakaltare));

        // Hängbro -> Utgång (låst dörr, kräver nyckel).
        hängbro.addDoor(new Door("ö", utgång, true));

        // Lägger ut en nyckel i ljusKorridor.
        ljusKorridor.addItem(new Key());

        // Lägger till skatten i skattkammare
        skattkammare.addItem(new Treasure());

        // Sätter start och slutrum.
        startRoom = ingång;
        lastRoom = utgång;
    }

    // Startar spelet och innehåller spelloopen.
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Bygger upp spelets värld
        setupGame();

        // Introtext
        System.out.println("Välkommen till spelet!\n\nDu är nu inne i Drakarnas-borg, försök att hitta ut med livet i behåll.\n");

        // Skapar spelaren i startrummet.
        System.out.print("Skriv ditt namn: ");
        Player player = new Player(scanner.nextLine(), startRoom);

        System.out.println();
        System.out.printf("Välkommen %s!\n\n", player.getName());

        // Loopen för spelet som körs tills spelet tar slut.
        boolean running = true;

        while (running) {
            
            // Om spelaren är död efter tidigare strid
            if (!player.isAlive()) {
                running = false;
                break;
            }

            // Hämtar rummet spelaren är i.
            Room current = player.getCurrentRoom();

            // Skriver ut beskrivning, items, dörrar.
            current.doNarrative();

            // Läser kommandon från spelare.
            System.out.print("Skriv kommando (n/s/v/ö, ta <sak>, väska): ");
            String dir = scanner.nextLine().toLowerCase().trim();
            System.out.println();

            // Visa väska / inventory
            if (dir.equals("väska") || dir.equals("vaska") || dir.equals("inv") || dir.equals("inventory")) {
                player.printInventory();
                System.out.println("Guld: " + player.getGold());
                System.out.println("HP: " + player.getHealthPoints());
                System.out.println("Skada: " + player.getDamage() + "\n");
                continue;
            }

            // ta <sak>.
            if (dir.startsWith("ta ")) {
                String itemName = dir.substring(3).trim();
                Item item = current.takeItem(itemName);

                if (item == null) {
                    System.out.println("Det finns ingen sådan sak här.\n");
                } else {
                    player.addItem(item);
                }
                continue;
            }

            // Försöker gå genom dörr i vald riktning.
            // Room hanterar monster val med scanner, slåss eller stå kvar
            Room next = current.tryDoor(dir, player, scanner);

            // Om det inte finns dörr eller spelaren står kvar vid monster.
            if (next == null) {
                System.out.println("Testa igen!\n");
            } else {

                // Flyttar spelare till nästa rum.
                player.moveTo(next);

                // Kollar om spelaren nått slutrummet.
                if (next == lastRoom) {
                    if (player.hasTreasure()) {
                        System.out.println("Grattis " + player.getName() + " - du hittade ut med skatten! Du är bäst.");
                    } else {
                        System.out.println("Grattis " + player.getName() + " - du hittade ut.\nMen du missade den fantastiska skatten - försök igen.");
                    }
                    running = false;
                }
            }
        }

        scanner.close();
    }
}


