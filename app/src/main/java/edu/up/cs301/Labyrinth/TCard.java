package edu.up.cs301.Labyrinth;

import java.io.Serializable;

/**
 * The class that defines the treasure card.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Dec. 2016, Beta
 */

public class TCard implements Serializable{
    //The symbol of this Treasure Card
    private LabTSymbol symbol;

    //Constructor for the Treasure Card
    public TCard(LabTSymbol treasure)
    {
        this.symbol = treasure;
    }
    //Getter for the treasure on the card
    public LabTSymbol getTreasure()
    {
        return this.symbol;
    }
}
