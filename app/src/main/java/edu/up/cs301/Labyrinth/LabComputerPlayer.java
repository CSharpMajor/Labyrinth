package edu.up.cs301.Labyrinth;

import java.io.Serializable;
import java.util.Random;

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
 * @version Dec. 2016, Beta
 */
public class LabComputerPlayer extends GameComputerPlayer implements Serializable{
    public LabComputerPlayer(String name) {
        super(name);
    }

    protected void receiveInfo(GameInfo info) {
        //Coordinates for the player to send to the local game
        int xCordMaze;
        int yCordMaze;
        int xCordPiece;
        int yCordPiece;
        Random rand = new Random();

        if (info instanceof LabGameState) {
            //Log.i("Coputer", "recived info");
            LabGameState myGameState = (LabGameState) info;
            //don do anything if it not our turn
            if (this.playerNum != myGameState.getTurnID()) {
                return;
            }
            else {
                //Log.i("Computer", "is my turn");
                //if we have moved the maze move our peice
                if (myGameState.hasMovedMaze()) {
                    //Pick a random spot to move the player
                    xCordPiece = rand.nextInt(6) + 1;
                    yCordPiece = rand.nextInt(6) + 1;
                    //Delay to make it look like the player is thinking
                    //sleep(3000);
                    //Then send the move piece action to the local game
                    //Log.i("computer", "sending move piece action");
                    game.sendAction(new LabMovePieceAction(this, xCordPiece, yCordPiece, this.playerNum));
                }
                //For the push!
                //now we want to make a maze move
                else {
                    xCordMaze = rand.nextInt(8);
                    yCordMaze = rand.nextInt(8);

                    //If the random spot is not even, pick again
                    int legalMove = 0;
                    while(legalMove == 0)
                    {
                        if(xCordMaze == 2 && yCordMaze == 0)
                        {
                            break;
                        }
                        else if(xCordMaze == 4 && yCordMaze == 0)
                        {
                             break;
                        }
                        else if(xCordMaze == 6 && yCordMaze == 0)
                        {
                            break;
                        }
                        else if(xCordMaze == 2 && yCordMaze == 8)
                        {
                            break;
                        }
                        else if(xCordMaze == 4 && yCordMaze == 8)
                        {
                            break;
                        }
                        else if(xCordMaze == 6 && yCordMaze == 8)
                        {
                            break;
                        }
                        else if(xCordMaze == 0 && yCordMaze == 2)
                        {
                            break;
                        }
                        else if(xCordMaze == 0 && yCordMaze == 4)
                        {
                            break;
                        }
                        else if(xCordMaze == 0 && yCordMaze == 6)
                        {
                            break;
                        }
                        else if(xCordMaze == 8 && yCordMaze == 2)
                        {
                            break;
                        }
                        else if(xCordMaze == 8 && yCordMaze == 4)
                        {
                            break;
                        }
                        else if(xCordMaze == 8 && yCordMaze == 6)
                        {
                            break;
                        }
                        xCordMaze = rand.nextInt(8);
                        yCordMaze = rand.nextInt(8);
                    }
                    //Log.i("xCord", "" + xCordMaze);
                    //Log.i("yCord", "" + yCordMaze);
                    //Delay to make it look like the player is thinking
                    sleep(1000);
                    //Then send the move maze action to the local game
                    //game.sendAction(new LabMoveExtraTile(this, xCordMaze, yCordMaze));
                    //int[] coordinates = myGameState.findExtraTile();
                    //Log.i("computer", "sending move maze action");
                    game.sendAction(new LabMoveExtraTile(this, xCordMaze, yCordMaze));
                    sleep(1000);

                    game.sendAction( new LabMoveMazeAction(this, xCordMaze, yCordMaze));
                }
            }
        }
    }
}
