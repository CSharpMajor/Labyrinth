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
    SPIDER( "spider" ),
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
}
