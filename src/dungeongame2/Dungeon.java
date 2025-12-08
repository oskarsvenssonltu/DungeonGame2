package dungeongame2;

import java.util.Scanner;

public class Dungeon {

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Skapa rummen
        Room room1 = new Room("Första rummet");
        Room room2 = new Room("Andra rummet");
        Room room3 = new Room("Tredje rummet");
        Room room4 = new Room("Fjärde rummet");
        Room room5 = new Room("Femte rummet");
        Room room6 = new Room("Sjätte rummet");
        Room room7 = new Room("Sjunde rummet");

        // Koppla dörrar
        room1.addDoor(new Door("s", room2));
        room2.addDoor(new Door("n", room1));
        room2.addDoor(new Door("s", room3));
        room2.addDoor(new Door("ö", room5));
        room3.addDoor(new Door("s", room4));
        room3.addDoor(new Door("n", room2));
        room4.addDoor(new Door("n", room3));
        room5.addDoor(new Door("v", room2));
        room5.addDoor(new Door("s", room6));
        room6.addDoor(new Door("n", room5));
        room6.addDoor(new Door("ö", room7));

        // Skapa spelare
        System.out.println("Välkommen till spelet!");
        
        System.out.print("Skriv ditt namn: ");
        String name = scanner.nextLine();
        
        System.out.println("");

        Player player = new Player(name, room1);
        
        System.out.printf("Välkommen %s!\n\n", name);

        // Loopen för spelet
        boolean running = true;

        while (running) {
            Room current = player.getCurrentRoom();
            current.doNarrative();
            
            System.out.print("Välj riktning: ");
            String dir = scanner.nextLine().toLowerCase();
            
            System.out.println("");
            
            Room next = current.tryDoor(dir);

            if (next == null) {
                System.out.println("Testa igen!\n");
            } else {
                player.moveTo(next);

                // Slutet
                if (next == room7) {
                    System.out.println("Grattis - du hittade ut! Du är bäst.");
                    running = false;
                }
            }
        }
        
        scanner.close();
    }
}
