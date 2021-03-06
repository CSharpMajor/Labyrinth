package edu.up.cs301.Labyrinth;

import android.util.Log;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;


/**
 * The LabyrinthLocalGame class for a Labyrinth game. Defines and enforces
 * the game rules; handles interactions with players.
 * 
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Dec. 2016, Beta
 */

public class LabLocalGame extends LocalGame
{
	/**
	 * PSA:
	 * Player 0 is Red
	 * Player 1 is Green
	 * Player 2 is Blue
	 * Player 3 is Yellow
	 */

	private LabGameState masterGameState;

	private int[] previousLOcation = {0,0};

	/*
	 * Welcome to the lovely local game constructor
	 */
	public LabLocalGame()
	{
		masterGameState = new LabGameState();
	}

	/*
	 * canMove(playerIndex:int):boolean - this method takes a player index, which is an int,
	 * and returns a boolean that reflects whether or not it is the
	 * player’s turn whose index was passed to the method
	 */
	protected boolean canMove(int playerIdx)
	{
		return playerIdx == masterGameState.getTurnID();
	}


	/*
	 * checkGameOver():String - this method checks to see if any player has
	 * won by checking to see if any of the player’s cardsToCollect
	 * hand is empty and if they have returned to their home maze tile.
	 */
	protected String checkIfGameOver()
	{
		//top left is green = 0 player Index
		//top right is red = 1 player Index
		//bottom left is blue = 2 player Index
		//bottom right is yellow = 3 player Index
		MazeTile[][] masterMaze = masterGameState.getMaze();
		//red player
		if (masterGameState.getTurnID() == 0)
		{
			//is on home and collected all Tcards
			if (masterMaze[1][1].occupiedBy.contains(0) && masterGameState.getPlayerHand(0).size() == 0)
			{
				return "The Red Player Has Won";
			}
		}
		//green
		else if (masterGameState.getTurnID() == 1)
		{
			if (masterMaze[7][1].occupiedBy.contains(1) && masterGameState.getPlayerHand(1).size() == 0)
			{
				return "The Green Player Has Won";
			}
		}
		//blue
		else if (masterGameState.getTurnID() == 2)
		{
			if (masterMaze[1][7].occupiedBy.contains(2) && masterGameState.getPlayerHand(2).size() == 0)
			{
				return "The Blue Player Has Won";
			}
		}
		//yellow
		else if (masterGameState.getTurnID() == 3)
		{
			if (masterMaze[7][7].occupiedBy.contains(3) && masterGameState.getPlayerHand(3).size() == 0)
			{
				return "The Yellow Player Has Won";
			}
		}
		return null;
	}

	/*
	 * makeMove(action:LabGameAction):boolean - this takes a game action
	 * and is based on whether the action is a maze tile insertion or
	 * piece move. It first checks to see if the action is from the player whose turn
	 * it is and then calls either of the helper makeMove methods listed below:
	 */
	protected boolean makeMove(GameAction action)
	{
		//check that it is their turn
		if (canMove(this.getPlayerIdx(action.getPlayer())))
		{
			//we get a maze move
			if (action instanceof LabMoveMazeAction)
			{
				return makeMazeMove(action);
			}
			//we get a move extra tile action
			else if(action instanceof LabMoveExtraTile){
				if(masterGameState.hasMovedMaze()){ return false; }
				masterGameState.moveExtraTile(((LabMoveExtraTile) action).getCoords()[0], ((LabMoveExtraTile) action).getCoords()[1]);
				sendAllUpdatedState();
				return true;
			}
			//we got a move player piece action
			else if (action instanceof LabMovePieceAction && masterGameState.hasMovedMaze())
			{
				return makePlayerPieceMove(action);
			}
			//we got a rotate the extra tile action
			else if(action instanceof LabRotateExtraTileAction){
				//get the extra tile
				int[] extra = masterGameState.findExtraTile();
				//get a copy of maze to manipulate
				MazeTile[][] newMaze = masterGameState.getMaze();
				//rotate
				newMaze[extra[0]][extra[1]].rotate(1);
				//set master to copy
				masterGameState.setMaze(newMaze);
				sendAllUpdatedState();
				return true;
			}
		}
		return false;
	}

