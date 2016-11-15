package edu.up.cs301.Labyrinth;

import java.util.ArrayList;

/**
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public class MazeTile {
    //this is for dummy data
    public int val;

    //the players currently on this tile
    public ArrayList<Integer> occupiedBy = new ArrayList<Integer>(4);

    //S(straight) T or L
    private char type;

    //n = 0-3 n*90 = the degrees from noraml
    private int orientation;

    //the treasure
    private LabTSymbol treasureSymbol;

    /*
     * Welcome to this wonderful constructor takes the type and Symbol
     */
    public MazeTile(char theType, LabTSymbol symbol)
    {
        type = theType;
        treasureSymbol = symbol;
        orientation = 0;
    }

    //getters for all the attributtes

    public char getType(){ return type; }

    public int getOrientation(){ return orientation; }

    public LabTSymbol getTreasureSymbol(){ return treasureSymbol; }

    public ArrayList<Integer> getOccupiedBy(){ return occupiedBy; }

    //setters for occupiedBy

    public void addPlayer(int player)
    {
        occupiedBy.add(player);
    }

    public void removePlayer(int player){ occupiedBy.remove((Integer)player); }




}
