package dungeongame2;

public class Dragon extends Monster {

   
     // Konstruktor för Drake.
    
    public Dragon(String name, int healthPoints, int damage, String monsterDesc) {
        // Vi kallar super med fast damage = 2
        super(name, healthPoints, damage, monsterDesc);
    }

    //Draken attackerar
    
    public int attack() {
        System.out.println(getName() + " sprutar eld och gör 2 skada.");
        return getDamage();
    }

    
    @Override
    public String getMonsterDesc() {
        return super.getMonsterDesc() + " Den ser uråldrig och mäktig ut, men rör sig långsamt.";
    }
}
