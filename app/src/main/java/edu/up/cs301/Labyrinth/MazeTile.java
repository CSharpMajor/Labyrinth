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

//    //S(straight) T or L
//    private char type;
//
//    //n = 0-3 n*90 = the degrees from noraml
//    private int orientation;

    //boolean array to represent each side of the tile.
    // True if it has a path on it will be rotated by the rotate method
    //the 0th index is the top of the tile it then progresses around clockwise
    private boolean[] pathMap;

    //the treasure
    private LabTSymbol treasureSymbol;

    /*
     * Welcome to this wonderful constructor takes the type and Symbol
     * the type can be S for straght or L or T
     */
    public MazeTile(char type, LabTSymbol symbol)
    {
        pathMap = new boolean[4];
        if(type == 'S'){
            pathMap[0]=false;
            pathMap[1]=true;
            pathMap[2]=false;
            pathMap[3]=true;
        }
        if(type == 'L'){
            pathMap[0]=true;
            pathMap[1]=true;
            pathMap[2]=false;
            pathMap[3]=false;
        }
        if(type == 'T'){
            pathMap[0]=false;
            pathMap[1]=true;
            pathMap[2]=false;
            pathMap[3]=true;
        }
        treasureSymbol = symbol;
    }

    //getters for all the attributtes

//    public char getType(){ return type; }
//
//    public int getOrientation(){ return orientation; }

    public LabTSymbol getTreasureSymbol(){ return treasureSymbol; }

    public ArrayList<Integer> getOccupiedBy(){ return occupiedBy; }

    //setters for occupiedBy

    public void addPlayer(int player)
    {
        occupiedBy.add(player);
    }

    public void removePlayer(int player){ occupiedBy.remove((Integer)player); }

    public void rotate(int nDegs){

    }




}
