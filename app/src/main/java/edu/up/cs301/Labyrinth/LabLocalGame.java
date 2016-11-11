package edu.up.cs301.Labyrinth;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;

/**
 * The LabyrinthLocalGame class for a Labyrinth game.  Defines and enforces
 * the game rules; handles interactions with players.
 * 
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */

public class LabLocalGame extends LocalGame {

	private LabGameState masterGameState;
	private boolean[][] booleanMazeMap = new boolean[9][9];

	/*
	 * Welocome to the lovely loval game constructor
	 */
	public LabLocalGame(){
		masterGameState = new LabGameState();
	}

	/*
	 * canMove(playerIndex:int):boolean - this method takes a player index, which is an int,
	 * and returns a boolean that reflects whether or not it is the
	 * player’s turn whose index was passed to the method
	 */
	protected boolean canMove(int playerIdx) {
		if(masterGameState.getTurnID() == playerIdx){
			return true;
		}
		return false;
	}





	/*
	 * checkGameOver():String - this method checks to see if any player has
	 * won by checking to see if any of the player’s cardsToCollect
	 * hand is empty and if they have returned to their home maze tile.
	 */
	protected String checkIfGameOver() {
		return null;
	}

	/*
	 * makeMove(action:LabGameAction):boolean - this takes a game action
	 * and is based on whether the action is a maze tile insertion or
	 * piece move. It first checks to see if the action is from the player whose turn
	 * it is and then calls either of the helper makeMove methods listed below:
	 */
	protected boolean makeMove(GameAction action) {
		return false;
	}

	/*
	 * makeMazeMove(action:LabMoveMazeAction):boolean - this is one of the
	 * helper methods for makeMove(). This will shift the maze row or column so that the extra maze tile is
	 * in the maze and the tile on the opposing side is pushed out of the maze.
	 */
	private boolean makeMazeMove(LabMoveMazeAction action){
		return false;
	}

	/*
	 * makePlayerPeiceMove(action:LabMovePieceAction):boolean - this is the other helper method for makeMove() this method
	  * will check there is a connected path via a helper method If there is,
	 * it will remove the player from the occupiedBy arrayList of the current
	 * tile and add the player to the occupiedBy ArrayList of the selected tile
	 */
	private boolean makePlayerPieceMove(LabMovePieceAction action){
		return false;
	}

	/*
	 * this is the helper method for makePlayerPeiceMove()
	 */
	private boolean checkPath(MazeTile[][] maze, int xDest, int yDest){
		return false;
	}

	/*
	 * checkTCollect(topCard:LabTCard, currTile:MazeTile):boolean - this takes the
	 * current maze Tile and the top Card to collect of the current player.
	 * It will be called as a helper method at the end of makeMove(). It will
	 * check to see if the current maze tile has the same treasure symbol.
	 */
	private boolean checkTCollect(TCard topCard, MazeTile currTile){
		return false;
	}

	/*
	 * sendUpdatedStateTo(p:GamePlayer):void - this takes the current player whose turn it is and makes deep copy of the maze, the
	 * number of cards to collect for each player, the current player’s top
	 * card to collect treasure. Then it sends all this info to the player.
	 */
	protected void sendUpdatedStateTo(GamePlayer p) {

	}

}
