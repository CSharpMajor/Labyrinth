package edu.up.cs301.Labyrinth;

/**
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public class TCard {
    //The symbol of this Treasure Card
    private LabTSymbol symbol;

    //Constructor for the Treasure Card
    public void TCard(LabTSymbol treasure)
    {
        this.symbol = treasure;
    }
    //Getter for the treasure on the card
    public LabTSymbol getTreasure()
    {
        return this.symbol;
    }
}
