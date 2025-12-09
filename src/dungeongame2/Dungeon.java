package dungeongame2;

import java.util.Scanner;
import java.util.ArrayList;

public class Dungeon {

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Room> rooms = new ArrayList<>();
        
        rooms.add(new Room("Dörren har rasat bakom dig, du kan bara gå en väg."));
        rooms.add(new Room("Du kommer in i ett mörkt rum, här vill du inte vara kvar."));
        rooms.add(new Room("Du ser ett konstigt ljus i dörren till söder, vågar du gå in eller tar du den säkra vägen tillbaka?"));
        rooms.add(new Room("Det starka ljuset kommer från en kista på golvet. Tyvärr kan du inte öppna kistan förrän nästa uppdatering. Gå tillbaka."));
        rooms.add(new Room("Du kommer in till drakarnas altare. Det luktar bränt och du får snabbt kasta dig ned på marken för att inte bli träffad av den eldsprutande draken. Ta dig till nästa rum eller gå tillbaka."));
        rooms.add(new Room("Du tog dig förbi draken. Du ser en dörr i slutet av en lång och farlig hängbo, vågra du gå över bron?"));
        rooms.add(new Room("Sista rummet"));

        Room i0 = rooms.get(0);
        Room i1 = rooms.get(1);
        Room i2 = rooms.get(2);
        Room i3 = rooms.get(3);
        Room i4 = rooms.get(4);
        Room i5 = rooms.get(5);
        Room i6 = rooms.get(6);
        
        i0.addDoor(new Door("s", i1));
        i1.addDoor(new Door("n", i0));
        i1.addDoor(new Door("s", i2));
        i1.addDoor(new Door("ö", i4));
        i2.addDoor(new Door("s", i3));
        i2.addDoor(new Door("n", i1));
        i3.addDoor(new Door("n", i2));
        i4.addDoor(new Door("v", i1));
        i4.addDoor(new Door("s", i5));
        i5.addDoor(new Door("n", i4));
        i5.addDoor(new Door("ö", i6));

        // Skapa spelare
        System.out.println("Välkommen till spelet! Du är nu inne i Drakarnas-borg, försök att hitta ut med livet i behåll.");
        
        System.out.print("Skriv ditt namn: ");
        Player player = new Player(scanner.nextLine(), i0);
        
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
                if (next == i6) {
                    System.out.println("Grattis " + player.getName() + " - du hittade ut! Du är bäst.");
                    running = false;
                }
            }
        }
        
        scanner.close();
    }
}
