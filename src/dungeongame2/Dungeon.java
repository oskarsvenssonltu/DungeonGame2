package dungeongame2;

import java.util.Scanner;
import java.util.ArrayList;

public class Dungeon {
    
    // Lista med alla rum.    
    private ArrayList<Room> rooms = new ArrayList<>();
    // Startrum för spelaren.
    private Room startRoom;
    // Sista rummet som avslutar spelet.
    private Room lastRoom;
    
    // Skapar spelets rum och kopplar dem till dörrar.
    private void setupGame() {
        
        // Skapar alla rum och lägger dem i arraylisten.
        rooms.add(new Room("Dörren har rasat bakom dig, du kan bara gå en väg.\n"));
        rooms.add(new Room("Du kommer in i ett mörkt rum, här vill du inte vara kvar.\n"));
        rooms.add(new Room("Du ser ett konstigt ljus genom dörren till söder,\nvågar du gå in eller tar du den säkra vägen tillbaka?\n"));
        rooms.add(new Room("Det starka ljuset kommer från en kista på golvet.\nTyvärr kan du inte öppna kistan förrän nästa uppdatering.\nGå tillbaka.\n"));
        rooms.add(new Room("Du kommer in till drakarnas altare.\nDet luktar bränt och du får snabbt kasta dig ned på marken för att\ninte bli träffad av den eldsprutande draken.\nTa dig till nästa rum eller gå tillbaka.\n"));
        rooms.add(new Room("Du tog dig förbi draken. Till öster ser du en dörr i slutet av\nen lång och farlig hängbro, vågar du gå över bron?\n"));
        rooms.add(new Room("Sista rummet"));
        
        // Variabelnamn för att koppla rum med dörrar.
        Room i0 = rooms.get(0);
        Room i1 = rooms.get(1);
        Room i2 = rooms.get(2);
        Room i3 = rooms.get(3);
        Room i4 = rooms.get(4);
        Room i5 = rooms.get(5);
        Room i6 = rooms.get(6);
        
        // Kopplar samman rummen med dörrarna i olika riktningar.
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
        
        // Sätter start och slutrum.
        startRoom = i0; 
        lastRoom = i6; 
    }

    // Startar spelet och innehåller spelloopen.
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        // Förbereder spelet, skapar rum, kopplar dörrar.
        setupGame();

        // Skriver ut välkomsttext och intro.
        System.out.println("Välkommen till spelet!\n\nDu är nu inne i Drakarnas-borg, försök att hitta ut med livet i behåll.\n");

        // Frågar efter spelarens namn och skapar spelaren i startrummet.
        System.out.print("Skriv ditt namn: ");
        Player player = new Player(scanner.nextLine(), startRoom);
        
        System.out.println("");
        
        System.out.printf("Välkommen %s!\n\n", player.getName());

        // Loopen för spelet som körs tills spelaren når sista rummet.
        boolean running = true; 

        while (running) {
            // Hämtar rummet där spelaren befinner sig, skriver ut berättelse och dörrar.
            Room current = player.getCurrentRoom();
            current.doNarrative(); 

            // Frågar spelaren om vilken riktning spelaren vill gå.
            System.out.print("Välj riktning: ");
            String dir = scanner.nextLine().toLowerCase(); 
            
            System.out.println("");
            // Försöker gå igenom dörr i vald riktning.
            Room next = current.tryDoor(dir); 

            // Om det inte finns någon dörr i den riktningen.
            if (next == null) {
                System.out.println("Testa igen!\n"); 
            } else {
                // Flyttar spelaren till nästa rum.
                player.moveTo(next); 

                // Kollar om spelaren nått slutrummet.
                if (next == lastRoom) {
                    System.out.println("Grattis " + player.getName() + " - du hittade ut! Du är bäst.");
                    running = false;
                }
            }
        }
        // Stänger scannern vid spelets slut.
        scanner.close();
    }
}
