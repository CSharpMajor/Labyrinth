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
