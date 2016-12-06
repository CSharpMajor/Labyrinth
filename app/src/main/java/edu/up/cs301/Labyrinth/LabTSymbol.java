package edu.up.cs301.Labyrinth;

/**
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public enum LabTSymbol {
    DRAGON( "dragon" ),
    GHOST( "ghost" ),
    TROLL( "troll" ),
    CANDELABRA( "candelabra" ),
    FLAMING_SWORD( "flaming sword" ),
    JIGGLYPUFF( "jigglypuff" ),
    ASTRONAUT( "benny" ),
    TREBLE_CLEF( "treble clef" ),
    SPIDER( "bat" ),
    COFFEE_MUG( "coffee mug" ),
    KEYS( "keys" ),
    CROWN( "crown" ),
    OWL( "owl" ),
    MOUSE( "mouse" ),
    BOOK( "book" ),
    MOTH( "moth" ),
    GEM( "gem" ),
    BAG_OF_GOLD( "bag o' gold" ),
    RING( "the one ring" ),
    SKULL( "skull" ),
    MAP( "map" ),
    SCARAB( "scarab" ),
    HELMET( "knight's helmet" ),
    EMPTY( "empty" ),
    CHEST( "treasure chest" );

    private String name;

    /*
        PSA: All of the pictures for the LabTSymbols will go into the res/drawable folder
     */

    /**
     * TODO: Assosiate with a picture for the GUI
     * I know how to do this- I'll do it after we get the alpha code working - Nicole
     * @param str the text to be displayed on the tile
     */
    LabTSymbol( String str )
    {
        this.name = str;
    }

    public String getName()
    {
        return name;
    }
    public String getFileId(){
        switch (this) {
            case DRAGON:
                return "dragoncard";
            case GHOST:
                return "ghostcard";
            case TROLL:
                return "goblincard";
            case CANDELABRA:
                return "candlecard";
            case FLAMING_SWORD:
                return "swordcard";
            case JIGGLYPUFF:
                return "jiggpufcard";
            case ASTRONAUT:
                return "bennycard";
            case TREBLE_CLEF:
                return "treblecard";
            case SPIDER:
                return "batcard";
            case COFFEE_MUG:
                return "coffeecard";
            case KEYS:
                return "keyscard";
            case CROWN:
                return "crowncard";
            case OWL:
                return "owlcard";
            case MOUSE:
                return "mousecard";
            case BOOK:
                return "bookcard";
            case MOTH:
                return "mothcard";
            case GEM:
                return "gemcard";
            case BAG_OF_GOLD:
                return "bagofgoldcard";
            case RING:
                return "ringcard";
            case SKULL:
                return "skullcard";
            case MAP:
                return "mapcard";
            case SCARAB:
                return "scarabcard";
            case HELMET:
                return "helmetcard";
            case CHEST:
                return "tboxcard";

        }
        return null;
    }
    public int getRotationCorrection(){
        switch (this) {
            case DRAGON:
                return 2;
            case TROLL:
                return 3;
            case CANDELABRA:
                return 2;
            case FLAMING_SWORD:
                return 2;
            case JIGGLYPUFF:
                return 3;
            case ASTRONAUT:
                return 3;
            case TREBLE_CLEF:
                return 3;
            case SPIDER:
                return 3;
            case COFFEE_MUG:
                return 3;
            case CROWN:
                return 1;
            case OWL:
                return 3;
            case MOUSE:
                return 2;
            case BOOK:
                return 2;
            case GEM:
                return 3;
            case RING:
                return 2;
            case SKULL:
                return 2;
            case MAP:
                return 1;
            case HELMET:
                return 2;
            case CHEST:
                return 2;

        }
        return 0;
    }
}
