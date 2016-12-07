package edu.up.cs301.Labyrinth;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by mikayla on 11/21/2016.
 */

public class LabMoveExtraTile extends GameAction implements Serializable{
    // instance variables: the selected row and column
    private int[] coords;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */

    public LabMoveExtraTile(GamePlayer player, int row, int col)
    {
        // invoke superclass constructor to set the player
        super(player);

        // set the row and column as passed to us
        coords = new int[2];
        this.coords[0] = row;
        this.coords[1] = col;
    }
    public int[] getCoords(){
        return coords;
    }
}
