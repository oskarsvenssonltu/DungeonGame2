package dungeongame2;

/* Basklass för monster i spelet (drake just nu).
  Monster har, namn, hälsa (poäng), skada, en beskrivning. */

public class Monster {

// Monstrets namn.
    private String name;

// Hur mycket liv monstret har.
    private int healthPoints;

// Hur mycket skada monstret gör.
    private int damage;

// Beskrivning som visas när spelaren möter monstret.
    private String monsterDesc;

    
 // Skapar ett monster med namn, HP, skada och beskrivning. 
    
    public Monster(String name, int healthPoints, int damage, String monsterDesc) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.monsterDesc = monsterDesc;
    }

    

 // Getters.

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public String getMonsterDesc() {
        return monsterDesc;
    }

    
    // Kollar om monstret lever.
     
    public boolean isAlive() {
        return healthPoints > 0;
    }

    // Monstret tar skada.
     
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
}

