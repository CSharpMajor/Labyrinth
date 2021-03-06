package edu.up.cs301.Labyrinth;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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
 * @version Dec. 2016, Beta
 */
public class LabGameState extends GameState implements Serializable
{
    //this is the board with a buffer region on either side
    private MazeTile[][] maze = new MazeTile[9][9];

    //the current players id
    int turnID;

    //all the hands of all the player's cards to collect
    private ArrayList<ArrayList<TCard>> cardsToCollect = new ArrayList<ArrayList<TCard>>();

    //all the hands of all the player's cards they have collected
    private ArrayList<ArrayList<TCard>> cardsCollected = new ArrayList<ArrayList<TCard>>();

    //boolean to ensure the player has moved the maze first
    private boolean hasMovedMaze;

    //an array of all the tiles in the game to be used only when initializing the game
    //first four are the four corner tiles
    private static ArrayList<MazeTile> allTiles = new ArrayList<MazeTile>(50);

    private boolean[][] booleanMazeMap = new boolean[9][9];

    private String winMessage = null;

    /*
    Welcome to the constructor this is where we create the game
     */
    public LabGameState(){
        //sets turn to player 0
        turnID = 0;

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
            ArrayList<TCard> hand = new ArrayList<TCard>();
            cardsToCollect.add(hand);
        }

        cardsCollected = new ArrayList<ArrayList<TCard>>(4);

        for( int i = 0; i < 4; i++ )
        {
             cardsCollected.add(new ArrayList<TCard>() );
        }
        //filling the arrayList of tiles
        getTiles();

        //fill top row with null
        for( int i = 0; i < 9; i++ )
        {
            maze[0][i] = null;
        }

        //fill left col with null
        for( int i = 0; i < 9; i++ )
        {
            maze[i][0] = null;
        }

        //fill right col with null
        for( int i = 0; i < 9; i++ )
        {
            maze[i][8] = null;
        }

        //fill bottom row with null
        for( int i = 0; i < 9; i++ )
        {
            maze[8][i] = null;
        }

        int count = 4;
        String str;

        //fill corner spots with L tiles
        maze[1][1] = allTiles.get(0);
        maze[1][1].rotate(3);
        allTiles.remove(0);
        maze[1][7] = allTiles.get(0);
        maze[1][7].rotate(2);
        allTiles.remove(0);
        maze[7][1] = allTiles.get(0);
        maze[7][1].rotate(0);
        allTiles.remove(0);
        maze[7][7] = allTiles.get(0);
        maze[7][7].rotate(1);
        allTiles.remove(0);

        Random rand = new Random();
        int ind;
        //fill rest of maze with tiles
        for( int r = 1; r < 8; r++ )
        {
            for( int c = 1; c < 8; c++ )
            {
                if( maze[r][c] == null )
                {
                    ind = rand.nextInt(allTiles.size());
                    maze[r][c] = allTiles.get(ind);
                    maze[r][c].rotate(rand.nextInt(4));
                    allTiles.remove(ind);
                }
            }
        }

        //place extra tile
        placeExtraTile( rand.nextInt(12), allTiles.get(0) );

        //Place players at their home squares
        /**
         * Player index numbers with their corresponding array index
         * Player index 0: Red Player
         * Player index 1: Green Player
         * Player index 2: Blue Player
         * Player index 3: Yellow Player
         */
        maze[1][1].addPlayer(0);
        maze[1][7].addPlayer(2);
        maze[7][1].addPlayer(1);
        maze[7][7].addPlayer(3);

