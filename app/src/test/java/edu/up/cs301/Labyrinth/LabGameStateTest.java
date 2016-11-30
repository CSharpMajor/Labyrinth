package edu.up.cs301.Labyrinth;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Testing the LabGameState class
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */
public class LabGameStateTest {

    @Test
    public void testGetMaze() throws Exception {
        LabGameState state = new LabGameState();
        MazeTile[][] stateMaze = state.getMaze();

        //goes through all the maze tiles (not the buffer) and sees that they have a maze tile in
        //them
        for( int r = 1; r < stateMaze.length-1; r++ )
        {
            for( int c = 1; c < stateMaze[r].length-1; c++ )
            {
                assertTrue( stateMaze[r][c] != null );
            }
        }
    }

    @Test
    public void testGetTurnID() throws Exception {
        LabGameState state = new LabGameState();

        assertTrue( state.getTurnID() == 0 );
    }

    @Test
    public void testGetPlayerHand() throws Exception {
        LabGameState state = new LabGameState();
        ArrayList<TCard> cards = state.getPlayerHand(0);

        assertTrue( cards.size() > 0 );

        for(int i = 0; i < 4; i++) {
            assertTrue( cards.get(i).getTreasure() != null );
        }

    }

    @Test
    public void testGetPlayerCollected() throws Exception {
        LabGameState testState = new LabGameState();

        //make sure there is a card collected
        testState.collectTCard(0);

        ArrayList<TCard> handToTest = testState.getPlayerCollected(0);

        //test that it has a card now
        assertTrue( handToTest != null );

    }

    @Test
    public void testMoveRow() throws Exception {
        LabGameState testState = new LabGameState();
        MazeTile[][] stateMaze = testState.getMaze();

        MazeTile[][] targetMaze = new MazeTile[9][9];
        for( int r = 0; r < 9; r ++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                targetMaze[r][c] = stateMaze[r][c];
            }
        }

        //finds the extra tile and moves it to (0,2)
        testState.moveExtraTile(2, 0);

        //move the row and then move it back to original
        testState.moveRow(2, true);

        //test that extra tile is on other end
        assertTrue( stateMaze[2][8] != null );

        //move the row back
        testState.moveRow(2, false);

        stateMaze = testState.getMaze();

