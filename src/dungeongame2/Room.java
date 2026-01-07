package dungeongame2;

import java.util.ArrayList;
import java.util.Scanner;
import dungeongame2.Dragon;

/*
 * Klassen Room representerar ett rum i spelet.
 * Ett rum kan innehålla beskrivning, dörrar, föremål, monster.
 */
public class Room {

    // Rummets första text.
    private String firstVisitDescription;

    // Text som visas när man kommer tillbaka.
    private String revisitDescription;

    // Håller koll på om spelaren varit där förut.
    private boolean visited = false;

    // Dörrar som leder till andra rum.
    private ArrayList<Door> doors = new ArrayList<>();

    // Föremål som finns i rummet.
    private ArrayList<Item> items = new ArrayList<>();

    // Monstret i rummet (null annars).
    private Monster monster;

    // Konstruktor samma text varje gång.
    public Room(String description) {
        this.firstVisitDescription = description;
        this.revisitDescription = description;
    }

    // Konstruktor olika text första gången och vid återbesök.
    public Room(String firstVisitDescription, String revisitDescription) {
        this.firstVisitDescription = firstVisitDescription;
        this.revisitDescription = revisitDescription;
    }

    // Dörrar.

    public void addDoor(Door door) {
        doors.add(door);
    }
    

    // Föremål.

    public void addItem(Item item) {
        items.add(item);
    }

    // Tar bort och returnerar ett föremål med visst namn. Null om föremålet inte finns.
    public Item takeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    // Monster.

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    // Text.

    // Skriver ut allt som finns i rummet.
    public void doNarrative() {

        // Rumsbeskrivning, första gången eller vid återbesök.
        if (!visited) {
            System.out.println(firstVisitDescription);
            visited = true;
        } else {
            System.out.println(revisitDescription);
        }

        // Visa monster.
        if (monster != null && monster.isAlive()) {

        System.out.println(
        "Du möter " + monster.getName().toLowerCase() +
        " (" + monster.getHealthPoints() + " HP)."
        );
        System.out.println(monster.getMonsterDesc());
}
        

        // Visa föremål.
        if (!items.isEmpty()) {
            System.out.println("Du ser:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }

        // Visa dörrar.
        for (Door d : doors) {
            String status = d.isLocked() ? " (låst)" : " (öppen)";
            switch (d.getDirection()) {
                case "n" -> System.out.println("Du kan gå norrut [n]" + status);
                case "s" -> System.out.println("Du kan gå söderut [s]" + status);
                case "v" -> System.out.println("Du kan gå västerut [v]" + status);
                case "ö" -> System.out.println("Du kan gå österut [ö]" + status);
            }
        }

        System.out.println();
    }

    // Strid.

    // Startar strid mellan spelare och monster tills spelaren eller monstret dör.
    private void doBattle(Player player) {

        if (monster == null || !monster.isAlive()) {
            monster = null;
            return;
        }

        System.out.println("STRID!");
        System.out.println("Du slåss mot: " + monster.getName() + "!");
        System.out.println();

        while (player.isAlive() && monster.isAlive()) {

            // Spelaren attackerar.
            System.out.println("Du attackerar " + monster.getName() + "!");
            monster.takeDamage(player.getDamage());
            System.out.println("Monstrets HP: " + monster.getHealthPoints());
            System.out.println("Dina HP: " + player.getHealthPoints());
            System.out.println();

            if (!monster.isAlive()) {
                System.out.println("Du besegrade " + monster.getName() + "!\n");
                monster = null;
                return;
            }

            // Monstret attackerar.
            System.out.println(monster.getName() + " attackerar dig!");
            player.takeDamage(monster.getDamage());
            System.out.println("Dina HP: " + player.getHealthPoints());
            System.out.println("Monstrets HP: " + monster.getHealthPoints());
            System.out.println();

            if (!player.isAlive()) {
                System.out.println("GAME OVER! Du blev besegrad av " + monster.getName() + ".\n");
                return;
            }
        }
    }

    // Val för spelare vid dörrar.
    public Room tryDoor(String direction, Player player, Scanner scanner) {

        // Riktning städas lite (så " N " funkar)
        direction = direction.trim().toLowerCase();

        // Monster i vägen spelare gör val.
        if (monster != null && monster.isAlive()) {
            System.out.println("Monstret stoppar dig! Vad vill du göra?");
            System.out.println("[1] Slåss");
            System.out.println("[2] Stå kvar");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                doBattle(player);

                // Om spelaren dör.
                if (!player.isAlive()) {
                    return this;
                }

                // Om monstret dör spelare får välja väg igen.
                return this;
            }

            if (choice.equals("2")) {
                // Stå kvar.
                System.out.println("Du står kvar och iakttar monstret...\n");
                return this;
            }

            // Ogiltigt val.
            System.out.println("Ogiltigt val. Du står kvar.\n");
            return this;
        }

        // Leta efter dörr i rätt riktning.
        for (Door d : doors) {
            if (d.getDirection().equals(direction)) {

                if (d.isLocked()) {
                    boolean unlocked = d.tryUnlock(player);

                    if (!unlocked) {
                        System.out.println("Dörren är låst. Du behöver en nyckel.\n");
                        return null;
                    } else {
                        System.out.println("Du låser upp dörren!\n");
                    }
                }

                return d.getNextRoom();
            }
        }

        return null;
    }
}
