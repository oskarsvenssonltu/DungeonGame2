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

// Konstruktor för monster.
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

 // Minskar monstrets hälsa när det tar skada.
     
    public void takeDamage(int amount) {
        healthPoints -= amount;
    }

 // Kollar om monstret lever.
    public boolean isAlive() {
        return healthPoints > 0;
    }
}
