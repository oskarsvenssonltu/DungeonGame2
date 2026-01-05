
package dungeongame2;

import java.util.ArrayList;

import java.util.Scanner;

// Klass bygger spelet (rum, dörrar och föremål) och kör spelets huvudloop där spelaren interagerar med spelet.

public class Dungeon {

    // Lista med alla rum i spelet (datastruktur enligt uppgiften)
    private ArrayList<Room> rooms = new ArrayList<>();

    // Startrum för spelaren
    private Room startRoom;

    // Slutrum som avslutar spelet
    private Room lastRoom;

    // Enkel karta (2D-grid). Null betyder att inget rum finns där.
    private Room[][] karta;

    // Kartans storlek
    private final int KARTA_BREDD = 5;
    private final int KARTA_HÖJD = 7;

    // Sparar utgångsrummet så vi kan markera det på kartan
    private Room utgångsRum;

    // Skapar spelets rum och kopplar dem med dörrar.
       private void setupGame() {

     // Skapar alla rum och lägger dem i listan
        rooms.add(new Room("Dörren har rasat bakom dig, du kan bara gå en väg.\n"));
        rooms.add(new Room("Du kommer in i ett mörkt rum, här vill du inte vara kvar.\n"));
        rooms.add(new Room("Du ser ett konstigt ljus genom dörren till söder,\nvågar du gå in eller tar du den säkra vägen tillbaka?\n"));
        rooms.add(new Room("Det starka ljuset kommer från en kista på golvet.\nTyvärr kan du inte öppna kistan förrän nästa uppdatering.\nGå tillbaka.\n"));
        rooms.add(new Room("Du kommer in till drakarnas altare.\nDet luktar bränt och du får snabbt kasta dig ned på marken för att\ninte bli träffad av den eldsprutande draken.\nTa dig till nästa rum eller gå tillbaka.\n"));
        rooms.add(new Room("Du tog dig förbi draken. Till öster ser du en dörr i slutet av\nen lång och farlig hängbro, vågar du gå över bron?\n"));
        rooms.add(new Room("Sista rummet\n"));

        // Beskrivande namn på rum
        Room ingång       = rooms.get(0);
        Room mörkSal      = rooms.get(1);
        Room ljusKorridor = rooms.get(2);
        Room skattkammare = rooms.get(3);
        Room drakaltare   = rooms.get(4);
        Room hängbro      = rooms.get(5);
        Room utgång       = rooms.get(6);

        // Kopplar samman rummen med dörrar i olika riktningar

        // Ingång <-> Mörk sal
        ingång.addDoor(new Door("s", mörkSal));
        mörkSal.addDoor(new Door("n", ingång));

        // Mörk sal <-> Ljus korridor
        mörkSal.addDoor(new Door("s", ljusKorridor));
        ljusKorridor.addDoor(new Door("n", mörkSal));

        // Ljus korridor <-> Skattkammare
        ljusKorridor.addDoor(new Door("s", skattkammare));
        skattkammare.addDoor(new Door("n", ljusKorridor));

        // Mörk sal <-> Drakaltare
        mörkSal.addDoor(new Door("ö", drakaltare));
        drakaltare.addDoor(new Door("v", mörkSal));

        // Drakaltare <-> Hängbro
        drakaltare.addDoor(new Door("s", hängbro));
        hängbro.addDoor(new Door("n", drakaltare));

        // Hängbro -> Utgång (låst dörr som kräver nyckel)
        hängbro.addDoor(new Door("ö", utgång, true));

        // Lägger ut en nyckel i ljusKorridor så spelaren kan hitta den
        ljusKorridor.addItem(new Key());

        // Sätter start- och slutrum
        startRoom = ingång;
        lastRoom = utgång;

        
// Skapar kartan
       karta = new Room[KARTA_HÖJD][KARTA_BREDD];

// Vertikal väg
    karta[0][0] = ingång;
    karta[2][0] = mörkSal;
    karta[4][0] = ljusKorridor;
    karta[6][0] = skattkammare;

// Streck åt öst från mörk sal
karta[2][2] = drakaltare;

// Streck ned från drakaltare
karta[4][2] = hängbro;

// Slutrum åt öst från hängbron
karta[4][4] = utgång;

 // Markera utgångsrummet för kartan
   utgångsRum = utgång;
    }

    // Skriver ut en ASCII-karta med både rum och korridorer.

