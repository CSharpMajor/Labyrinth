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
        MazeTile ourMaze[][] = new MazeTile[9][9];

        for( int r = 0; r < ourMaze.length; r++)
        {
            for( int c = 0; c < ourMaze[r].length; c++)
            {
                ourMaze[r][c] = new MazeTile( 'S', LabTSymbol.COFFEE_MUG );
            }
        }

        state.setMaze(ourMaze);
        MazeTile[][] stateMaze = state.getMaze();

        for( int r = 0; r < stateMaze.length; r++ )
        {
            for( int c = 0; c < stateMaze[r].length; c++ )
            {
                assertTrue( ourMaze[r][c].getTreasureSymbol().
                            getName().equals( stateMaze[r][c].getTreasureSymbol().
                            getName() ) );
                assertTrue( ourMaze[r][c].getType() == stateMaze[r][c].getType() );
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

        int[] extraTileCoor = testState.findExtraTile();

        if( )

        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                assertTrue(testState.getMaze()[r][c].getTreasureSymbol().
                        getName().equals( preSetMaze[r][c].getTreasureSymbol().getName()));
                //assertTrue(testState.getMaze()[r][c].getType() ==
                        //preSetMaze[r][c].getType());
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