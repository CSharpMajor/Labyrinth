package edu.up.cs301.Labyrinth;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import android.graphics.Point;

/**
 * A computerized tic-tac-toe player that recognizes an immediate win
 * or loss, and plays appropriately.  If there is not an immediate win
 * (which it plays) or loss (which it blocks), it moves randomly.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */
public class LabSmartComputerPlayer extends GameComputerPlayer
{
	/**
	 * instance variable that tells which piece am I playing ('X' or 'O').
	 * This is set once the player finds out which player they are, in the
	 * 'initAfterReady' method.
	 */
	protected char piece;

	/**
	 * constructor for a computer player
	 *
	 * @param name
	 * 		the player's name
	 */
	public LabSmartComputerPlayer (String name) {
		// invoke superclass constructor
		super(name);
	}// constructor

	/**
	 * perform any initialization that needs to be done after the player
	 * knows what their game-position and opponents' names are.
	 */
	protected void initAfterReady() {
		// initialize our piece
		piece = "XO".charAt(playerNum);
	}// initAfterReady

	/**
	 * Called when the player receives a game-state (or other info) from the
	 * game.
	 *
	 * @param info
	 * 		the message from the game
	 */
	@Override
	protected void receiveInfo(GameInfo info) {

		/*// if it's not a TTTState message, ignore it; otherwise
		// cast it
		if (!(info instanceof TTTState)) return;
		TTTState myState = (TTTState)info;

		// if it's not our move, ignore it
		if (myState.getWhoseMove() != this.playerNum) return;

		// sleep for a second to make any observers think that we're thinking
		sleep(1000);

		// if we find a win, select that move
		Point win = findWin(myState, piece);
		if (win != null) {
			game.sendAction(new TTTMoveAction(this, win.y, win.x));
			return;
		}

		// if we find a threat of a loss (i.e., a direct win for out opponent),
		// select that position as a blocking move.
		char opponentPiece = piece == 'X' ? 'O' : 'X';
		Point loss = findWin(myState, opponentPiece);
		if (loss != null) {
			game.sendAction(new TTTMoveAction(this, loss.y, loss.x));
			return;
		}

		// otherwise, make a move that is randomly selected from the
		// blank squares ...

		// count the spaces
		int spaceCount = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (myState.getPiece(j, i) == ' ') spaceCount++;
			}
		}

		// generate a random integer in range 0 through #spaces-1
		int selectCount = (int)(spaceCount*Math.random());

		// re-find the space that corresponds to the random integer we
		// just generated; make that move
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (myState.getPiece(j, i) == ' ') {
					if (selectCount == 0) {
						// make the move
						game.sendAction(new TTTMoveAction(this, j, i));
						return;
					}
					selectCount--;
				}
			}
		}*/
	}// receiveInfo
}