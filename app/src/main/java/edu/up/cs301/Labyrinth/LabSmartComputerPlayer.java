package edu.up.cs301.Labyrinth;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameState;

import android.graphics.Point;
import android.util.Log;

import java.io.Serializable;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * A computerized Labyrinth player who tries to add the extra tile where it will create the most
 * connections and then move to its current treasure goal. If it cannot it will move as close as
 * possible.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, Alpha
 */
public class LabSmartComputerPlayer extends GameComputerPlayer implements Serializable
{
	/**
	 * constructor for a computer player
	 *
	 * @param name
	 * 		the player's name
	 */
	public LabSmartComputerPlayer (String name) {
		// invoke superclass constructor
		super(name);
	}

	protected void receiveInfo(GameInfo info) {
		//Coordinates for the player to send to the local game
		int xCordMaze;
		int yCordMaze;
		int xCordPiece;
		int yCordPiece;
		Random rand = new Random();

		if (info instanceof LabGameState) {
			LabGameState myGameState = (LabGameState) info;
			if (this.playerNum != myGameState.getTurnID()) {
				return;
			}
			else {
				if (myGameState.hasMovedMaze()) {
					//moving the player piece

					//check to see if treasure or player home( if all tcards have been collected )
					// is reachable
					int[] tCoords = findTreasure( myGameState );
					if( tCoords[0] != -1 && myGameState.checkPath(tCoords[0], tCoords[1]) )
					{
						xCordPiece = tCoords[0];
						yCordPiece = tCoords[1];
					}
					//if not reachable, move as close as possible
					else
					{
						int[] closestCoord = findClosest( myGameState, tCoords );
						xCordPiece = closestCoord[0];
						yCordPiece = closestCoord[1];
					}

					//Delay to make it look like the player is thinking
					sleep(3000);
					//Then send the move piece action to the local game
					game.sendAction(new LabMovePieceAction(this, xCordPiece, yCordPiece, this.playerNum));
				}
				else {
					//place the extra tile

					//find where to place
					int[] coords = whereTileGo( myGameState );
					xCordMaze = coords[0];
					yCordMaze = coords[1];

					//Delay to make it look like the player is thinking
					sleep(2000);
					//Then send the move maze action to the local game
					Log.i("Sent Action", "move maze");
					game.sendAction(new LabMoveExtraTile(this, xCordMaze, yCordMaze));
					game.sendAction(new LabMoveMazeAction(this, xCordMaze, yCordMaze));
				}
			}
		}
	}

