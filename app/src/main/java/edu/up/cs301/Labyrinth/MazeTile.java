package edu.up.cs301.Labyrinth;

import java.util.ArrayList;

/**
 * The maze tiles are what make up the game board. The type and orientation will be used to check
 * for connected maze paths. The maze tiles will also keep track of the players who occupy it and
 * if it has a treasure, what that treasure is.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public class MazeTile {

    private ArrayList<Integer> occupiedBy = new ArrayList<Integer>(1);  //lists which players are on
                                                                        // the tile by their integer
                                                                        // value
    private char type; //either T, L, or S value

    private LabTSymbol symbol; //symbol on the tile (if any)

    private int orientation;    //values 0-3, to denote the number of 90 degree clockwise
                                // rotations from the normal

    public MazeTile( char c, LabTSymbol sym )
    {
        type = c;
        symbol = sym;
        orientation = 0;
    }

    public MazeTile( char c, LabTSymbol sym, int or)
    {
        type = c;
        symbol = sym;
        orientation = or;
    }

    public LabTSymbol getLabTSymbol()
    {
        return symbol;
    }

    /**
     * this method takes the index of a player and adds it to the occupiedBy arrayList.
     * This method is used when a player moves to a new tile.
     * @param player the index of the player (0-3)
     */
    public void addPlayer( int player )
    {
        occupiedBy.add(player);
    }

    /**
     * This method takes the integer of the player
     * and removes the player from the occupiedBy arrayList.
     * This method is to be used when a player moves off of a a tile
     * @param player the index of the player (0-3)
     */
    public void removePlayer( int player )
    {
        occupiedBy.remove(occupiedBy.indexOf(player));
    }

    public ArrayList<Integer> getOccupiedBy()
    {
        return occupiedBy;
    }

    public char getType()
    {
        return type;
    }
}
