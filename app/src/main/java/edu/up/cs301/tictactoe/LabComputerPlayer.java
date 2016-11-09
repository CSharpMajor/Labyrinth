package edu.up.cs301.tictactoe;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * This is a really dumb computer player that always just makes a random move
 * it's so stupid that it sometimes tries to make moves on non-blank spots.
 * 
 * @author Steven R. Vegdahl
 * @versio2 July 2013 
 */
public class LabComputerPlayer extends GameComputerPlayer
{
    public LabComputerPlayer()
    {
        super("hi");
    }

    protected void receiveInfo(GameInfo info) {

    }
}
