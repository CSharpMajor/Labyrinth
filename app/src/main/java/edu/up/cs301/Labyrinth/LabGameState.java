package edu.up.cs301.Labyrinth;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;


/**
 * Contains the state of a Labyrinth game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 * 
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov. 2016, preAlpha
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
        //sets turn to player 0
        turnID = 0;

        ArrayList<TCard> hand = new ArrayList<TCard>(4);
        for(int i = 0; i < 4; i++) {
            TCard card = new TCard();
            card.num = i;
            hand.add(card);
        }
        cardsToCollect.add(hand);

        ArrayList<TCard> hand2 = new ArrayList<TCard>(4);
        for(int i = 0; i < 4; i++) {
            TCard card = new TCard();
            card.num = i+4;
            hand2.add(card);
        }
        cardsCollected.add(hand2);

        //grosssssss
        maze = new MazeTile[9][9];
        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                maze[r][c] = new MazeTile(0);
            }
        }
    }

    public LabGameState( LabGameState copy )
    {

    }

    public MazeTile[][] getMaze()
    {
        return maze;
    }

    public int getTurnID()
    {
        return turnID;
    }

    public ArrayList<TCard> getPlayerHand( int playerIndex )
    {
        return cardsToCollect.get( playerIndex );
    }

    public ArrayList<TCard> getPlayerCollected( int playerIndex )
    {
        return null;
    }

    public void setMaze( MazeTile[][] newMaze )
    {
        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                maze[r][c] = newMaze[r][c];
            }
        }
    }

    public void collectTCard( int playerIndex )
    {
        TCard toAdd = new TCard();
        toAdd.num = cardsToCollect.get(playerIndex).get(0).num;
        cardsCollected.get(playerIndex).add(toAdd);
        cardsToCollect.get(playerIndex).remove(0);
    }

    public int[] getPlayerCurTile( int playerIndex )
    {
        int[] coords = new int[2];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[i].length; j++)
            {
                if(maze[i][j].occupiedBy.get(0) == playerIndex)
                {
                    coords[0] = i;
                    coords[1] = j;

                }
            }
        }
        return coords;
    }
}
