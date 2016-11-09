package edu.up.cs301.Labyrinth;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;

/**
 * The TTTLocalGame class for a simple tic-tac-toe game.  Defines and enforces
 * the game rules; handles interactions with players.
 * 
 * @author Steven R. Vegdahl 
 * @version July 2013
 */

public class LabLocalGame extends LocalGame {


	protected void sendUpdatedStateTo(GamePlayer p) {

	}

	protected boolean canMove(int playerIdx) {
		return false;
	}

	protected String checkIfGameOver() {
		return null;
	}

	protected boolean makeMove(GameAction action) {
		return false;
	}
}
