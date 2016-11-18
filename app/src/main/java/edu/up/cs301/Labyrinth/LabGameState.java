package edu.up.cs301.Labyrinth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;


/**
 * Contains the state of a Labyrinth game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 *
 * TODO CODE THE MAZE CHLOE!!! Then do the deep copy for it
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
        turnID = 1;
        ArrayList<TCard> deck = new ArrayList<TCard>(24);
        for (LabTSymbol sym : LabTSymbol.values()) {
            deck.add(new TCard(sym));
        }

        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(4);
            for(int j = 0; j < 6; j++) {
                Random rand = new Random();
                TCard temp = deck.get(rand.nextInt(deck.size()));
                hand.add(temp);
                deck.remove(temp);
            }
            cardsToCollect.add(hand);
        }
        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(0);
            cardsToCollect.add(hand);
        }



        //grosssssss
        maze = new MazeTile[9][9];
        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                //maze[r][c] = new MazeTile(0);
            }
        }

        //blue player is home
        //maze[1][7].occupiedBy.add(2);

        hasMovedMaze = false;
    }

    public LabGameState( LabGameState copy ){
        turnID = copy.getTurnID();
        hasMovedMaze = copy.hasMovedMaze;

        cardsToCollect = new ArrayList<ArrayList<TCard>>(0);

        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(4);
            for(int j = 0; j < 6; j++) {
                LabTSymbol sym = copy.getPlayerHand(i).get(j).getTreasure();
                TCard temp = new TCard(sym);
                hand.add(temp);
            }
            cardsToCollect.add(hand);
        }

        cardsCollected = new ArrayList<ArrayList<TCard>>(4);

        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(4);
            for(int j = 0; j < 6; j++) {
                LabTSymbol sym = copy.getPlayerCollected(i).get(j).getTreasure();
                TCard temp = new TCard(sym);
                hand.add(temp);
            }
            cardsToCollect.add(hand);
        }



    }

    public boolean hasMovedMaze()
    {
        return hasMovedMaze;
    }

    public void setHasMovedMaze( boolean val )
    {
        hasMovedMaze = val;
    }

    public MazeTile[][] getMaze()
    {
        return maze;
    }

    public int getTurnID()
    {
        return turnID;
    }

    public void setTurnID(int playerIndex) { turnID = playerIndex ; }

    public ArrayList<TCard> getPlayerHand( int playerIndex )
    {
        return cardsToCollect.get( playerIndex );
    }

    public ArrayList<TCard> getPlayerCollected( int playerIndex ) { return cardsCollected.get(playerIndex); }

    public void setMaze( MazeTile[][] newMaze ){
        for( int r = 0; r < 9; r++ ){
            for( int c = 0; c < 9; c++ ){
                maze[r][c] = newMaze[r][c];
            }
        }
    }

    public void collectTCard( int playerIndex ){
        TCard move = cardsToCollect.get(playerIndex).get(0);
        cardsCollected.get(playerIndex).add(move);
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

    /**
     * Row move operation. Assumes the extra tile is in the correct space.
     * @param row the row the extra tile is in
     * @param left whether the tile is coming from the left or the right
     */
    public void moveRow( int row, boolean left )
    {
       MazeTile[] newRow = new MazeTile[maze[row].length];

        //creating the new row
        for( int i = 0; i < newRow.length; i++ )
        {
            if( left )
            {
                //new tile from the left
                if( i == 0 )
                {
                    newRow[i] = null;
                }
                else
                {
                    newRow[i] = maze[row][i-1];
                }
            }
            else
            {
                //new tile from the right
                if( i == newRow.length - 1 )
                {
                    newRow[i] = null;
                }
                else
                {
                    newRow[i] = maze[row][i+1];
                }
            }

            //copy the new row to the game state
            //for( int i = 0; i < newRow.length; i++ )
            //{
            //    maze[row][i] = newRow[i];
            //}
        }
    }

    /**
     * Column move operation. Assumes the extra tile is in the correct space.
     * @param col the column to be moved
     * @param top whether the extra tile is coming from the top or bottom
     */
    public void moveCol( int col, boolean top )
    {
        MazeTile[] newCol = new MazeTile[maze.length];

        //create the new column
        for( int i = 0; i < newCol.length; i++ )
        {
            if( top )
            {
                //tile coming from the top
                if( i == 0 )
                {
                    newCol[i] = null;
                }
                else
                {
                    newCol[i] = maze[i+1][col];
                }
            }
        }

        //copy the new column to replace the old column
        for( int i = 0; i < newCol.length; i++ )
        {
            maze[i][col] = newCol[i];
        }
    }

    /**
     * returns the x and y coordinate of the extra tile. Both coordinates will be -1 if the extra
     * tile is for some reason not in the maze.
     * @return index 0 will have the x coordinate, index 1 will have the y coordinate
     */
    public int[] findExtraTile()
    {
        int[] coordinates = { -1, -1 };

        //search top row
        for( int i = 0; i < maze[0].length; i++ )
        {
            if( maze[0][i] != null )
            {
                coordinates[0] = 0;
                coordinates[1] = i;
                return coordinates;
            }
        }

        //search left side
        for( int i = 0; i < maze.length; i++ )
        {
            if( maze[i][0] != null )
            {
                coordinates[0] = i;
                coordinates[1] = 0;
                return coordinates;
            }
        }

        //search bottom row
        for( int i = 0; i < maze[maze.length - 1].length; i++ )
        {
            if( maze[maze.length - 1][i] != null )
            {
                coordinates[0] = maze.length - 1;
                coordinates[1] = i;
                return coordinates;
            }
        }

        for ( int i = 0; i < maze.length; i++ )
        {
            if( maze[i][maze.length - 1] != null )
            {
                coordinates[0] = i;
                coordinates[1] = maze.length - 1;
                return coordinates;
            }
        }

        //somehow never found an extra tile
        return coordinates;
    }
}
