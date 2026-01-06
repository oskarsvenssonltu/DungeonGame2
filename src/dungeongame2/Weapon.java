package dungeongame2;

/*  Weapon är ett föremål som kan öka spelarens skada på monstret.
    Just nu ett svärd som gör +2 skada på monster (draken).
 */

public class Weapon extends Item {

    // Hur mycket extra skada vapnet ger.
    private int increaseDamage;

    // Skapar ett vapen med ett namn och extra skada.
    
    public Weapon(String name, int increaseDamage) {
        super(name);
        this.increaseDamage = increaseDamage;
    }

    // Returnerar hur mycket extra skada vapnet gör.
     
    public int getIncreaseDamage() {
        return increaseDamage;
    }
}