	/*
	 * makeMazeMove(action:LabMoveMazeAction):boolean - this is one of the
	 * helper methods for makeMove(). This will shift the maze row or column so that the extra maze tile is
	 * in the maze and the tile on the opposing side is pushed out of the maze. Assumes that the extra tile
	 * has been moved to the proper space for the movement
	 */
	private boolean makeMazeMove(GameAction action)
	{
		//if the player has already moved the maze they cant move it again
		if(masterGameState.hasMovedMaze()){ return false; }

		if (!(action instanceof LabMoveMazeAction) || ( ! checkExtraTile() ) ) { return false; }

		//finding the coordinates for where the extra tile is
		int[] coordinates = masterGameState.findExtraTile();

		if(coordinates[0] == previousLOcation[0] && coordinates[1] == previousLOcation[1]){
			//sendAction();
			return false;
		}

		//extra tile is in the top row
		if (coordinates[0] == 0)
		{
			masterGameState.moveCol(coordinates[1], true);
			masterGameState.setHasMovedMaze(true);
			checkPlayerWrap();
			return true;
		}
		//extra tile is on the left side
		else if (coordinates[1] == 0)
		{
			masterGameState.moveRow(coordinates[0], true);
			masterGameState.setHasMovedMaze(true);
			previousLOcation = masterGameState.findExtraTile();
			checkPlayerWrap();
			return true;
		}
		//extra tile is on the bottom
		else if (coordinates[0] == masterGameState.getMaze().length - 1)
		{
			masterGameState.moveCol(coordinates[1], false);
			masterGameState.setHasMovedMaze(true);
			previousLOcation = masterGameState.findExtraTile();
			checkPlayerWrap();
			return true;
		}
		//extra tile is on the right side
		else if (coordinates[1] == masterGameState.getMaze().length - 1)
		{
			masterGameState.moveRow(coordinates[0], false);
			masterGameState.setHasMovedMaze(true);
			previousLOcation = masterGameState.findExtraTile();
			//check to see if a player has moved off the board
			checkPlayerWrap();
			return true;
		}
		//check to see if a player has moved off the board
		checkPlayerWrap();
		sendAllUpdatedState();
		return false;
	}


	/*
	 * makePlayerPieceMove(action:LabMovePieceAction):boolean - this is the other helper method for makeMove() this method
	  * will check there is a connected path via a helper method If there is,
	 * it will remove the player from the occupiedBy arrayList of the current
	 * tile and add the player to the occupiedBy ArrayList of the selected tile
	 */
	private boolean makePlayerPieceMove(GameAction action)
	{
		if(!masterGameState.hasMovedMaze() || !masterGameState.hasMovedMaze()){
			return false;
		}
		MazeTile[][] newMaze = masterGameState.getMaze();
		Log.i("makePlayerPeiceMove","starting the method");
		//the coordinates are off the board
		if(((LabMovePieceAction) action).getCoords()[0] <= 0 || ((LabMovePieceAction) action).getCoords()[1] <= 0){
			//Log.i("makePlayerPeiceMove","first if returned false");
			return false; }
		if(((LabMovePieceAction) action).getCoords()[0] >= newMaze.length-1 || ((LabMovePieceAction) action).getCoords()[1] >= newMaze.length-1){
			//Log.i("makePlayerPeiceMove","second if returned false");
			return false; }

		//the player stayed on the same tile do nothing but increment the turn
		if(newMaze[((LabMovePieceAction) action).getCoords()[0]][((LabMovePieceAction) action).getCoords()[1]].getOccupiedBy().contains(((LabMovePieceAction) action).getPlayerNum())){
			Log.i("makePlayerPeiceMove", "choose to stay");
			//set the maze flag for the next turn
			masterGameState.setHasMovedMaze(false);
			//itterate the turn
			if(masterGameState.getTurnID() == players.length-1){
				masterGameState.setTurnID(0);
			}
			else{
				masterGameState.setTurnID(masterGameState.getTurnID()+1);
			}
			return true;
		}
		//check the path and it returns true
		else if(masterGameState.checkPath(((LabMovePieceAction) action).getCoords()[0], ((LabMovePieceAction) action).getCoords()[1])){
			Log.i("movelayePeice", "check path returned true");

			//remove the player from the tile its on
			int[] curTil = masterGameState.getPlayerCurTile(masterGameState.getTurnID());
			newMaze[curTil[0]][curTil[1]].removePlayer(masterGameState.getTurnID());

			//add the player to its desired destination
			newMaze[((LabMovePieceAction) action).getCoords()[0]][((LabMovePieceAction) action).getCoords()[1]].addPlayer(((LabMovePieceAction) action).getPlayerNum());
			//change the maze in the game state
			masterGameState.setMaze(newMaze);
			//set the maze flag back to false for the new turn
			masterGameState.setHasMovedMaze(false);
			if(masterGameState.getPlayerHand(masterGameState.getTurnID()).size() == 0)
			{
				masterGameState.setWinMessage(checkIfGameOver());
				checkIfGameOver();
			}
			else{
				checkTCollect(masterGameState.getPlayerHand(masterGameState.getTurnID()).get(0), newMaze[((LabMovePieceAction) action).getCoords()[0]][((LabMovePieceAction) action).getCoords()[1]]);
			}
			//iterate the turns
			if(masterGameState.getTurnID() == players.length-1){
				masterGameState.setTurnID(0);
			}
			else{
				masterGameState.setTurnID(masterGameState.getTurnID()+1);
			}

			Log.i("makePlayerPeiceMove", "returning ture");
			masterGameState.setHasMovedMaze(false);
			return true;
		}
		//the check path method has returned false
		else{
			Log.i("movelayePeice", "check path returned false");
			masterGameState.setHasMovedMaze(false);
			//iterate the turns
			if(masterGameState.getTurnID() == players.length-1){
				masterGameState.setTurnID(0);
			}
			else{
				masterGameState.setTurnID(masterGameState.getTurnID()+1);
			}
		}
		Log.i("makePlayerPeiceMove", "ending method");
		sendAllUpdatedState();
		return false;
	}


