package edu.up.cs301.Labyrinth;

import android.util.Log;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * this is the primary activity for Labyrinth game
 * 
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */
public class LabMainActivity extends GameMainActivity {
	
	public static final int PORT_NUMBER = 5213;
	public static String PACKAGE_NAME;
	/**
	 * a tic-tac-toe game is for two players. The default is human vs. computer
	 */
	@Override
	public GameConfig createDefaultConfig() {

		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
		

		// Human player
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new LabHumanPlayer(name);
						//R.layout.ttt_human_player1);
			}
		});

		// dumb computer player
		playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
			public GamePlayer createPlayer(String name) {
				return new LabComputerPlayer(name);
			}
		});

		playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
			public GamePlayer createPlayer(String name) {
				return new LabComputerPlayer(name);
			}
		});

		playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
			public GamePlayer createPlayer(String name) {
				return new LabComputerPlayer(name);
			}
		});
		// smarter computer player
//		playerTypes.add(new GamePlayerType("Computer Player (smart)") {
//			public GamePlayer createPlayer(String name) {
//				return new LabSmartComputerPlayer(name);
//			}
//		});

		// Create a game configuration class for Tic-tac-toe
		GameConfig defaultConfig = new GameConfig(playerTypes, 2,4, "The Amazing Labyrinth", PORT_NUMBER);

		// Add the default players
		defaultConfig.addPlayer("Human", 0); // GUI player
		defaultConfig.addPlayer("Computer", 1); // dumb computer player
		defaultConfig.addPlayer("Computer", 2); //dumb computer player
		defaultConfig.addPlayer("Computer", 3);	//dumb computer player

		// Set the initial information for the remote player
		defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI
		
		//done!
		return defaultConfig;
		
	}//createDefaultConfig


	/**
	 * createLocalGame
	 * 
	 * Creates a new game that runs on the server tablet,
	 * 
	 * @return a new, game-specific instance of a sub-class of the LocalGame
	 *         class.
	 */
	@Override
	public LocalGame createLocalGame() {
		//Log.i("main", "hello");
		PACKAGE_NAME = getApplicationContext().getPackageName();
		Log.i("main", PACKAGE_NAME);
		return new LabLocalGame();

	}

}