        //goes through all the maze tiles (not the buffer)
        for( int r = 1; r < 8; r++ )
        {
            for( int c = 1; c < 8; c++ )
            {
                assertTrue( stateMaze[r][c] != null );
                assertTrue( targetMaze[r][c] != null );
                assertTrue(stateMaze[r][c].getTreasureSymbol() == targetMaze[r][c].getTreasureSymbol());
                assertTrue(stateMaze[r][c].getType() == targetMaze[r][c].getType());
            }
        }
    }

    @Test
    public void testMoveCol() throws Exception
    {
        LabGameState testState = new LabGameState();
        MazeTile[][] stateMaze = testState.getMaze();

        MazeTile[][] targetMaze = new MazeTile[9][9];
        for( int r = 0; r < 9; r ++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                targetMaze[r][c] = stateMaze[r][c];
            }
        }

        //find the extra tile
        int[] coordinates = testState.findExtraTile();
        MazeTile extraTile = stateMaze[coordinates[0]][coordinates[1]];

        //move extraTile 2nd row left side for state maze
        stateMaze[0][2] = extraTile;
        stateMaze[coordinates[0]][coordinates[1]] = null;

        //move the col and then move it back to original
        testState.moveCol(2, true);

        //test that tile got on other side
        assertTrue( stateMaze[8][2] != null );

        //move the column back
        testState.moveCol(2, false);

        stateMaze = testState.getMaze();

        //goes through all the maze tiles (not the buffer)
        for( int r = 1; r < 8; r++ )
        {
            for( int c = 1; c < 8; c++ )
            {
                assertTrue( stateMaze[r][c] != null );
                assertTrue( targetMaze[r][c] != null );
                assertTrue(stateMaze[r][c].getTreasureSymbol() == targetMaze[r][c].getTreasureSymbol());
                assertTrue(stateMaze[r][c].getType() == targetMaze[r][c].getType());
            }
        }
    }

    @Test
    public void testCollectTCard() throws Exception {
        LabGameState testState = new LabGameState();
        int handSizeToTest = testState.getPlayerHand(0).size();
        ArrayList<TCard> handtoTest = testState.getPlayerHand(0);
        testState.collectTCard(0);
        assertTrue(handtoTest.size() == handSizeToTest-1);
    }

    @Test
    public void testGetPlayerCurTile() throws Exception {
        LabGameState testState = new LabGameState();

        MazeTile[][] compareMaze = testState.getMaze();
        compareMaze[2][2].occupiedBy.add(1);

        int[] coordsFound = new int[2];

        //goes through the maze (excludes buffer and extra tile)
        for(int i = 1; i < compareMaze.length - 1; i++)
        {
            for(int j = 1; j < compareMaze[i].length - 1; j++)
            {
                if(compareMaze[i][j].occupiedBy.contains(1)){
                    coordsFound[0] = i;
                    coordsFound[1] = j;
                }
            }
        }

        assertTrue(coordsFound[0] == 2);
        assertTrue(coordsFound[1] == 2);
    }

    @Test
    public void testMoveExtraTile() throws Exception {
        LabGameState testState = new LabGameState();
        int[] coordinates = new int[2];

        //move tile to TRL
        testState.moveExtraTile(0,2);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 0 && coordinates[1] == 2 );

        //move tile to TRM
        testState.moveExtraTile(0,4);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 0 && coordinates[1] == 4 );

        //move tile to TRR
        testState.moveExtraTile(0,6);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 0 && coordinates[1] == 6 );

        //move tile to RCT
        testState.moveExtraTile(2,8);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 2 && coordinates[1] == 8 );

        //move tile to RCM
        testState.moveExtraTile(4,8);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 4 && coordinates[1] == 8 );

        //move tile to RCB
        testState.moveExtraTile(6,8);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 6 && coordinates[1] == 8 );

        //move tile to BRR
        testState.moveExtraTile(8,6);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 8 && coordinates[1] == 6 );

        //move tile to BRM
        testState.moveExtraTile(8,4);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 8 && coordinates[1] == 4 );

        //move tile to BRL
        testState.moveExtraTile(8,2);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 8 && coordinates[1] == 2 );

        //move tile to LCB
        testState.moveExtraTile(6,0);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 6 && coordinates[1] == 0 );

        //move tile to LCM
        testState.moveExtraTile(4,0);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 4 && coordinates[1] == 0 );

        //move tile to LCT
        testState.moveExtraTile(2,0);
        coordinates = testState.findExtraTile();
        assertTrue( coordinates[0] == 2 && coordinates[1] == 0 );
    }

    @Test
    public void setUpMazeTest() throws Exception {
        LabGameState testSate = new LabGameState();
        int count = 0;

        //makes sure no LabTSymbols are repeated
        String sym1, sym2;
        for( MazeTile[] row : testSate.getMaze() )
        {
            for( MazeTile tile : row )
            {
                if( tile != null && tile.getTreasureSymbol() != null )
                {
                    count++;
                }
                for( MazeTile[] r2 : testSate.getMaze() )
                {
                    for( MazeTile t2 : r2 )
                    {
                        if( tile != null && t2 != null && tile != t2 )
                        {
                            if( tile.getTreasureSymbol() != null && t2.getTreasureSymbol() != null )
                            {
                                sym1 = tile.getTreasureSymbol().getName();
                                sym2 = t2.getTreasureSymbol().getName();
                                assertTrue(!sym1.equals(sym2));
                            }
                        }
                    }
                }
            }
        }

        assertTrue( count == 23 );
    }

    public void testCheckPath() throws Exception{
        LabGameState testState = new LabGameState();

    }


}