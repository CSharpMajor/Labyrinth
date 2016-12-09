package edu.up.cs301.Labyrinth;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * this action is for when the player wishes to rotate the tile.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Dec. 2016, Beta
 */

public class LabRotateExtraTileAction extends GameAction implements Serializable{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     *  this will be used by the local game to rotate the tile in the maze
     */
    public LabRotateExtraTileAction(GamePlayer player) {
        super(player);
    }
}
