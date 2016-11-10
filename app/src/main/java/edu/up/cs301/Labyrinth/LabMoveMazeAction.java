package edu.up.cs301.Labyrinth;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A game-move object that a tic-tac-toe player sends to the game to make
 * a move.
 * 
 * @author Steven R. Vegdahl
 * @version 2 July 2001
 */
public class LabMoveMazeAction extends GameAction
{
	// instance variables: the selected row and column
    private int row;
    private int col;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public LabMoveMazeAction(GamePlayer player) {
        super(player);
    }

/*
    public LabMoveMazeAction(GamePlayer player, int row, int col)
    {
        // invoke superclass constructor to set the player
        super(player);

        // set the row and column as passed to us
        this.row = Math.max(0, Math.min(2, row));
        this.col = Math.max(0, Math.min(2, col));
    }
*/


}