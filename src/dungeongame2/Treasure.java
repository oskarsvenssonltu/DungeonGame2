package dungeongame2;

// Treasure är skatten i form av Item som ger spelaren guld.


public class Treasure extends Item {

    // Hur mycket guld skatten är värd
    private int goldValue;

    public Treasure(String name, int goldValue) {
        super(name);
        this.goldValue = goldValue;
    }

    public int getGoldValue() {
        return goldValue;
    }
}
