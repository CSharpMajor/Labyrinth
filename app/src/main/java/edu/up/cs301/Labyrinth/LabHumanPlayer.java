package edu.up.cs301.Labyrinth;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * A GUI that allows a human to play Labyrinth.
 *
 * @author Chloe Kuhar
 * @author Liz Frick
 * @author Nicole Kister
 * @author Mikayla Whiteaker
 * @version Nov 2016, preAlpha
 */
public class LabHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    //Variables for the buttons on the GUI
    //Buttons that surround the maze area
    private ImageButton leftColT = null;
    private ImageButton leftColM = null;
    private ImageButton leftColB = null;
    private ImageButton topRowL = null;
    private ImageButton topRowM = null;
    private ImageButton topRowR = null;
    private ImageButton botRowL = null;
    private ImageButton botRowM = null;
    private ImageButton botRowR = null;
    private ImageButton rightColT = null;
    private ImageButton rightColM = null;
    private ImageButton rightColB = null;
    //Move button
    private Button moveButtonArea = null;
    //rotate button
    private Button rotateButton = null;
    //Text view for the treasure card
    private TextView cardToGet = null;
    //Display for the treasures of the blue player
    private ImageView blueTreasures = null;
    //Display for the treasures of the green player
    private ImageView greenTreasures = null;
    //Display for the treasures of the yellow player
    private ImageView yellowTreasures = null;
    //Display for the treasures of the red player
    private ImageView redTreasures = null;
    //Display for the turn information
    //image view for the turn info
    private ImageView changingTurnInfo = null;
    //Player 0's Information (red)
    private TextView redPlayerInfo = null;
    //Player 1's information (green)
    private TextView greenPlayerInfo = null;
    //Player 2's information (blue)
    private TextView bluePlayerInfo = null;
    //Player 3's information (yellow)
    private TextView yellowPlayerInfo = null;

    private TextView turnInfo = null;
    //Variable for the activity
    private Activity myActivity;
    //Variable for the surface view
    private LabMazeSurfaveView surfaceView;
    //Variable for the state of the game
    private LabGameState myState;
    //The maze of the game
    private MazeTile[][] myMaze;
    //Variable that holds the coordinates of the extra tile
    private int coords[] = null;
    //Flag that keeps track of whether the player is to move their piece or to move the maze
    private boolean flag = false;
    //Flag that shows if the coordinates have been set or not
    private boolean coordsFlag = false;

    //Constructor for the human player
    public LabHumanPlayer(String name) {
        super(name);
    }

    public View getTopView() {
        return null;
    }

    /**
     * Receive info takes the game state that is sent to the player and updates the GUI according
     * to the new state of the game.
     *
     * @param info
     */
    public void receiveInfo(GameInfo info) {

        //Test to see if the player has received any info
        Log.i("human Player", "called reciveInfo");
        //If the surface view is null then just return
        if(surfaceView == null) return;

        //If the instance of the game we have received is illegal or not our turn, then we don't
        //want to do anything.
        if( info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo)
        {
        }
        //If the instance of the game we have received is not the game state we don't
        //want to do anything
        else if(!(info instanceof LabGameState))
        {
            //If the state is not a game state, ignore it
        }
        //If the instance of the game is the game state then we want to update the GUI with
        //the most current information from the state
        else
        {
            //If it is our turn we want to enable the move button
            if(((LabGameState) info).getTurnID() == playerNum){
                //If the coordinates have not been set (the beginning of our turn) we need
                //to set the coordinates as well as set the move button to be enabled at the start
                if(!coordsFlag)
                {
                    moveButtonArea.setEnabled(true);
                    rotateButton.setEnabled(true);
                    coords = ((LabGameState) info).findExtraTile();
                    coordsFlag = true;
                    flag = false;
                }
                //If the flag has been set the human player has moved the maze so we need to
                //disable the maze move buttons
                if(flag)
                {
                    leftColM.setEnabled(false);
                    leftColB.setEnabled(false);
                    leftColT.setEnabled(false);
                    rightColB.setEnabled(false);
                    rightColM.setEnabled(false);
                    rightColT.setEnabled(false);
                    topRowM.setEnabled(false);
                    topRowR.setEnabled(false);
                    topRowL.setEnabled(false);
                    botRowR.setEnabled(false);
                    botRowL.setEnabled(false);
                    botRowM.setEnabled(false);
                    flag = false;
                }
                //For the push!
                //If the flag has not been set we want to have the buttons to move the maze enabled
                else
                {
                    leftColM.setEnabled(true);
                    leftColB.setEnabled(true);
                    leftColT.setEnabled(true);
                    rightColB.setEnabled(true);
                    rightColM.setEnabled(true);
                    rightColT.setEnabled(true);
                    topRowM.setEnabled(true);
                    topRowR.setEnabled(true);
                    topRowL.setEnabled(true);
                    botRowR.setEnabled(true);
                    botRowL.setEnabled(true);
                    botRowM.setEnabled(true);
                }
            }
            //If it is not our turn we need to make sure the buttons on the GUI are not enabled
            else{
                leftColM.setEnabled(false);
                leftColB.setEnabled(false);
                leftColT.setEnabled(false);
                rightColB.setEnabled(false);
                rightColM.setEnabled(false);
                rightColT.setEnabled(false);
                topRowM.setEnabled(false);
                topRowR.setEnabled(false);
                topRowL.setEnabled(false);
                botRowR.setEnabled(false);
                botRowL.setEnabled(false);
                botRowM.setEnabled(false);
                moveButtonArea.setEnabled(false);
            }
            /**
             * The following code updates the GUI from the game state given to us
             */
            //Set the state to be from the game state
            myState = (LabGameState) info;
            //Get the maze from the game state
            myMaze = myState.getMaze();
            //Set the state of the surface view
            surfaceView.setState(myState);

            //The following arrays are the arrays of collected treasure cards from each player
            ArrayList<TCard> blueTreasure = ((LabGameState) info).getPlayerCollected(2);
            ArrayList<TCard> greenTreasure = ((LabGameState) info).getPlayerCollected(1);
            ArrayList<TCard> yellowTreasure = ((LabGameState) info).getPlayerCollected(3);
            ArrayList<TCard> redTreasure = ((LabGameState) info).getPlayerCollected(0);

            //The number of treasures each player has collected
            int blueTreasureNum = 0;
            int greenTreasureNum = 0;
            int yellowTreasureNum = 0;
            int redTreasureNum = 0;

            //If the blueTreasure array has no cards, the blue player has collected 0 treasures
           if(blueTreasure == null)
           {
               //Set the number of treasures the blue player has collected to 0
               blueTreasureNum = 0;
           }
            //If the greenTreasure array has no cards, the green player has collected 0 treasures
            if(greenTreasure == null)
            {
                //Set the number of treasures the green player has collected to 0
                greenTreasureNum = 0;
            }
            //If the yellowTreasure array has no cards, the yellow player has collected 0 treasures
            if(yellowTreasure == null)
            {
                //Set the number of treasures the yellow player has collected to 0
                yellowTreasureNum = 0;
            }
            //If the redTreasure array has no cards, the red player has collected 9 treasures
            if(redTreasure == null)
            {
                //Set the number of treasures the red player has collected to 0
                redTreasureNum = 0;
            }
            //If the treasure array isn't null, then it must have cards so get the size of the array
            //to see how many cards the player has collected
            else
            {
                blueTreasureNum = blueTreasure.size();
                greenTreasureNum = greenTreasure.size();
                yellowTreasureNum = yellowTreasure.size();
                redTreasureNum = redTreasure.size();
            }
            /**
             * The following switch statement sets the number of treasures the blue player has
             * to the screen. It looks at the variable blueTreasureNum and correctly displays the
             * number of treasures. Each case is for all of the number of treasure cards
             * a player could possibly have.
             */
            switch(blueTreasureNum){
                case 0:
                    blueTreasures.setImageResource(R.mipmap.nocards);
                    break;
                case 1:
                    blueTreasures.setImageResource(R.mipmap.cardsone);
                    break;
                case 2:
                    blueTreasures.setImageResource(R.mipmap.cardtwo);
                    break;
                case 3:
                    blueTreasures.setImageResource(R.mipmap.cardthree);
                    break;
                case 4:
                    blueTreasures.setImageResource(R.mipmap.cardfour);
                    break;
                case 5:
                    blueTreasures.setImageResource(R.mipmap.cardfive);
                    break;
                case 6:
                    blueTreasures.setImageResource(R.mipmap.cardsix);
                    break;
                case 7:
                    blueTreasures.setImageResource(R.mipmap.cardseven);
                    break;
                case 8:
                    blueTreasures.setImageResource(R.mipmap.cardeight);
                    break;
                case 9:
                    blueTreasures.setImageResource(R.mipmap.cardnine);
                    break;
                case 10:
                    blueTreasures.setImageResource(R.mipmap.cardten);
                    break;
                case 11:
                    blueTreasures.setImageResource(R.mipmap.cardeleven);
                    break;
                case 12:
                    blueTreasures.setImageResource(R.mipmap.cardtwelve);
                    break;
            }

            /**
             * The following switch statement sets the number of treasures the red player has
             * to the screen. It looks at the variable redTreasureNum and correctly displays the
             * number of treasures. Each case is for all of the number of treasure cards
             * a player could possibly have.
             */
            switch(redTreasureNum){
                case 0:
                    redTreasures.setImageResource(R.mipmap.nocards);
                    break;
                case 1:
                    redTreasures.setImageResource(R.mipmap.cardsone);
                    break;
                case 2:
                    redTreasures.setImageResource(R.mipmap.cardtwo);
                    break;
                case 3:
                    redTreasures.setImageResource(R.mipmap.cardthree);
                    break;
                case 4:
                    redTreasures.setImageResource(R.mipmap.cardfour);
                    break;
                case 5:
                    redTreasures.setImageResource(R.mipmap.cardfive);
                    break;
                case 6:
                    redTreasures.setImageResource(R.mipmap.cardsix);
                    break;
                case 7:
                    redTreasures.setImageResource(R.mipmap.cardseven);
                    break;
                case 8:
                    redTreasures.setImageResource(R.mipmap.cardeight);
                    break;
                case 9:
                    redTreasures.setImageResource(R.mipmap.cardnine);
                    break;
                case 10:
                    redTreasures.setImageResource(R.mipmap.cardten);
                    break;
                case 11:
                    redTreasures.setImageResource(R.mipmap.cardeleven);
                    break;
                case 12:
                    redTreasures.setImageResource(R.mipmap.cardtwelve);
                    break;
            }

            /**
             * The following switch statement sets the number of treasures the green player has
             * to the screen. It looks at the variable greenTreasureNum and correctly displays the
             * number of treasures. Each case is for all of the number of treasure cards
             * a player could possibly have.
             */
            switch(greenTreasureNum){
                case 0:
                    greenTreasures.setImageResource(R.mipmap.nocards);
                    break;
                case 1:
                    greenTreasures.setImageResource(R.mipmap.cardsone);
                    break;
                case 2:
                    greenTreasures.setImageResource(R.mipmap.cardtwo);
                    break;
                case 3:
                    greenTreasures.setImageResource(R.mipmap.cardthree);
                    break;
                case 4:
                    greenTreasures.setImageResource(R.mipmap.cardfour);
                    break;
                case 5:
                    greenTreasures.setImageResource(R.mipmap.cardfive);
                    break;
                case 6:
                    greenTreasures.setImageResource(R.mipmap.cardsix);
                    break;
                case 7:
                    greenTreasures.setImageResource(R.mipmap.cardseven);
                    break;
                case 8:
                    greenTreasures.setImageResource(R.mipmap.cardeight);
                    break;
                case 9:
                    greenTreasures.setImageResource(R.mipmap.cardnine);
                    break;
                case 10:
                    greenTreasures.setImageResource(R.mipmap.cardten);
                    break;
                case 11:
                    greenTreasures.setImageResource(R.mipmap.cardeleven);
                    break;
                case 12:
                    greenTreasures.setImageResource(R.mipmap.cardtwelve);
                    break;
            }

            /**
             * The following switch statement sets the number of treasures the yellow player has
             * to the screen. It looks at the variable yellowTreasureNum and correctly displays the
             * number of treasures. Each case is for all of the number of treasure cards
             * a player could possibly have.
             */
            switch(yellowTreasureNum){
                case 0:
                    yellowTreasures.setImageResource(R.mipmap.nocards);
                    break;
                case 1:
                    yellowTreasures.setImageResource(R.mipmap.cardsone);
                    break;
                case 2:
                    yellowTreasures.setImageResource(R.mipmap.cardtwo);
                    break;
                case 3:
                    yellowTreasures.setImageResource(R.mipmap.cardthree);
                    break;
                case 4:
                    yellowTreasures.setImageResource(R.mipmap.cardfour);
                    break;
                case 5:
                    yellowTreasures.setImageResource(R.mipmap.cardfive);
                    break;
                case 6:
                    yellowTreasures.setImageResource(R.mipmap.cardsix);
                    break;
                case 7:
                    yellowTreasures.setImageResource(R.mipmap.cardseven);
                    break;
                case 8:
                    yellowTreasures.setImageResource(R.mipmap.cardeight);
                    break;
                case 9:
                    yellowTreasures.setImageResource(R.mipmap.cardnine);
                    break;
                case 10:
                    yellowTreasures.setImageResource(R.mipmap.cardten);
                    break;
                case 11:
                    yellowTreasures.setImageResource(R.mipmap.cardeleven);
                    break;
                case 12:
                    yellowTreasures.setImageResource(R.mipmap.cardtwelve);
                    break;
            }

            //If the human player's number is 0, display player 0's top card to collect
            if(playerNum == 0){
                //Get player 0's array list of cards left to collect
                ArrayList<TCard> temp = ((LabGameState) info).getPlayerHand(0);
                //Get the name of the treasure of the first card in the list
                String cardName = temp.get(0).getTreasure().getName();
                cardToGet.setText("Current Goal:\n" + cardName);
                //Set the GUI text to let the user know what their current goal is
                cardToGet.setText("Current Goal:\n" + cardName);
            }
            //If the human player's number is 1, display player 1's top card to collect
            else if(playerNum == 1){
                //Get player 1's array list of cards left to collect
                ArrayList<TCard> temp = ((LabGameState) info).getPlayerHand(1);
                //Get the name of the treasure of the first card in the list
                String cardName = temp.get(0).getTreasure().getName();
                //Set the GUI text to let the user know what their current goal is
                cardToGet.setText("Current Goal:\n" + cardName);
            }
            //If the human player's number is 2, display player 2's top card to collect
            else if(playerNum == 2){
                //Get player 2's array list of cards left to collect
                ArrayList<TCard> temp = ((LabGameState) info).getPlayerHand(2);
                //Get the name of the treasure of the first card in the list
                String cardName = temp.get(0).getTreasure().getName();
                //Set the GUI text to let the user know what their current goal is
                cardToGet.setText("Current Goal:\n" + cardName);
            }
            //If the human player's number is 3, display player 3's top card to collect
            else if(playerNum == 3){
                //Get player 3's array list of cards left to collect
                ArrayList<TCard> temp = ((LabGameState) info).getPlayerHand(3);
                //Get the name of the treasure of the first card in the list
                String cardName = temp.get(0).getTreasure().getName();
                //Set the GUI text to let the user know what their current goal is
                cardToGet.setText("Current Goal:\n" + cardName);
            }

            /**
             * The following set of statements change the turn counter to accurately display which
             * user's turn it is
             */
            //If it is player 0's turn, set the text to "Red"
            if (((LabGameState) info).getTurnID() == 0){
                changingTurnInfo.setImageResource(R.mipmap.iconred);
            }
            //If it is player 1's turn, set the text to "Green"
            else if(((LabGameState) info).getTurnID() == 1 ){
                changingTurnInfo.setImageResource(R.mipmap.icongreen);
            }
            //If it is player 2's turn, set the text to "Blue"
            else if(((LabGameState) info).getTurnID() == 2){
                changingTurnInfo.setImageResource(R.mipmap.iconblue);
            }
            //If it is player 3's turn, set the text to "Yellow"
            else if(((LabGameState) info).getTurnID() == 3){
                changingTurnInfo.setImageResource(R.mipmap.iconyellow);
            }

            //Setting the player's names on the GUI. It only sets the number of names based on
            //how many players are in the game. This also allows the human player to be another color
            for(int i = 0; i < allPlayerNames.length; i++)
            {
                if(i == 0)
                {
                    redPlayerInfo.setText(allPlayerNames[0]);
                }
                else if(i==1)
                {
                    greenPlayerInfo.setText(allPlayerNames[1]);
                }
                else if(i==2){
                    bluePlayerInfo.setText(allPlayerNames[2]);
                }
                else if(i==3)
                {
                    yellowPlayerInfo.setText(allPlayerNames[3]);
                }
            }
            //Invalidate the surface view to draw all of the extra changes
            surfaceView.invalidate();
            drawExtraTile();
            //Log for testing purposes
            Log.i("human player", "receiving");
        }
    }

    /**
     * setAsGui sets the listeners for the buttons and surface view
     *
     * @param activity
     */
    public void setAsGui(GameMainActivity activity) {

        //Seting the activity
        myActivity = activity;

        //Setting the GUI
        activity.setContentView(R.layout.ttt_human_player1);

        //Buttons on the left side of the board
        this.leftColT = (ImageButton) activity.findViewById(R.id.leftColT);
        this.leftColB = (ImageButton) activity.findViewById(R.id.leftColB);
        this.leftColM = (ImageButton) activity.findViewById(R.id.leftColM);
        //Buttons on the top side of the board
        this.topRowL = (ImageButton) activity.findViewById(R.id.topRowL);
        this.topRowM = (ImageButton) activity.findViewById(R.id.topRowM);
        this.topRowR = (ImageButton) activity.findViewById(R.id.topRowR);
        //Buttons on the bottom side of the board
        this.botRowL = (ImageButton) activity.findViewById(R.id.botRowL);
        this.botRowM = (ImageButton) activity.findViewById(R.id.botRowM);
        this.botRowR = (ImageButton) activity.findViewById(R.id.botRowR);
        //Buttons on the right side of the board
        this.rightColB = (ImageButton) activity.findViewById(R.id.rightColB);
        this.rightColM = (ImageButton) activity.findViewById(R.id.rightColM);
        this.rightColT = (ImageButton) activity.findViewById(R.id.rightColT);

        //Setting the text view for the current treasure card
        this.cardToGet = (TextView) activity.findViewById(R.id.treasureCardToGet);
        //Image for the number of treasures the blue player has
        this.blueTreasures = (ImageView) activity.findViewById(R.id.blueTreasures);
        //Image for the number of treasures the red player has
        this.redTreasures = (ImageView) activity.findViewById(R.id.redTreasures);
        //Image for the number of treasures the green player has
        this.greenTreasures = (ImageView) activity.findViewById(R.id.greenTreasures);
        //Image for the number of treasures the yellow player has
        this.yellowTreasures = (ImageView) activity.findViewById(R.id.yellowTreasures);
        //Setting the listener for the turn info text area
        this.changingTurnInfo = (ImageView) activity.findViewById(R.id.changingTurnInfo);
        this.changingTurnInfo = (ImageView)activity.findViewById(R.id.changingTurnInfo);

        //confirm move button
        this.moveButtonArea = (Button) activity.findViewById(R.id.moveButton) ;
        //rotate button
        this.rotateButton = (Button) activity.findViewById(R.id.rotatebutton);

        //Setting the text views for player information
        this.bluePlayerInfo = (TextView)activity.findViewById(R.id.BlueInfo);
        this.greenPlayerInfo = (TextView)activity.findViewById(R.id.greenInfo2);
        this.yellowPlayerInfo = (TextView)activity.findViewById(R.id.yellowInfo2);
        this.redPlayerInfo = (TextView)activity.findViewById(R.id.redInfo2);

        //Setting the onclick listeners for the buttons
        leftColM.setOnClickListener(this);
        leftColB.setOnClickListener(this);
        leftColT.setOnClickListener(this);
        topRowM.setOnClickListener(this);
        topRowR.setOnClickListener(this);
        topRowL.setOnClickListener(this);
        botRowR.setOnClickListener(this);
        botRowM.setOnClickListener(this);
        botRowL.setOnClickListener(this);
        rightColT.setOnClickListener(this);
        rightColB.setOnClickListener(this);
        rightColM.setOnClickListener(this);
        moveButtonArea.setOnClickListener(this);
        rotateButton.setOnClickListener(this);

        //Setting up the surface view
        surfaceView = (LabMazeSurfaveView) myActivity.findViewById(R.id.ACTUALMAZE);
        //Setting the listener for the surface view
        surfaceView.setOnTouchListener(this);
    }//end of set as GUI

    /**
     *
     * @param v
     */
    public void onClick(View v) {
        //so all coordinates are y,x on the SurfaceView...oops
        if (v == leftColB) {
            if(coords[0] == 8 && coords[1] == 6)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 0, 6));
        } else if (v == leftColM) {
            if(coords[0] == 8 && coords[1] == 4)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 0, 4));
        } else if (v == leftColT) {
            if(coords[0] == 8 && coords[1] == 2)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 0, 2));
        } else if (v == topRowM) {
            if(coords[0] == 4 && coords[1] == 8)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 4, 0));
        } else if (v == topRowL) {
            if(coords[0] == 2 && coords[1] == 8)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 2, 0));
        } else if (v == topRowR) {
            if(coords[0] == 6 && coords[1] == 8)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 6, 0));
        } else if (v == botRowL) {
            if(coords[0] == 2 && coords[1] == 0)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            //For the push!
            game.sendAction(new LabMoveExtraTile(this, 2, 8));
        } else if (v == botRowM) {
            if(coords[0] == 4 && coords[1] == 0)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 4, 8));
        } else if (v == botRowR) {
            if(coords[0] == 6 && coords[1] == 0)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 6, 8));
        } else if (v == rightColB) {
            if(coords[0] == 0 && coords[1] == 6)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 8, 6));
        } else if (v == rightColM) {
            if(coords[0] == 0 && coords[1] == 4)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 8, 4));
        } else if (v == rightColT) {
            if(coords[0] == 0 && coords[1] == 2)
            {
                moveButtonArea.setEnabled(false);
            }
            else
            {
                moveButtonArea.setEnabled(true);
            }
            game.sendAction(new LabMoveExtraTile(this, 8, 2));
        }
        else if (v == moveButtonArea )
        {
            /**
             * Once the user presses the move button we need to keep track of which part of the turn
             * it is. If the flag has not been set we know the player is ending their maze move turn
             * so we need to set the flag. If the flag is true then the player is ending their
             * move piece turn and so we need to reset the flag for the next turn
             */
            //Also need to reset the flag for the coordinates
            if(coordsFlag){
                coordsFlag = false;
            }
            int[] coordinates = myState.findExtraTile();
            game.sendAction( new LabMoveMazeAction(this, coordinates[0], coordinates[1]));
            //Make the move button disabled to keep the user from pressing it when they shouldn't be
            moveButtonArea.setEnabled(false);
            rotateButton.setEnabled(false);
        }
        //If the user has clicked the rotate button then we need to rotate the tile
        else if(v == rotateButton) {
            game.sendAction(new LabRotateExtraTileAction(this));
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        //If the event is an "up" we need to ignore it
        if(event.getAction() != MotionEvent.ACTION_UP)
        {
            return true;
        }
        //Getting the x and y position of the touch 125 is the current width of the tile
        int xCord = (int)event.getX() / 125;
        int yCord = (int)event.getY() / 125;

        Log.i("onTouch", String.valueOf(xCord) + "   " + String.valueOf(yCord));

        //Sending these coordinates as a LabMovePieceAction
        game.sendAction(new LabMovePieceAction(this, xCord, yCord, this.playerNum));
        //We have handled the event
        surfaceView.invalidate();
        return true;
    }

    private void drawExtraTile(){
        if(myState != null) {
            int[] coords = myState.findExtraTile();
            Log.i("placeExtra", ""+coords[0]+coords[1]);
            topRowR.setBackgroundColor(Color.WHITE);
            topRowM.setBackgroundColor(Color.WHITE);
            topRowL.setBackgroundColor(Color.WHITE);
            rightColT.setBackgroundColor(Color.WHITE);
            rightColM.setBackgroundColor(Color.WHITE);
            rightColB.setBackgroundColor(Color.WHITE);
            leftColT.setBackgroundColor(Color.WHITE);
            leftColM.setBackgroundColor(Color.WHITE);
            leftColB.setBackgroundColor(Color.WHITE);
            botRowL.setBackgroundColor(Color.WHITE);
            botRowM.setBackgroundColor(Color.WHITE);
            botRowR.setBackgroundColor(Color.WHITE);


            if (coords[0] == 0) {
                switch (coords[1]) {
                    case 2:
                        leftColT.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        leftColM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        leftColB.setBackgroundColor(Color.RED);
                        break;
                }
            } else if (coords[0] == 8) {
                switch (coords[1]) {
                    case 2:
                        rightColT.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        rightColM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        rightColB.setBackgroundColor(Color.RED);
                        break;
                }
            } else if (coords[1] == 0) {
                switch (coords[0]) {
                    case 2:
                        topRowL.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        topRowM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        topRowR.setBackgroundColor(Color.RED);
                        break;
                }
            } else if (coords[1] == 8) {
                switch (coords[0]) {
                    case 2:
                        botRowL.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        botRowM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        botRowR.setBackgroundColor(Color.RED);
                        break;
                }
            }
        }
    }
}
