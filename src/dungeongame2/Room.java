package dungeongame2;

import java.util.ArrayList;

/* Klassen Room representerar ett rum i spelet.
   Ett rum kan innehålla beskrivning, dörrar, föremål, monster.
*/

public class Room {

    // Rummets namn.
    private String name;

    // Beskrivning av rummet.
    private String description;

    // Dörrar som leder till andra rum.
    private ArrayList<Door> doors = new ArrayList<>();

    // Föremål som finns i rummet.
    private ArrayList<Item> items = new ArrayList<>();

    // Monstret i rummet (null annars).
    private Monster monster;

    // Konstruktor skapar rum med beskrivning.
    public Room(String description) {
        this.description = description;
    }

    // Dörrar
    public void addDoor(Door door) {
        doors.add(door);
    }

    // True om rummet har en dörr i angiven riktning (n/s/v/ö). Används av karta för att rita korridorer.
    public boolean hasDoor(String direction) {
        for (Door d : doors) {
            if (d.getDirection().equals(direction)) {
                return true;
            }
        }
        return false;
    }

    // Föremål
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

    public void removeMonster() {
        monster = null;
    }

    // Skriver ut allt som finns i rummet.
    public void doNarrative() {

        System.out.println(description);

        // Visa monster.
        if (monster != null && monster.isAlive()) {
            System.out.println("Du möter ett monster: " + monster.getName()
                    + " (HP: " + monster.getHealthPoints() + ")");
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

    // Startar strid mellan spelare och monster tills spelaren eller monstret dör.
    public void doBattle(Player player) {

        // Om inget monster finns.
        if (monster == null) {
            return;
        }

        // Om monstret redan har dött.
        if (!monster.isAlive()) {
            monster = null;
            return;
        }

        System.out.println("STRID!");
        System.out.println("Du slåss mot: " + monster.getName());
        System.out.println(monster.getMonsterDesc());
        System.out.println();

        // Loop så länge både spelaren och monstret lever.
        while (player.isAlive() && monster.isAlive()) {

            // Spelaren attackerar.
            System.out.println("Du attackerar " + monster.getName() + "!");
            monster.takeDamage(player.getDamage());

            System.out.println("Monstrets HP: " + monster.getHealthPoints());
            System.out.println("Dina HP: " + player.getHealthPoints());
            System.out.println();

            // Om monstret dör, avsluta strid.
            if (!monster.isAlive()) {
                System.out.println(" Du besegrade " + monster.getName() + "!\n");
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

    /* Försöker gå genom dörr i vald riktning.
       Om monster lever: strid först, spelaren får försöka igen.
       Om dörren är låst: försöker låsa upp.
     */
    public Room tryDoor(String direction, Player player) {

        // Om monster finns och lever, strid.
        if (monster != null && monster.isAlive()) {
            System.out.println("Monstret blockerar vägen!\n");
            doBattle(player);
            return null;
        }

        // Leta efter dörr i rätt riktning.
        for (Door d : doors) {
            if (d.getDirection().equals(direction)) {

                // Om dörren är låst försök låsa upp
                if (d.isLocked()) {
                    boolean unlocked = d.tryUnlock(player);

                    if (!unlocked) {
                        System.out.println("Dörren är låst. Du behöver en nyckel.\n");
                        return null;
                    } else {
                        System.out.println("Du låser upp dörren med nyckeln!\n");
                    }
                }

                return d.getNextRoom();
            }
        }

        // Ingen dörr i den riktningen.
        return null;
    }
}


