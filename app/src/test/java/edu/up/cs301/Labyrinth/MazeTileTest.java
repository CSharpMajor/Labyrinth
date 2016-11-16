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
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        assert (testTile.getTreasureSymbol().getName().equals("dragon"));
    }

    @Test
    public void getOccupiedBy() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);

    }

    @Test
    public void addPlayer() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        testTile.addPlayer(1);
        assert (testTile.getOccupiedBy().size() == 1);
        assert (testTile.getOccupiedBy().contains(1));
    }

    @Test
    public void removePlayer() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        testTile.addPlayer(1);
        assert (testTile.getOccupiedBy().size() == 1);
        assert (testTile.getOccupiedBy().contains(1));
        testTile.removePlayer(1);
        assert (testTile.getOccupiedBy().size() == 0);
        assert (!testTile.getOccupiedBy().contains(1));
        testTile.removePlayer(1);
        assert (testTile.getOccupiedBy().size() == 0);
        assert (!testTile.getOccupiedBy().contains(1));
    }

    @Test
    public void rotate() throws Exception {
        MazeTile testTile = new MazeTile('S', LabTSymbol.DRAGON);
        testTile.rotate(1);
        boolean[] array = testTile.getPathMap();
        boolean[] correct = new boolean[4];
        correct[0]=true;
        correct[1]=false;
        correct[2]=true;
        correct[3]=false;
        boolean flag = false;
        for(int i=0; i<array.length; i++){
            flag = (correct[i] == array[i]);
        }
        assert (flag);

        testTile = new MazeTile('S', LabTSymbol.DRAGON);
        testTile.rotate(2);
        array = testTile.getPathMap();
        correct = new boolean[4];
        correct[0]=false;
        correct[1]=true;
        correct[2]=false;
        correct[3]=true;
        flag = false;
        for(int i=0; i<array.length; i++){
            flag = (correct[i] == array[i]);
        }
        assert (flag);

    }

}