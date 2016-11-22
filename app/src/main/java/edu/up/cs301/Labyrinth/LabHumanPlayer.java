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
import android.widget.TextView;

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
    private Button moveButtonArea = null;


    private Activity myActivity;

    private LabMazeSurfaveView surfaceView;

    private LabGameState myState;

    private MazeTile[][] myMaze;

    //Set listener for the surface view

    public LabHumanPlayer(String name) {
        super("hi");
    }

    public View getTopView() {
        return null;
    }

    public void receiveInfo(GameInfo info) {

        Log.i("human Player", "called reciveInfo");




        if(surfaceView == null) return;




        if( info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo)
        {
            //The move was illegal, so let the user know using the surface view
            //surfaceView.flash(Color.RED, 50);
        }
        else if(!(info instanceof LabGameState))
        {
            //If the state is not a game state, ignore it
            return;
        }
        else
        {
            //Set the surface view's state
            //Invalidate the surface view
            myState = (LabGameState) info;
            myMaze = myState.getMaze();
            surfaceView.setState(myState);
            surfaceView.invalidate();
            drawExtraTile();
            Log.i("human player", "receiving");
        }
    }

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

        // how do we get the canvas from the surface view to draw on?
        surfaceView = (LabMazeSurfaveView) myActivity.findViewById(R.id.ACTUALMAZE);

        surfaceView.setOnTouchListener(this);


    }//end of set as GUI

    public void onClick(View v) {
        if (v == leftColB) {
            game.sendAction(new LabMoveExtraTile(this, 6, 0));
            game.sendAction(new LabMoveMazeAction(this,6,0));
            Log.i("leftColB Button", "Operational");
        } else if (v == leftColM) {
            game.sendAction(new LabMoveExtraTile(this, 4, 0));
            game.sendAction(new LabMoveMazeAction(this,4,0));
            Log.i("leftColM Button", "Operational");
        } else if (v == leftColT) {
            game.sendAction(new LabMoveExtraTile(this, 2, 0));
            game.sendAction(new LabMoveMazeAction(this,2,0));
            Log.i("leftColT Button", "Operational");
        } else if (v == topRowM) {
            game.sendAction(new LabMoveExtraTile(this, 0, 4));
            game.sendAction(new LabMoveMazeAction(this,0,4));
            Log.i("topRowM Button", "Operational");
        } else if (v == topRowL) {
            game.sendAction(new LabMoveExtraTile(this, 0, 2));
            game.sendAction(new LabMoveMazeAction(this,0,2));
            Log.i("topRowL Button", "Operational");
        } else if (v == topRowR) {
            game.sendAction(new LabMoveExtraTile(this, 0, 6));
            game.sendAction(new LabMoveMazeAction(this,0,6));
            Log.i("topRowR Button", "Operational");
        } else if (v == botRowL) {
            game.sendAction(new LabMoveExtraTile(this, 8, 2));
            game.sendAction(new LabMoveMazeAction(this,8,2));
            Log.i("botRowL Button", "Operational");
        } else if (v == botRowM) {
            game.sendAction(new LabMoveExtraTile(this, 8, 4));
            game.sendAction(new LabMoveMazeAction(this,8,4));
            Log.i("botRowM Button", "Operational");
        } else if (v == botRowR) {
            game.sendAction(new LabMoveExtraTile(this, 8, 6));
            game.sendAction(new LabMoveMazeAction(this,8,6));
            Log.i("botRowR Button", "Operational");
        } else if (v == rightColB) {
            game.sendAction(new LabMoveExtraTile(this, 6, 8));
            game.sendAction(new LabMoveMazeAction(this,6,8));
            Log.i("rightColB Button", "Operational");
        } else if (v == rightColM) {
            game.sendAction(new LabMoveExtraTile(this, 4, 8));
            game.sendAction(new LabMoveMazeAction(this,4,8));
            Log.i("rightColM Button", "Operational");
        } else if (v == rightColT) {
            game.sendAction(new LabMoveExtraTile(this, 2, 8));
            game.sendAction(new LabMoveMazeAction(this,2,8));
            Log.i("rightColT Button", "Operational");
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        //If the event is an "up" we need to ignore it
        if(event.getAction() != MotionEvent.ACTION_UP)
        {
            return true;
        }
        //Getting the x and y position of the touch
        int xCord = (int)event.getX();
        int yCord = (int)event.getY();
        //Sending these coordinates as a LabMovePieceAction
        game.sendAction(new LabMovePieceAction(this, xCord, yCord));
        //We have handled the event
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
                        topRowR.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        topRowM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        topRowL.setBackgroundColor(Color.RED);
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
                        leftColT.setBackgroundColor(Color.RED);
                        break;
                    case 4:
                        leftColM.setBackgroundColor(Color.RED);
                        break;
                    case 6:
                        leftColB.setBackgroundColor(Color.RED);
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
