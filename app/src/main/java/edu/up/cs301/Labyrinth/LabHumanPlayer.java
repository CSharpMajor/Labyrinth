package edu.up.cs301.Labyrinth;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
 * A GUI that allows a human to play tic-tac-toe. Moves are made by clicking
 * regions on a canvas
 *
 * @author Steven R. Vegdahl
 * @version September 2016
 */
public class LabHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    public LabHumanPlayer()
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

    }

    public void setAsGui(GameMainActivity activity) {

    }
}
