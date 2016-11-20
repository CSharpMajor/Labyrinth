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
public class LabHumanPlayer extends GameHumanPlayer   {

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

    private GameMainActivity myActivity;

    //Set listener for the surface view

    public LabHumanPlayer(String name)
    {
        super("hi");
    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public View getTopView() {
        return null;
    }

    public void receiveInfo(GameInfo info) {

        if( info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo)
        {
            //The move was illegal, so let the user know using the surface view
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
        }
    }

    public void setAsGui(GameMainActivity activity) {

        //Seting the activity
        myActivity = activity;

        //Setting the GUI
        activity.setContentView(R.layout.ttt_human_player1);
    }
//        //Buttons on the left side of the board
//        this.leftColT = (ImageButton)activity.findViewById(R.id.leftColT);
//        this.leftColB = (ImageButton)activity.findViewById(R.id.leftColB);
//        this.leftColM = (ImageButton)activity.findViewById(R.id.leftColM);
//        //Buttons on the top side of the board
//        this.topRowL = (ImageButton)activity.findViewById(R.id.topRowL);
//        this.topRowM = (ImageButton)activity.findViewById(R.id.topRowM);
//        this.topRowR = (ImageButton)activity.findViewById(R.id.topRowR);
//        //Buttons on the bottom side of the board
//        this.botRowL = (ImageButton)activity.findViewById(R.id.botRowL);
//        this.botRowM = (ImageButton)activity.findViewById(R.id.botRowM);
//        this.botRowR = (ImageButton)activity.findViewById(R.id.botRowR);
//        //Buttons on the right side of the board
//        this.rightColB = (ImageButton)activity.findViewById(R.id.rightColB);
//        this.rightColM = (ImageButton)activity.findViewById(R.id.rightColM);
//        this.rightColT = (ImageButton)activity.findViewById(R.id.rightColT);
//
//        //Setting the onclick listeners for the buttons
//        leftColM.setOnClickListener(this);
//        leftColB.setOnClickListener(this);
//        leftColT.setOnClickListener(this);
//        topRowM.setOnClickListener(this);
//        topRowR.setOnClickListener(this);
//        topRowL.setOnClickListener(this);
//        botRowR.setOnClickListener(this);
//        botRowM.setOnClickListener(this);
//        botRowL.setOnClickListener(this);
//        rightColT.setOnClickListener(this);
//        rightColB.setOnClickListener(this);
//        rightColM.setOnClickListener(this);
//    }
//
//    public void onClick(View v) {
//        if(v == leftColB) {
//            Log.i("leftColB Button", "Operational");
//        }
//        else if(v == leftColM){
//            Log.i("leftColM Button", "Operational");
//        }
//        else if(v == leftColT){
//            Log.i("leftColT Button", "Operational");
//        }
//        else if(v == topRowM){
//            Log.i("topRowM Button", "Operational");
//        }
//        else if(v == topRowL){
//            Log.i("topRowL Button", "Operational");
//        }
//        else if(v == topRowR){
//            Log.i("topRowR Button", "Operational");
//        }
//        else if (v == botRowL){
//            Log.i("botRowL Button", "Operational");
//        }
//        else if(v == botRowM){
//            Log.i("botRowM Button", "Operational");
//        }
//        else if(v == botRowR){
//            Log.i("botRowR Button", "Operational");
//        }
//        else if(v == rightColB){
//            Log.i("rightColB Button", "Operational");
//        }
//        else if(v == rightColM){
//            Log.i("rightColM Button", "Operational");
//        }
//        else if(v == rightColT){
//            Log.i("rightColT Button", "Operational");
//        }
//    }
}
