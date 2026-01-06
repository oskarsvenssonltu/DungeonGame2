package dungeongame2;

/* Dragon ärver från Monster. 
   Den har fasta värden.
 */

public class Dragon extends Monster {

    // Skapar en drake med fasta värden.
     
    public Dragon() {
        super(
            "Drake",
            18, // healthPoints
            1,  // damage
            "En enorm drake tornar upp sig framför dig och sprutar eld!"
        );
    }

    // Utökad beskrivning för draken.
    
    @Override
    public String getMonsterDesc() {
        return super.getMonsterDesc()
                + " Den ser uråldrig och mäktig ut, men rör sig långsamt.";
    }
}
