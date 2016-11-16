package edu.up.cs301.Labyrinth;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mikayla on 11/15/2016.
 */
public class MazeTileTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getType() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        assertTrue(testTile.getType() == 'S');
    }

    @Test
    public void getPathMap() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        boolean[] array = testTile.getPathMap();
        boolean[] correct = new boolean[4];
        correct[0]=false;
        correct[1]=true;
        correct[2]=false;
        correct[3]=true;
        boolean flag = false;
        for(int i=0; i<array.length; i++){
            flag = (correct[i] == array[i]);
        }
        assert (flag);
    }

    @Test
    public void getTreasureSymbol() throws Exception {

    }

    @Test
    public void getOccupiedBy() throws Exception {

    }

    @Test
    public void addPlayer() throws Exception {

    }

    @Test
    public void removePlayer() throws Exception {

    }

    @Test
    public void rotate() throws Exception {

    }

}