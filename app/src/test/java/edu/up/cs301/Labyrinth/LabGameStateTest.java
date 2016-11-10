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
        LabGameState state = new LabGameState();

        assertTrue( state.getTurnID() == 0 );
    }

    @Test
    public void testGetPlayerHand() throws Exception {
        LabGameState state = new LabGameState();
        ArrayList<TCard> cards = state.getPlayerHand(0);

        for(int i = 0; i < 4; i++) {
            assertTrue( cards.get(i).num == i );
        }
    }



    @Test
    public void testGetPlayerCollected() throws Exception {
        LabGameState testState = new LabGameState();

    }

    @Test
    public void testSetMaze() throws Exception {

    }

    @Test
    public void testCollectTCard() throws Exception {

    }

    @Test
    public void testGetPlayerCurTile() throws Exception {

    }
}