        //Set the moved maze variable to false
        hasMovedMaze = false;
    }

    public LabGameState( LabGameState copy ){
        turnID = copy.getTurnID();
        hasMovedMaze = copy.hasMovedMaze;

        cardsToCollect = new ArrayList<ArrayList<TCard>>(0);

        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(4);
            for(int j = 0; j < copy.getPlayerHand(i).size(); j++) {
                LabTSymbol sym = copy.getPlayerHand(i).get(j).getTreasure();
                TCard temp = new TCard(sym);
                hand.add(temp);
            }
            cardsToCollect.add(hand);
        }

        cardsCollected = new ArrayList<ArrayList<TCard>>(4);

        for(int i=0; i<4; i++){
            ArrayList<TCard> hand = new ArrayList<TCard>(4);
            for(int j = 0; j < copy.getPlayerCollected(i).size(); j++) {
                if(copy.getPlayerCollected(i) == null || copy.getPlayerCollected(i).size() == 0){ continue; }
                LabTSymbol sym = copy.getPlayerCollected(i).get(j).getTreasure();
                TCard temp = new TCard(sym);
                hand.add(temp);
            }
            cardsCollected.add(hand);
        }

        //deeeeeeeeeeeeeep copy of maze
        MazeTile[][] cp = copy.getMaze();
        for( int r = 0; r < maze.length; r++ )
        {
            for( int c = 0; c < maze[r].length; c++ )
            {
                if( r > 0 && c > 0 && r < 8 && c < 8 )
                {
                    maze[r][c] = new MazeTile( cp[r][c] );
                }
            }
        }

        //placing extra tile
        int[] coordinates = copy.findExtraTile();
        Log.i("Extra Tile", coordinates[0]+","+coordinates[1]);
        maze[coordinates[0]][coordinates[1]] = new MazeTile(cp[coordinates[0]][coordinates[1]]);



    }

    /**
     * fills the Arraylist of tiles for purposes of filling the maze with tiles randomly
     */
    private static void getTiles()
    {
        //13 blank L shaped tiles
        for( int i = 0; i < 13; i++ )
        {
            allTiles.add(new MazeTile('L', LabTSymbol.EMPTY ));
        }

        //13 blank S shaped tiles
        for( int i = 0; i < 13; i++ )
        {
            allTiles.add(new MazeTile('S', LabTSymbol.EMPTY ));
        }

        //T-shaped treasure tiles
        allTiles.add(new MazeTile('T', LabTSymbol.DRAGON ));
        allTiles.add(new MazeTile('T', LabTSymbol.GHOST ));
        allTiles.add(new MazeTile('T', LabTSymbol.TROLL ));
        allTiles.add(new MazeTile('T', LabTSymbol.CANDELABRA ));
        allTiles.add(new MazeTile('T', LabTSymbol.FLAMING_SWORD ));
        allTiles.add(new MazeTile('T', LabTSymbol.HELMET ));
        allTiles.add(new MazeTile('T', LabTSymbol.CHEST ));
        allTiles.add(new MazeTile('T', LabTSymbol.KEYS ));
        allTiles.add(new MazeTile('T', LabTSymbol.CROWN ));
        allTiles.add(new MazeTile('T', LabTSymbol.BOOK ));
        allTiles.add(new MazeTile('T', LabTSymbol.GEM ));
        allTiles.add(new MazeTile('T', LabTSymbol.BAG_OF_GOLD ));
        allTiles.add(new MazeTile('T', LabTSymbol.RING ));
        allTiles.add(new MazeTile('T', LabTSymbol.SKULL ));
        allTiles.add(new MazeTile('T', LabTSymbol.MAP ));
        allTiles.add(new MazeTile('T', LabTSymbol.SPIDER ));
        allTiles.add(new MazeTile('T', LabTSymbol.MOUSE ));

        //L shaped treasure tiles
        allTiles.add(new MazeTile('L', LabTSymbol.OWL ));
        allTiles.add(new MazeTile('L', LabTSymbol.SCARAB ));
        allTiles.add(new MazeTile('L', LabTSymbol.JIGGLYPUFF ));
        allTiles.add(new MazeTile('L', LabTSymbol.MOTH ));
        allTiles.add(new MazeTile('L', LabTSymbol.ASTRONAUT ));
        allTiles.add(new MazeTile('L', LabTSymbol.TREBLE_CLEF ));
        allTiles.add(new MazeTile('L', LabTSymbol.COFFEE_MUG ));

    }

    /**
     * moves the extra tile to where it needs to be
     * @param x the new location for the extra tile
     * @param y
     */
    public void moveExtraTile( int x, int y )
    {
        if( x < 0 || x > 8 || y < 0 || y > 8 )
        {
            return;
        }

        //get the extra maze tile
        int[] coordinates = findExtraTile();
        MazeTile extra = maze[coordinates[0]][coordinates[1]];

        //make old spot null
        maze[coordinates[0]][coordinates[1]] = null;

        //put tile in new spot
        maze[x][y] = extra;
    }

    /**
     * will take a number from 0-11 that determines the placement of the extra tile
     * @param i a value from 0-11
     * @param extra the extra tile to be placed
     */
    private void placeExtraTile( int i, MazeTile extra)
    {
        switch ( i )
        {
            //Top of Board: Left
            case 0: maze[0][2] = extra;
                break;
            //Top of Board: Middle
            case 1: maze[0][4] = extra;
                break;
            //Top of Board: Right
            case 2: maze[0][6] = extra;
                break;
            //Right of Board: Top
            case 3: maze[2][8] = extra;
                break;
            //Right of Board: Middle
            case 4: maze[4][8] = extra;
                break;
            //Right of Board: Bottom
            case 5: maze[6][8] = extra;
                break;
            //Bottom of Board: Right
            case 6: maze[8][6] = extra;
                break;
            //Bottom of Board: Middle
            case 7: maze[8][4] = extra;
                break;
            //Bottom of Board: Left
            case 8: maze[8][2] = extra;
                break;
            //Left of Board: Bottom
            case 9: maze[6][0] = extra;
                break;
            //Left of Board: Middle
            case 10: maze[4][0] = extra;
                break;
            //Left of Board: Top
            case 11: maze[2][0] = extra;
                break;
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

    public ArrayList<TCard> getPlayerCollected( int playerIndex ) {
        if(cardsCollected.size() == 0 ){
            return null;
        }
        return cardsCollected.get(playerIndex);
    }

    /**
     * When a player collects a card it will move the card from cardsToCollect to cardsCollected
     *
     * @param playerIndex the player who has collected the card
     */
    public void collectTCard( int playerIndex ){
        TCard move = cardsToCollect.get(playerIndex).get(0);

        cardsCollected.get(playerIndex).add(move);
        ArrayList<TCard> hand = cardsToCollect.get(playerIndex);
        hand.remove(0);
    }

    /**
     * for when we move the player on the maze
     * @param newMaze The new maze reflecting the new player position
     */
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

    public int[] getPlayerCurTile( int playerIndex )
    {
        int[] coords = new int[2];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[i].length; j++)
            {
                if(maze[i][j] == null){ continue; }
                if(maze[i][j].occupiedBy.contains(playerIndex))
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
        }
        //copy the new row to the game state
        for( int i = 0; i < newRow.length; i++ )
        {
            maze[row][i] = newRow[i];
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
                    newCol[i] = maze[i-1][col];
                }
            }
            else
            {
                //tile from bottom
                if( i == newCol.length - 1 )
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
        for( int i = 0; i < 9; i++ )
        {
            if( maze[0][i] != null )
            {
                coordinates[0] = 0;
                coordinates[1] = i;
                return coordinates;
            }
        }

        //search left side
        for( int i = 0; i < 9; i++ )
        {
            if( maze[i][0] != null )
            {
                coordinates[0] = i;
                coordinates[1] = 0;
                return coordinates;
            }
        }

        //search bottom row
        for( int i = 0; i < 9; i++ )
        {
            if( maze[8][i] != null )
            {
                coordinates[0] = 8;
                coordinates[1] = i;
                return coordinates;
            }
        }

        //right side
        for ( int i = 0; i < 9; i++ )
        {
            if( maze[i][8] != null )
            {
                coordinates[0] = i;
                coordinates[1] = 8;
                return coordinates;
            }
        }

        //somehow never found an extra tile
        return coordinates;
    }


    /*
	 * this is the helper method for makePlayerPieceMove()
	 */
    public boolean checkPath(int xDest, int yDest) throws ArrayIndexOutOfBoundsException
    {
        Log.i("checkPath", "starting: "+this.getTurnID());

        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                booleanMazeMap[i][j] = false;
                if(maze[i][j] == null){continue;}
                if (maze[i][j].getOccupiedBy().contains((Integer) this.getTurnID()))
                {
                    booleanMazeMap[i][j] = true;
                }
            }
        }
        boolean changeFlag = true;
        while (changeFlag)
        {
            changeFlag = false;
            //Log.i("what", "while loop");
            for (int i = 1; i < maze.length-1; i++)
            {
                for (int j = 1; j < maze[i].length-1; j++)
                {
                    //Log.i("checkPath", maze[i][j].toString());

                    if (booleanMazeMap[i][j])
                    {
                        //////////////////
                        //coners of maze//
                        //////////////////
                        if(i==1 && j==1){
                            //right
                            if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3])
                            {

                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;


                            }
                            //bottom
                            if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0])
                            {
                                if(!booleanMazeMap[i][j + 1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;

                            }
                        }
                        else if(i==1 && j==maze[i].length-2){
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j-1].getPathMap()[2])
                            {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j-1] = true;

                            }
                            //right
                            if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3])
                            {
                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;

                            }
                        }
                        else if(j==1 && i==maze[i].length-2){
                            //bottom
                            if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0])
                            {
                                if(!booleanMazeMap[i][j+1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;

                            }
                            //left
                            if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1])
                            {
                                if(!booleanMazeMap[i - 1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }
                        else if(j==maze[i].length-2 && i==maze[i].length-2){
                            Log.i("check", "got in the bottom right corner");
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j-1].getPathMap()[2])
                            {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j-1] = true;

                            }
                            //left
                            if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1])
                            {
                                if(!booleanMazeMap[i - 1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }

                        //////////////////
                        //edges  of maze//
                        //////////////////

                        else if(i==1){
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j-1].getPathMap()[2])
                            {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j-1] = true;

                            }
                            //right
                            if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3])
                            {
                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;

                            }
                            //bottom
                            if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0])
                            {
                                if(!booleanMazeMap[i][j+1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;

                            }
                        }
                        else if(j==1){
                            //right
                            if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3])
                            {
                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;

                            }
                            //bottom
                            if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0])
                            {
                                if(!booleanMazeMap[i][j+1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;

                            }
                            //left
                            if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1])
                            {
                                if(!booleanMazeMap[i-1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }
                        else if(i==maze[i].length-2){
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j-1].getPathMap()[2])
                            {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j-1] = true;

                            }
                            //bottom
                            else if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0])
                            {
                                if(!booleanMazeMap[i][j+1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;

                            }
                            //left
                            else if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1])
                            {
                                if(!booleanMazeMap[i - 1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }
                        else if(j==maze[i].length-2){
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j-1].getPathMap()[2])
                            {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j-1] = true;

                            }
                            //right
                            else if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3])
                            {
                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;

                            }
                            //left
                            else if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1])
                            {
                                if(!booleanMazeMap[i - 1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }

                        //////////////////
                        //middle of maze//
                        //////////////////
                        else {
                            //top
                            if (maze[i][j].getPathMap()[0] && maze[i][j - 1].getPathMap()[2]) {
                                if(!booleanMazeMap[i][j-1]){ changeFlag = true; }
                                booleanMazeMap[i][j - 1] = true;

                            }
                            //right
                            if (maze[i][j].getPathMap()[1] && maze[i + 1][j].getPathMap()[3]) {
                                if(!booleanMazeMap[i + 1][j]){ changeFlag = true; }
                                booleanMazeMap[i + 1][j] = true;

                            }
                            //bottom
                            if (maze[i][j].getPathMap()[2] && maze[i][j + 1].getPathMap()[0]) {
                                if(!booleanMazeMap[i][j + 1]){ changeFlag = true; }
                                booleanMazeMap[i][j + 1] = true;
                            }
                            //left
                            if (maze[i][j].getPathMap()[3] && maze[i - 1][j].getPathMap()[1]) {
                                if(!booleanMazeMap[i - 1][j]){ changeFlag = true; }
                                booleanMazeMap[i - 1][j] = true;

                            }
                        }
                    }
                }
            }

        }
        return booleanMazeMap[xDest][yDest];
    }


    public void setWinMessage(String s){
        winMessage = s;
    }

    public String getWinMessage(){
        return winMessage;
    }
}

