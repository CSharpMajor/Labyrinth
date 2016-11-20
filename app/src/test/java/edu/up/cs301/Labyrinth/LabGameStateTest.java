package edu.up.cs301.Labyrinth;

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

        //goes through all the maze tiles (not the buffer
        for( int r = 1; r < stateMaze.length-1; r++ )
        {
            for( int c = 1; c < stateMaze[r].length-1; c++ )
            {
                assertTrue( );
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
        //LabGameState state = new LabGameState();
        //ArrayList<TCard> cards = state.getPlayerHand(0);

        for(int i = 0; i < 4; i++) {
            //assertTrue( cards.get(i).num == i );
        }
        //for(int i = 0; i < 4; i++) {
        //    assertTrue( cards.get(i).num == i );
        //}
    }



    @Test
    public void testGetPlayerCollected() throws Exception {
        //LabGameState testState = new LabGameState();
        //ArrayList<TCard> handToTest = testState.getPlayerHand(0);
        //assertTrue(handToTest.size() == 4);
        //assertTrue(handToTest.get(0).num == 0);
        //assertTrue(handToTest.get(3).num == 3);
        LabGameState testState = new LabGameState();
        ArrayList<TCard> handToTest = testState.getPlayerHand(0);
        assertTrue(handToTest.size() == 4);
        //assertTrue(handToTest.get(0).num == 0);
        //assertTrue(handToTest.get(3).num == 3);
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

        //find the extra tile
        int[] coordinates = testState.findExtraTile();
        MazeTile extraTile = stateMaze[coordinates[0]][coordinates[1]];

        //move extraTile 2nd row left side for state maze
        stateMaze[2][0] = extraTile;
        stateMaze[coordinates[0]][coordinates[1]] = null;

        //move the row and then move it back to original
        testState.moveRow(2, true);
        testState.moveRow(2, false);

        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                assertTrue(testState.getMaze()[r][c].getTreasureSymbol().
                        getName().equals( targetMaze[r][c].getTreasureSymbol().getName()));
                assertTrue(testState.getMaze()[r][c].getType() ==
                        targetMaze[r][c].getType());
            }
        }
    }

    @Test
    public void testCollectTCard() throws Exception {
//        LabGameState testState = new LabGameState();
//        ArrayList<TCard> handtoTest = testState.getPlayerHand(0);
//        testState.collectTCard(0);
//        ArrayList<TCard> afterHand = testState.getPlayerHand(0);
//        assertTrue(handtoTest.size() == afterHand.size());
//        assertTrue(handtoTest.get(0).num == 1);
        LabGameState testState = new LabGameState();
        ArrayList<TCard> handtoTest = testState.getPlayerHand(0);
        testState.collectTCard(0);
        ArrayList<TCard> afterHand = testState.getPlayerHand(0);
        assertTrue(handtoTest.size() == afterHand.size());
        //assertTrue(handtoTest.get(0).num == 1);

    }

    @Test
    public void testGetPlayerCurTile() throws Exception {
        LabGameState testState = new LabGameState();
        MazeTile[][] testMaze = testState.getMaze();

        testMaze[2][2].occupiedBy.add(1);

        testState.setMaze(testMaze);

        MazeTile[][] compareMaze = testState.getMaze();
        int[] coordsFound = new int[2];

        for(int i = 0; i < compareMaze.length; i++)
        {
            for(int j = 0; j < compareMaze[i].length; j++)
            {
                if(compareMaze[i][j].occupiedBy.contains((Integer) 1)){
                    coordsFound[0] = i;
                    coordsFound[1] = j;
                }
            }
        }

        assertTrue(coordsFound[0] == 2);
        assertTrue(coordsFound[1] == 2);
    }
}