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

    // Skriver ut ASCII-draken
    public void printDragon() {
        System.out.println(
            "                                                  .~))>>\n"+
            "                                                 .~)>>\n"+
            "                                               .~))))>>>\n"+
            "                                             .~))>>             ___\n"+
            "                                           .~))>>)))>>      .-~))>>\n"+
            "                                         .~)))))>>       .-~))>>)>\n"+
            "                                       .~)))>>))))>>  .-~)>>)>\n"+
            "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
            "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
            "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
            "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
            "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
            "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
            "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
            "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
            "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
            "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
            "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
            " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
            " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
            "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
            "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
            "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
            "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
            "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
            "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
            "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
            "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
            "               ((((~~              / /'              _.'~bb.--~\n"+
            "                                  ((((          __.-~bb.-~\n"+
            "                                              .'  b .~~\n"+
            "                                              :bb ,'\n"+
            "                                              ~~~~\n"
        );
    }
}
