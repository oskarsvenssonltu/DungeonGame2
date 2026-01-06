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
    public void skatt (){
    System.out.println(
            "                   .--.\n"+
            "              _.-'_:-'||\n"+
            "          _.-'_.-::::'||\n"+
            "     _.-:'_.-::::::'  ||\n"+
            "   .'`-.-:::::::'     ||\n"+
            "  /.'`;|:::::::'      ||_\n"+
            " ||   ||::::::'      _.;._'-._\n"+
            " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
            " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
            "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
            "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
            "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
            "      ||=[ '-._.-\\U/.-'    o |'||\n"+
            "      || '-.]=|| |'|      o  |'||\n"+
            "      ||      || |'|        _| ';\n"+
            "      ||      || |'|    _.-'_.-'\n"+
            "      |'-._   || |'|_.-'_.-'\n"+
            "      '-._'-.|| |' `_.-'\n"+
            "           '-.||_/.-'\n");
        }
}

