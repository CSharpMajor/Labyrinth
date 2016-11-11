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