	/**
	 * determines where the extra tile should go. Chooses based on if they can get all the "true"
	 * sides of the tile to match when it is placed in the maze.
	 * @return x coordinate in the 0 index, y coordinate in the 1 index
     */
	protected int[] whereTileGo( LabGameState gameState )
	{
		int[] prevCoord = gameState.findExtraTile();
		MazeTile extraTile = gameState.getMaze()[prevCoord[0]][prevCoord[1]];
		MazeTile left, right, bottom;
		int[] bestMatch = {-1, -1};
		int numPosMatches = getPosMatches(extraTile.getType());
		int matchCount; //if equal to numPosMatches this is a good match and we'll return
		int bestmatchCount = 0; //if we haven't equaled numPosMatches yet

		//goes through maze edges and sees where it can put the tile
		//best move is the one where all trues touch other trues (does not consider rotating the
		//extra tile)

		// top of board
		if( prevCoord[0] != 0 || prevCoord[1] != 2)
		{
			left = gameState.getMaze()[1][1]; //left to the extra tile if inserted
			right = gameState.getMaze()[1][3]; //to the right
			bottom = gameState.getMaze()[1][2]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 0;
				bestMatch[1] = 2;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 0 || prevCoord[1] != 4 )
		{
			left = gameState.getMaze()[1][3]; //left to the extra tile if inserted
			right = gameState.getMaze()[1][5]; //to the right
			bottom = gameState.getMaze()[1][4]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 0;
				bestMatch[1] = 4;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 0 || prevCoord[1] != 6 )
		{
			left = gameState.getMaze()[1][5]; //left to the extra tile if inserted
			right = gameState.getMaze()[1][7]; //to the right
			bottom = gameState.getMaze()[1][6]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 0;
				bestMatch[1] = 6;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		//right side of board
		if( prevCoord[0] != 2 || prevCoord[1] != 8 )
		{
			left = gameState.getMaze()[2][7]; //above the extra tile (sorry about misleading name)
			right = gameState.getMaze()[1][7]; //below the tile
			bottom = gameState.getMaze()[3][7]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 2;
				bestMatch[1] = 8;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 4 || prevCoord[1] != 8 )
		{
			left = gameState.getMaze()[4][7]; //above the extra tile (sorry about misleading names)
			right = gameState.getMaze()[3][7]; //below the tile
			bottom = gameState.getMaze()[5][7]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 4;
				bestMatch[1] = 8;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 6 || prevCoord[1] != 8 )
		{
			left = gameState.getMaze()[6][7]; //above the extra tile (sorry about misleading name)
			right = gameState.getMaze()[5][7]; //below the tile
			bottom = gameState.getMaze()[7][7]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 6;
				bestMatch[1] = 8;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		//bottom of maze
		if( prevCoord[0] != 8 || prevCoord[1] != 2)
		{
			left = gameState.getMaze()[7][1]; //left to the extra tile if inserted
			right = gameState.getMaze()[7][3]; //to the right
			bottom = gameState.getMaze()[7][2]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 8;
				bestMatch[1] = 2;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 8 || prevCoord[1] != 4 )
		{
			left = gameState.getMaze()[7][3]; //left to the extra tile if inserted
			right = gameState.getMaze()[7][5]; //to the right
			bottom = gameState.getMaze()[7][4]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 8;
				bestMatch[1] = 4;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 8 || prevCoord[1] != 6 )
		{
			left = gameState.getMaze()[7][5]; //left to the extra tile if inserted
			right = gameState.getMaze()[7][7]; //to the right
			bottom = gameState.getMaze()[7][6]; //take it back now y'all

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 8;
				bestMatch[1] = 6;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		//left side of maze
		if( prevCoord[0] != 2 || prevCoord[1] != 0 )
		{
			left = gameState.getMaze()[2][1]; //above the extra tile (sorry about misleading name)
			right = gameState.getMaze()[1][1]; //below the tile
			bottom = gameState.getMaze()[3][1]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 2;
				bestMatch[1] = 0;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 4 || prevCoord[1] != 0 )
		{
			left = gameState.getMaze()[4][1]; //above the extra tile (sorry about misleading names)
			right = gameState.getMaze()[3][1]; //below the tile
			bottom = gameState.getMaze()[5][1]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 4;
				bestMatch[1] = 0;
				bestmatchCount = matchCount;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}
		if( prevCoord[0] != 6 || prevCoord[1] != 0 )
		{
			left = gameState.getMaze()[6][1]; //above the extra tile (sorry about misleading name)
			right = gameState.getMaze()[5][1]; //below the tile
			bottom = gameState.getMaze()[7][1]; //to the left of the tile

			matchCount = getMatches(extraTile, left, right, bottom);

			//checks if this match is better than our current "best match"
			if( matchCount >= bestmatchCount )
			{
				bestMatch[0] = 6;
				bestMatch[1] = 0;

				if( matchCount == numPosMatches )
				{
					return bestMatch;
				}
			}
		}

		//if all else fails and bestMatch is still {-1,-1} put the extra tile on opposite side of
		//the board
		if( bestMatch[0] == -1 && bestMatch[1] == -1 )
		{
			//top of board
			if( prevCoord[0] == 0 )
			{
				bestMatch[0] = 8;
				bestMatch[1] = prevCoord[1];
			}
			//bottom of board
			else if( prevCoord[0] == 8 )
			{
				bestMatch[0] = 0;
				bestMatch[1] = prevCoord[1];
			}
			//left of board
			else if( prevCoord[1] == 0 )
			{
				bestMatch[0] = prevCoord[0];
				bestMatch[1] = 8;
			}
			//right of board
			else if( prevCoord[1] == 8 )
			{
				bestMatch[0] = prevCoord[0];
				bestMatch[1] = 0;
			}
		}

		Log.i("X coord", ""+bestMatch[0]);
		Log.i("Y coord", ""+bestMatch[1]);

		return bestMatch;
	}

	/**
	 * returns the number of possible matches for tile connections
	 * @param type the type of maze tile, S, L, or T
	 * @return number of connection to possibly match
     */
	protected int getPosMatches( char type )
	{
		//the minimum will be 2, but if the tile is T shaped num possible matches will be 3
		int numTrue = 2;
		if( type == 'T' )
		{
			numTrue++;
		}
		return numTrue;
	}

	/**
	 * Finds how well the tile fits in by seeing how many sides of the extra tile will be matched
	 * if placed in this location.
	 * @param extra the extra tile to be placed
	 * @param left the tile to the left
	 * @param right the tile to the right
	 * @param bottom the tile below
     * @return how many sides match (true to true)
     */
	protected int getMatches( MazeTile extra, MazeTile left, MazeTile right, MazeTile bottom )
	{
		int count = 0;
		if( extra.getPathMap()[3] && left.getPathMap()[1] )
		{
			count++;
		}
		if( extra.getPathMap()[2] && bottom.getPathMap()[0] )
		{
			count++;
		}
		if( extra.getPathMap()[1] && right.getPathMap()[3] )
		{
			count++;
		}
		return count;
	}

	/**
	 * finds the coordinates of the treasure goal for the computer player. Returns the coordinates
	 * of the player's home square if their hand is empty
	 * @param state the game state
	 * @return coordinates of the treasure goal
     */
	protected int[] findTreasure( LabGameState state )
	{
		int[] coords = {-1, -1};
		MazeTile[][] theMaze = state.getMaze();

		//if there are still treasure cards in the player's hand
		if( state.getPlayerHand(this.playerNum).size() > 0 )
		{
			String goal = state.getPlayerHand(this.playerNum).get(0).getTreasure().getName();

			//iterate through the maze until treasure is found, will not check buffer
			for (int row = 1; row < 8; row++)
			{
				for (int col = 1; col < 8; col++)
				{
					if (theMaze[row][col].getTreasureSymbol().name().equals(goal))
					{
						coords[0] = row;
						coords[1] = col;
					}
				}
			}
		}
		else
		{
			//all treasures have been collected and the player goes back to their home tile

			//red player
			if( this.playerNum == 0 )
			{
				coords[0] = 1;
				coords[1] = 1;
			}
			//green player
			else if( this.playerNum == 1 )
			{
				coords[0] = 7;
				coords[1] = 1;
			}
			//blue player
			else if( this.playerNum == 2 )
			{
				coords[0] = 1;
				coords[1] = 7;
			}
			//yellow player
			else if( this.playerNum == 3 )
			{
				coords[0] = 7;
				coords[1] = 7;
			}
		}

		return coords;
	}

	/**
	 * finds the closest tile to the treasure goal
	 * @param state game state
	 * @param treasureCoord coordinates of the treasure goal
     * @return coordinates to go to
     */
	protected int[] findClosest( LabGameState state, int[] treasureCoord )
	{
		int[] coords = state.getPlayerCurTile(this.playerNum); 	//defaults to player's current
																	//location if all else fails

		//check above treasureCoord
		if( treasureCoord[0] != 1 && treasureCoord[0] != 0 && treasureCoord[1] != 0 && treasureCoord[1] != 8)
		{
			try
			{
				if( state.checkPath( treasureCoord[0]+1, treasureCoord[1] ) )
				{
					coords[0] = treasureCoord[0] + 1;
					coords[1] = treasureCoord[1];
					return coords;
				}
			}
			catch (ArrayIndexOutOfBoundsException aob )
			{
				//do nothing
			}
		}

		//check right
		if( treasureCoord[1] != 1 && treasureCoord[1] != 0 && treasureCoord[0] != 0 && treasureCoord[0] != 8 )
		{
			try
			{
				if (state.checkPath(treasureCoord[0], treasureCoord[1] + 1))
				{
					coords[0] = treasureCoord[0];
					coords[1] = treasureCoord[1] + 1;
					return coords;
				}
			}
			catch (ArrayIndexOutOfBoundsException aob )
			{
				//do nothing
			}
		}

		//check left
		if( treasureCoord[1] != 7 && treasureCoord[1] != 8 && treasureCoord[0] != 0 && treasureCoord[0] != 8 )
		{
			try
			{
				if (state.checkPath(treasureCoord[0], treasureCoord[1] - 1))
				{
					coords[0] = treasureCoord[0];
					coords[1] = treasureCoord[1] - 1;
					return coords;
				}
			}
			catch (ArrayIndexOutOfBoundsException aob )
			{
				//do nothing
			}
		}

		//check bottom
		if( treasureCoord[0] != 7 && treasureCoord[0] != 8 && treasureCoord[1] != 0 && treasureCoord[1] != 8)
		{
			try
			{
				if (state.checkPath(treasureCoord[0] - 1, treasureCoord[1]))
				{
					coords[0] = treasureCoord[0] - 1;
					coords[1] = treasureCoord[1];
					return coords;
				}
			}
			catch (ArrayIndexOutOfBoundsException aob )
			{
				//do nothing
			}
		}

		return coords;
	}
}