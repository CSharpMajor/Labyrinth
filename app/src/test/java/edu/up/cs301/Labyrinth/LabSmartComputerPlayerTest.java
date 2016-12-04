package edu.up.cs301.Labyrinth;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chloekuhar on 11/30/16.
 */
public class LabSmartComputerPlayerTest
{

    @Test
    public void testWhereTileGo() throws Exception
    {
        LabGameState state = new LabGameState();
        LabSmartComputerPlayer player = new LabSmartComputerPlayer("muffin");

        //test that we never get < 0 or > 8 as a value
        assertTrue(player.whereTileGo(state)[0] >= 0 && player.whereTileGo(state)[0] <= 8);

        //test that the coordinates are in the buffer and not the maze
        assertTrue( player.whereTileGo(state)[0] < 1 || player.whereTileGo(state)[0] > 7
                || player.whereTileGo(state)[1] < 1 || player.whereTileGo(state)[1] > 7);
    }

    @Test
    public void testGetPosMatches() throws Exception
    {
        LabSmartComputerPlayer player = new LabSmartComputerPlayer("muffin");

        //test all tile types
        assertTrue( player.getPosMatches('L') == 2 );
        assertTrue( player.getPosMatches('S') == 2 );
        assertTrue( player.getPosMatches('T') == 3 );
    }

    @Test
    public void testGetMatches() throws Exception
    {
        //4 tiles all type 'L'
        MazeTile extra = new MazeTile('L', null);
        MazeTile right = new MazeTile( extra );
        MazeTile bottom = new MazeTile( extra );
        MazeTile left = new MazeTile( extra );

        LabSmartComputerPlayer player = new LabSmartComputerPlayer("muffin");

        //should be no matches
        assertTrue( player.getMatches(extra, left, right, bottom) == 0 );

        //4 tiles all type 'T'
        extra = new MazeTile( 'T', null );
        right = new MazeTile( extra );
        bottom = new MazeTile( extra );
        left = new MazeTile( extra );

        //should be 2 matches
        assertTrue( player.getMatches(extra, left, right, bottom) == 2 );
    }

    @Test
    public void testFindTreasure() throws Exception
    {
        LabGameState state = new LabGameState();
        LabSmartComputerPlayer player = new LabSmartComputerPlayer("muffin");

        assertTrue( state.getPlayerHand(0) != null );
        assertTrue( player.findTreasure(state)[0] != -1 );
    }

    @Test
    public void testFindClosest() throws Exception
    {

    }
}