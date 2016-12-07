package edu.up.cs301.Labyrinth;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by mikayla on 11/30/2016.
 */

public class LabRotateExtraTileAction extends GameAction implements Serializable{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public LabRotateExtraTileAction(GamePlayer player) {
        super(player);
    }
}
