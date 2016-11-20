package edu.up.cs301.Labyrinth;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * This is a really dumb computer player that always just makes a random move
 * it's so stupid that it sometimes tries to make moves on non-blank spots.
 * 
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */
public class LabComputerPlayer extends GameComputerPlayer
{
    public LabComputerPlayer()
    {
        super("hi");
    }

    protected void receiveInfo(GameInfo info) {
        if(info instanceof LabGameState){
            LabGameState myGameState = (LabGameState) info;
            if(this.playerNum != myGameState.getTurnID()){
                return;
            }
            else{
                if(myGameState.hasMovedMaze()){
                    //move piece
                    Random rand = new Random();
                    Log.i("Computer", "moved piece");
                    //random value between 1-8
                    LabMovePieceAction action = new LabMovePieceAction(this, rand.nextInt(8)+1, rand.nextInt(8)+1);
                }
                else{
                    //move maze
                    Random rand = new Random();
                    Log.i("Computer", "moved maze");
                    //LabMoveMazeAction action = new LabMoveMazeAction(this, rand.nextInt());
                    //game.sendAction(action);
                }

            }
        }
    }
}
