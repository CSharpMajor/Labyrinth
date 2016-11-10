package edu.up.cs301.Labyrinth;

import java.util.ArrayList;

/**
 * Created by nakis on 11/8/2016.
 */

public class MazeTile {

    public int val;
    public ArrayList<Integer> occupiedBy = new ArrayList<Integer>(1);

    public MazeTile( int i )
    {
        val = i;
    }

    public void addPlayer(int player)
    {
        occupiedBy.add(player);
    }




}