   private void skrivKarta(Player player) {
    System.out.println("KARTA:");

    // Först skapar vi en "rityta" med tecken (strings) som vi kan skriva över
    String[][] canvas = new String[KARTA_HÖJD][KARTA_BREDD];

    // Fyll allt som tomt först
    for (int r = 0; r < KARTA_HÖJD; r++) {
        for (int c = 0; c < KARTA_BREDD; c++) {
            canvas[r][c] = "   "; // tre tecken brett för snyggare utskrift
        }
    }

    // Rita ut rum som rutor
    for (int r = 0; r < KARTA_HÖJD; r++) {
        for (int c = 0; c < KARTA_BREDD; c++) {
            if (karta[r][c] != null) {
                Room här = karta[r][c];

                if (här == player.getCurrentRoom()) {
                    canvas[r][c] = "[X]";
                } else if (här == utgångsRum) {
                    canvas[r][c] = "[E]";
                } else {
                    canvas[r][c] = "[ ]";
                }
            } else {
                // valfritt: visa tomma platser som [#] för debugging
                // canvas[r][c] = "[#]";
                canvas[r][c] = "   ";
            }
        }
    }

// Rita korridorer mellan rum (vi tittar bara åt höger och ner för att undvika dubbla linjer)
    for (int r = 0; r < KARTA_HÖJD; r++) {
        for (int c = 0; c < KARTA_BREDD; c++) {
            Room room = karta[r][c];
            if (room == null) continue;

// Öst: rita bara om rummet har en öst-dörr och det finns ett rum där
      if (c + 2 < KARTA_BREDD && karta[r][c + 2] != null && room.hasDoor("ö")) {
    canvas[r][c + 1] = "───";
}

// Syd: rita bara om rummet har en syd-dörr och det finns ett rum där
     if (r + 2 < KARTA_HÖJD && karta[r + 2][c] != null && room.hasDoor("s")) {
    canvas[r + 1][c] = " │ ";
}

        }
    }

    // Skriv ut canvas (karta)
    for (int r = 0; r < KARTA_HÖJD; r++) {
        for (int c = 0; c < KARTA_BREDD; c++) {
            System.out.print(canvas[r][c]);
        }
        System.out.println();
    }

    System.out.println();
}


    /*
     * Startar spelet och innehåller spelloopen där spelaren interagerar.
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Bygger upp spelets värld
        setupGame();

        // Introtext
        System.out.println("Välkommen till spelet!\n\nDu är nu inne i Drakarnas-borg, försök att hitta ut med livet i behåll.\n");

        // Skapar spelaren i startrummet
        System.out.print("Skriv ditt namn: ");
        Player player = new Player(scanner.nextLine(), startRoom);

        System.out.println();
        System.out.printf("Välkommen %s!\n\n", player.getName());

         boolean running = true;

        while (running) {
            // Hämtar rummet spelaren är i
            Room current = player.getCurrentRoom();

            // Skriver ut beskrivning, items och dörrar
            current.doNarrative();

            // Läser input (kan vara riktning eller kommando)
            System.out.print("Skriv kommando (n/s/v/ö, ta <sak>, karta): ");
            String dir = scanner.nextLine().toLowerCase().trim();
            System.out.println();

            // Kommando: visa karta
            if (dir.equals("karta")) {
                skrivKarta(player);
                continue;
            }

            // Kommando: ta <sak>
            if (dir.startsWith("ta ")) {
                String itemName = dir.substring(3).trim();
                Item item = current.takeItem(itemName);

                if (item == null) {
                    System.out.println("Det finns ingen sådan sak här.\n");
                } else {
                    player.addItem(item);
                    System.out.println("Du plockade upp: " + item.getName() + "\n");
                }
                continue;
            }

            // Försöker gå genom dörr i vald riktning (nyckel-check genom player).
            Room next = current.tryDoor(dir, player);

            // Om det inte finns dörr eller dörren är låst och saknar nyckel
            if (next == null) {
                System.out.println("Testa igen!\n");
            } else {
                // Flyttar spelare till nästa rum.
                player.moveTo(next);

                // Kollar om spelaren nått slutrummet.
                if (next == lastRoom) {
                    System.out.println("Grattis " + player.getName() + " - du hittade ut! Du är bäst.");
                    running = false;
                }
            }
        }

        scanner.close();
    }
}
