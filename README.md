# DungeonGame2

DungeonGame2 är ett textbaserat äventyrsspel där spelaren navigerar genom en dungeon med flera sammankopplade rum. Varje rum har en beskrivning och dörrar som leder vidare i olika riktningar. Målet är att navigera genom dungeonen och hitta vägen ut. 

Till skillnad från den tidigare versionen innehåller spelet nu:
 * Strider mot ett monster (drake).
 * Föremål som kan plockas upp (svärd, nyckel, skatt).
 * Låsta dörrar och en nyckel.
 * Inventory i form av en väska.

Spelinstruktioner:
1. Starta programmet och skriv ditt namn.
2. Läs beskrivning av det rum du befinner dig i.
3. Se vilka alternativ som finns:
   * Tillgängliga riktningar.
   * Fiender i rummet.
   * Föremål som kan plockas upp.
4. Navigera med n,s,v,ö (nord,söder,väst,öst).
5. Plocka upp föremål med ta <sak> (exempel: ta svärd, ta nyckel, ta skatt).
6. För att visa vad som finns i inventory, skriv väska.
7. Om en dörr är låst krävs en nyckel för att fortsätta.
8. När du når sista rummet avslutas spelet med ett meddelande.

Karta över rummen

[Start]
   |
  [ ] ––– [ ]
   |       |
  [ ]     [ ] –– [Utgång]
   |
[Skatt]

Klassöversikt:
DungeonGame2 (main)
* Startpunkt för programmet.
* Skapar ett Dungeon objekt.
* Startar spelet via dungeon.playGame().

Dungeon
* Skapar och kopplar ihop rum.
* Kopplar ihop dörrar.
* Skapar spelaren.
* Kör huvudloopen.
* Tar emot spelarens val och hanterar förflyttning.

Room
* Rum med beskrivning
* Lista med dörrar och visar möjliga vägar.
* doNarrative() metoden skriver ut info samt möjliga riktningar.

Door
* Dörr mellan två rum med riktning (n,s,v,ö).
* Innehåller referens till rum som dörren leder till.
* Kan vara låst och kräva nyckel.

Player
* Håller spelarens namn och nuvarande rum.
* Metoden moveTo(Room) flyttar spelaren till ett nytt rum.

Monster
* Basklass för fiender.
* Har namn, hälsa och skada.

Dragon
* Ärver från Monster.
* En specifik typ av monster.

Item
* Basklass för alla föremål.
* Kan plockas upp och lagras i inventory.

Weapon
* Ärver från Item.
* Ökar spelarens skada mot monster

Key
* Ärver från Item.
* Används för att låsa upp låsta dörrar.

Treasure
* Ärver från Item.
* Innehåller guld.
         
Antaganden
- Spelaren kan endast gå i fyra riktningar: n, v, s och ö.
- Spelaren har HP och kan förlora spelet om HP når 0. 
- Spelaren kan bara vinna spelet genom att navigera sig fram till det sista rummet.
- Spelaren kan nå sista rummet utan att ha hittat skatten.
- Dörrar kan vara låsta och kräver nyckel för att låsas upp.
- Samma nyckel passar till alla dörrar.
- Skatten kräver ingen nyckel.

Filstruktur:
DungeonGame2.java - start
Dungeon.java - spelvärd och huvudloop
Room.java - rum och hantering av dörrar.
Door.java - riktningar och kopplingar
Player.java - spelardata
Monster.java - baskass för fienden
Dragon.java - specifik fiendetyp
Item.java - Basklass för föremål
Weapon.java - Vapen
Key.java - Nycklar
Treasure.java - Skatt
README.md - dokumentation

Möjliga utökningar
* Karta.
* Alternativa vägar.
* Mer dynamiska rum.
* Fler monster.

Författare:
Oskar Svensson
Emelie Lysell
Eyerusalem Kelati Teweldemedhin
Munkh-Erdene Puntsagbaljir

Programutveckling med Java (D0019N)
