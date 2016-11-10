package edu.up.cs301.Labyrinth;

import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;


/**
 * Contains the state of a Tic-Tac-Toe game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 * 
 * @author Steven R. Vegdahl 
 * @version July 2013
 */
public class LabGameState extends GameState
{
    //this is the board with a buffer region on either side
    private MazeTile[][] maze = new MazeTile[9][9];

    //the current players id
    int turnID;

    //all the hands of all the player's cards to collect
    private ArrayList<ArrayList<TCard>> cardsToCollect = new ArrayList<ArrayList<TCard>>(0);

    //all the hands of all the player's cards they have collected
    private ArrayList<ArrayList<TCard>> cardsCollected = new ArrayList<ArrayList<TCard>>(0);

    //boolean to ensure the player has moved the maze first
    private boolean hasMovedMaze;


    /*
    Welcome to the constructor this is where we create the game
     */
    public LabGameState(){

    }

    public LabGameState( LabGameState copy )
    {

    }

    public MazeTile[][] getMaze()
    {
        return null;
    }

    public int getTurnID()
    {
        return -1;
    }

    public ArrayList<TCard> getPlayerHand( int playerIndex )
    {
        return null;
    }

    public ArrayList<TCard> getPlayerCollected( int playerIndex )
    {
        return null;
    }

    public void setMaze( MazeTile[][] newMaze )
    {

    }

    public void collectTCard( int playerIndex )
    {

    }

    public int[] getPlayerCurTile( int playerIndex )
    {
        return null;
    }
}