	/*
	 * checkTCollect(topCard:LabTCard, currTile:MazeTile):boolean - this takes the
	 * current maze Tile and the top Card to collect of the current player.
	 * It will be called as a helper method at the end of makeMove(). It will
	 * check to see if the current maze tile has the same treasure symbol.
	 */
	private boolean checkTCollect(TCard topCard, MazeTile currTile)
	{
		if(currTile.getTreasureSymbol() == null){return false;}
		if (currTile.getTreasureSymbol().getName().equals(topCard.getTreasure().getName()))
		{
			masterGameState.collectTCard(masterGameState.getTurnID());
			return true;
		}
		return false;
	}

	/*
	 * sendUpdatedStateTo(p:GamePlayer):void - this takes the current player whose turn it is and makes deep copy of the maze, the
	 * number of cards to collect for each player, the current player’s top
	 * card to collect treasure. Then it sends all this info to the player.
	 */
	protected void sendUpdatedStateTo(GamePlayer p)
	{
		Log.i("LabLocalGame", p.toString());
		LabGameState copy = new LabGameState(masterGameState);
		p.sendInfo(copy);
	}

	/**
	 * checks that extra tile is in valid location
	 * @return true is extra tile is in valid location
     */
	private boolean checkExtraTile()
	{
		//find the extra tile
		int[] extraTileCoor = masterGameState.findExtraTile();
		//check that the coordinates are correct ie even
		if (extraTileCoor[0] % 2 != 0 && extraTileCoor[1] % 2 != 0)
		{
			return false;
		}
		else if (extraTileCoor[0] == 0 && extraTileCoor[1] == 0)
		{
			return false;
		}
		else if (extraTileCoor[0] == 0 && extraTileCoor[1] == 8)
		{
			return false;
		}
		else if (extraTileCoor[0] == 8 && extraTileCoor[1] == 0)
		{
			return false;
		}
		else if (extraTileCoor[0] == 8 && extraTileCoor[1] == 8)
		{
			return false;
		}
		return true;
	}


	private boolean checkPlayerWrap(){
		MazeTile[][] newMaze = masterGameState.getMaze();
		//check each player
		for(int i=0; i<playerNames.length; i++){
			int[] coords = masterGameState.getPlayerCurTile(i);
			//Log.i("checkPlayerWrap", ""+coords[0]+" , "+coords[1]);
			//check each side of the buffer for a tile containing a player and move the player to other side of board
			if(coords[0] == 0){
				newMaze[coords[0]][coords[1]].removePlayer(i);
				newMaze[newMaze.length-2][coords[1]].addPlayer(i);
			}
			if(coords[0] == masterGameState.getMaze().length-1){
				newMaze[coords[0]][coords[1]].removePlayer(i);
				newMaze[1][coords[1]].addPlayer(i);
			}
			if(coords[1] == 0){
				newMaze[coords[0]][coords[1]].removePlayer(i);
				newMaze[coords[0]][newMaze.length-2].addPlayer(i);
			}
			if(coords[1] == masterGameState.getMaze().length-1){
				newMaze[coords[0]][coords[1]].removePlayer(i);
				newMaze[coords[0]][1].addPlayer(i);
			}
		}

		return true;
	}
}
