package edu.up.cs301.Labyrinth;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A game-move object that a Labyrinth player sends to the game to make
 * a move.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public class LabMovePieceAction extends GameAction implements Serializable {

    // instance variables: the selected row and column
    private int[] coords;

    private int playerNum;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public LabMovePieceAction(GamePlayer player, int row, int col, int num) {
        super(player);
        // set the row and column as passed to us
        coords = new int[2];
        this.coords[0] = row;
        this.coords[1] = col;
        this.playerNum = num;
    }
     public int[] getCoords(){
         return coords;
     }
    public int getPlayerNum(){
        return playerNum;
    }
}
