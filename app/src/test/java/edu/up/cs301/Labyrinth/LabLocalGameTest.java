package edu.up.cs301.Labyrinth;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mikayla on 11/10/2016.
 */
public class LabLocalGameTest {

    @Test
    public void canMove() throws Exception {
        LabLocalGame testLocal = new LabLocalGame();
        assertTrue(testLocal.canMove(1) == true);
        //assertTrue(testLocal.canMove(1));
    }

    @Test
    public void checkIfGameOver() throws Exception {
        LabLocalGame testLocal = new LabLocalGame();
        assertTrue(testLocal.checkIfGameOver().equals("The Red Player Has Won"));
    }


    @Test
    public void makeMove() throws Exception {

    }

    @Test
    public void sendUpdatedStateTo() throws Exception {

    }

}