package edu.up.cs301.Labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mikayla on 11/20/2016.
 */

public class LabMazeSurfaveView extends SurfaceView implements SurfaceHolder.Callback{

    protected LabGameState state;

    private SurfaceHolder mHolder;

    MazeTile[][] myMaze;

    public LabMazeSurfaveView(Context context) {
        super(context);
    }

    public LabMazeSurfaveView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public LabMazeSurfaveView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public void setState(LabGameState s){
        this.state = s;
        myMaze = state.getMaze();
    }

    public void onDraw(Canvas g){
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        for(int i = 0; i<myMaze.length; i++){
            for(int j = 0; j<myMaze[i].length; j++){
                g.drawText("Testing", i*10, j*10, p);
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
