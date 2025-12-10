# DungeonGame2

DungeonGame är ett textbaserat äventyrsspel där spelaren navigerar genom en dungeon med flera sammankopplade rum. Varje rum har en beskrivning och dörrar som leder vidare i olika riktningar. Målet är att hitta vägen ut genom att välja rätt riktning i varje rum.

Klassöversikt:
DungeonGame2 (main)
Startpunkt för programmet.
Skapar ett Dungeon objekt och startar spelet via dungeon.playGame().

Dungeon
Skapar och kopplar ihop rum.
Kopplar ihop dörrar
Skapar spelaren
Kör huvudloopen
Tar emot spelarens val och hanterar förflyttning till nytt rum.

Room
Rum med beskrivning
Array lista med dörrar och visar möjliga vägar.
doNarrative() metoden skriver ut info samt möjliga riktningar.

Door
Dörr mellan två rum med riktning (n,s,v,ö).
Innehåller referens till rum som dörren leder till.

Player
Håller spelarens namn och nuvarande rum.
Metoden moveTo(Room) flyttar spelaren till ett nytt rum.

Spelinstruktioner:
1. Starta programmet och skriv ditt namn.
2. Läs beskrivning av rummet.
3. Läs vilka riktningar som finns och välj riktning.
4. Navigera med n,s,v,ö (nord,söder,väst,öst).
5. Om ingen navigation finns åt det valda hållet får du försöka igen.
6. Försök navigera till skatten och hitta vägen ut.
7. När du när sista rummet avslutas spelet med ett meddelande.

Antaganden
- Spelaren kan endast gå i fyra riktningar: n, v, s och ö.
- Spelaren kan inte förlora liv. 
- Spelaren kan bara vinna spelet genom att navigera sig fram till det sista rummet.
- Vi har inte låsta dörrar, nycklar, strider etc.
- Vi hanterar inte föremål eller inventory.

Övrigt:
Vissa rum innehåller en drake, en skatt eller farliga platser. Dessa påverkar inte spelet logiskt i detta steg utan ger en berättelse för spelaren.

Filstruktur:
DungeonGame2.java - start
Dungeon.java - spelvärd och huvudloop
Room.java - rum och hantering av dörrar.
Door.java - riktningar och kopplingar
Player. java - spelardata
README.md - dokumentation

Möjliga utökningar
Strid mot draken.
Skatt och föremål.
Spelarhälsa.
Karta.
Alternativa vägar.
Mer dynamiska rum.

Författare:
Oskar Svensson
Emelie Lysell
Eyerusalem Kelati Teweldemedhin

Programutveckling med Java (D0019N)
