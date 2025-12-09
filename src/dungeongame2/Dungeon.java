package dungeongame2;

import java.util.Scanner;

public class Dungeon {

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Skapa rummen
        Room room1 = new Room("Dörren har rasat bakom dig, du kan bara gå en väg.");
        Room room2 = new Room("Du kommer in i ett mörkt rum, här vill du inte vara kvar.");
        Room room3 = new Room("Du ser ett konstigt ljus i dörren till söder, vågar du gå in eller tar du den säkra vägen tillbaka?");
        Room room4 = new Room("Det starka ljuset kommer från en kista på golvet. Tyvärr kan du inte öppna kistan förräns nästa uppdatering. Gå tillbaka.");
        Room room5 = new Room("Du kommer in till drakarnas altare. Det luktar bränt och du får snabbt kasta dig ned på marken för att inte bli träffad av den eldsprutande draken. Ta dig till nästa rum eller gå tillbaka.");
        Room room6 = new Room("Du tog dig förbi draken. Du ser en dörr i slutet av en lång och farlig hängbo, vågra du gå över bron?");
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
        System.out.println("Välkommen till spelet! Du är nu inne i Drakarnas-borg, försök att hitta ut med livet i behåll.");
        
        System.out.print("Skriv ditt namn: ");
        Player player = new Player(scanner.nextLine(), room1);
        
        System.out.println("");
        
        System.out.printf("Välkommen %s!\n\n", player.getName());

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
                    System.out.println("Grattis " + player.getName() + " - du hittade ut! Du är bäst.");
                    running = false;
                }
            }
        }
        
        scanner.close();
    }
}
