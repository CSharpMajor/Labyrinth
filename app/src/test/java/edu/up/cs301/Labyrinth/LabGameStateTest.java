package edu.up.cs301.Labyrinth;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by kuhar19 on 11/9/2016.
 */
public class LabGameStateTest {

    @Test
    public void testGetMaze() throws Exception {
        LabGameState state = new LabGameState();
        MazeTile ourMaze[][] = { { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}, { new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0), new MazeTile(0),
                new MazeTile(0)}};

        state.setMaze(ourMaze);
        MazeTile[][] stateMaze = state.getMaze();

        for( int r = 0; r < 9; r++ )
        {
            for( int c = 0; c < 9; c++ )
            {
                assertTrue( ourMaze[r][c].val == 0 );
                assertTrue( ourMaze[r][c].val == stateMaze[r][c].val);
            }
        }
    }

    @Test
    public void testGetTurnID() throws Exception {

    }

    @Test
    public void testGetPlayerHand() throws Exception {

    }



    @Test
    public void testGetPlayerCollected() throws Exception {
        LabGameState testState = new LabGameState();
        ArrayList<TCard> handToTest = testState.getPlayerHand(0);
        assertTrue(handToTest.size() == 4);
        assertTrue(handToTest.get(0).num == 0);
        assertTrue(handToTest.get(3).num == 3);
    }

    @Test
    public void testSetMaze() throws Exception {

    }

    @Test
    public void testCollectTCard() throws Exception {
        LabGameState testState = new LabGameState();
        ArrayList<TCard> handtoTest = testState.getPlayerHand(0);
        testState.collectTCard(0);
        ArrayList<TCard> afterHand = testState.getPlayerHand(0);
        assertTrue(handtoTest.size() == afterHand.size());
        assertTrue(handtoTest.get(0).num == 1);

    }

    @Test
    public void testGetPlayerCurTile() throws Exception {

    }
}