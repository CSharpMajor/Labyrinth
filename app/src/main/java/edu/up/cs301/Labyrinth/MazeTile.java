package edu.up.cs301.Labyrinth;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The class that defines each maze tile for the game.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Dec. 2016, Beta
 */

public class MazeTile implements Serializable{
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
            pathMap[0]=false;
            pathMap[1]=false;
            pathMap[2]=true;
            pathMap[3]=true;
        }
        if(type == 'T'){
            pathMap[0]=false;
            pathMap[1]=true;
            pathMap[2]=true;
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

        //copy the maze map
        boolean[] cpMap = cp.getPathMap();
        for( int i = 0; i < cpMap.length; i++ )
        {
            pathMap[i] = cpMap[i];
        }
        //copy the tSymbol
        treasureSymbol = cp.getTreasureSymbol();
        //copy the orrientation
        orientation = cp.getOrientation();
        //copy all the player on the tile
        occupiedBy.ensureCapacity(4);

        //Collections.copy( occupiedBy, cp.getOccupiedBy() );
        //Log.i("copyMazeTile", "size "+cp.occupiedBy.size());
        for(int i=0; i < cp.occupiedBy.size(); i++){
            this.occupiedBy.add(cp.occupiedBy.get(i));
            //Log.i("copyMazeTile", "copied player"+cp.occupiedBy.get(i));
        }
    }

    //getters for all the attributes

    public char getType(){ return type; }

    public boolean[] getPathMap(){ return pathMap; }

    public LabTSymbol getTreasureSymbol(){ return treasureSymbol; }

    public ArrayList<Integer> getOccupiedBy(){ return occupiedBy; }

    public int getOrientation(){
        return orientation;
    }

    //setters for occupiedBy

    public void addPlayer(int player)
    {
        occupiedBy.add(player);
        //Log.i("addPlayer", "added player "+player);
    }

    public void removePlayer(int player){ occupiedBy.remove((Integer)player); }

    //this changes the path map and the orrientation when a rotate action is called
    public void rotate(int nDegs){
        //increiment the orrientation
        orientation += nDegs;
        //shift the path map to the right for a 90 degree roation
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
