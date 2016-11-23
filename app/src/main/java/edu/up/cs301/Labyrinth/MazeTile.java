package edu.up.cs301.Labyrinth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    //n = 0-3 n*90 = the degrees from normal
    private int orientation=0;

    //boolean array to represent each side of the tile.
    // True if it has a path on it will be rotated by the rotate method
    //the 0th index is the top of the tile it then progresses around clockwise
    private boolean[] pathMap = new boolean[4];

    //the treasure
    private LabTSymbol treasureSymbol;

    /*
     * Welcome to this wonderful constructor takes the type and Symbol
     * the type can be S for straght or L or T
     */
    public MazeTile(char thetype, LabTSymbol symbol)
    {
        type = thetype;
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

    /**
     * copy constructor for MazeTile
     * @param cp the tile we will copy
     */
    public MazeTile( MazeTile cp )
    {
        type = cp.getType();

        boolean[] cpMap = cp.getPathMap();
        for( int i = 0; i < cpMap.length; i++ )
        {
            pathMap[i] = cpMap[i];
        }

        treasureSymbol = cp.getTreasureSymbol();

        occupiedBy.ensureCapacity(4);

        //Collections.copy( occupiedBy, cp.getOccupiedBy() );

        for(int i=0; i < occupiedBy.size(); i++){
          occupiedBy.add(cp.occupiedBy.get(i));
        }
    }

    //getters for all the attributes

    public char getType(){ return type; }
//
//    public int getOrientation(){ return orientation; }

    public boolean[] getPathMap(){ return pathMap; }

    public LabTSymbol getTreasureSymbol(){ return treasureSymbol; }

    public ArrayList<Integer> getOccupiedBy(){ return occupiedBy; }

    //setters for occupiedBy

    public void addPlayer(int player)
    {
        occupiedBy.add(player);
    }

    public void removePlayer(int player){ occupiedBy.remove((Integer)player); }

    public void rotate(int nDegs){
        orientation = nDegs;
        for(int i=1; i<=nDegs; i++) {
            boolean last = pathMap[pathMap.length - 1];          // save off first element

            // shift right
            for (int index = pathMap.length - 2; index >= 0; index--)
                pathMap[index + 1] = pathMap[index];

            // wrap last element into first slot
            pathMap[0] = last;
        }
    }

}
