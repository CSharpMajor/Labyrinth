package edu.up.cs301.Labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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
        Log.i("LabMazeSurfaveView", ""+myMaze.length);
    }

    public void onDraw(Canvas g){
        Log.i("LabMazeSurfaveView", "called on draw");
        if(myMaze == null) return;
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        for(int i = 0; i<myMaze.length; i++){
            for(int j = 0; j<myMaze[i].length; j++){
                if(myMaze[i][j] == null) continue;
                p.setStyle(Paint.Style.STROKE);
                g.drawRect(i*125, j*125, 125, 125, p);
                g.drawText(String.valueOf(myMaze[i][j].getPathMap()[0]), i*125+62, j*125+5, p);
                //g.drawText(String.valueOf(myMaze[i][j].getPathMap()[1]), i*125+120, j*125+62, p);
                if(myMaze[i][j].getTreasureSymbol() != null){
                    g.drawText(String.valueOf(myMaze[i][j].getTreasureSymbol().getName()), i*125+62, j*125+62, p);
                }
                for(int k=0; k<myMaze[i][j].getOccupiedBy().size(); k++){
                    g.drawText(String.valueOf(myMaze[i][j].getOccupiedBy().get(k)), i*125+70, j*125+62+(j*5), p);
                }

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
