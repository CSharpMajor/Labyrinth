package edu.up.cs301.Labyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import edu.up.cs301.game.R;

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
        //Log.i("LabMazeSurfaveView", ""+myMaze.length);
    }

    public void onDraw(Canvas g){
        //Log.i("LabMazeSurfaveView", "called on draw");
        if(myMaze == null) return;
        Paint p = new Paint();
        int testIter = 0;
        for(int i = 0; i<myMaze.length; i++){
            for(int j = 0; j<myMaze[i].length; j++){
                p.setColor(Color.BLUE);
                if(myMaze[i][j] == null) continue;
                if(myMaze[i][j].getTreasureSymbol().getFileId() != null){
                    //Log.i("SurfaceView", myMaze[i][j].getTreasureSymbol().getFileId());
                    String name = myMaze[i][j].getTreasureSymbol().getFileId();
                    //Log.i("SurfaceView", ""+getResources().getIdentifier(name,"mipmap","edu.up.cs301.game"));
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(name,"mipmap","edu.up.cs301.game"));
                    icon = RotateBitmap(icon, 90*myMaze[i][j].getTreasureSymbol().getRotationCorrection());
                    icon = RotateBitmap(icon, 90*myMaze[i][j].getOrientation());
                    icon = Bitmap.createScaledBitmap(icon, 140, 140, true);
                    //g.drawColor(Color.BLACK);
                    g.drawBitmap(icon, 125*i, 125*j, p);
                }
                else{
                    //Log.i("SurfaceView", ""+myMaze[i][j].getType());
                    if(myMaze[i][j].getType() == 'S'){
                        int rotation = myMaze[i][j].getOrientation();
                        //Log.i("SurfaceView", ""+rotation);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.straightcard);
                        icon = RotateBitmap(icon, 90*rotation);
                        icon = Bitmap.createScaledBitmap(icon, 140, 140, true);
                        g.drawBitmap(icon, 125*i, 125*j, p);
                    }
                    if(myMaze[i][j].getType() == 'L'){
                        Bitmap icon;
                        //if(i==0 && j==0){

                        //}
                        //else{
                            int rotation = myMaze[i][j].getOrientation();
                            //Log.i("SurfaceView", "rotation: "+rotation);
                            icon = BitmapFactory.decodeResource(getResources(), R.mipmap.blankcornercard);
                            icon = RotateBitmap(icon, 90*rotation);
                            icon = Bitmap.createScaledBitmap(icon, 140, 140, true);
                        //}

                        g.drawBitmap(icon, 125*i, 125*j, p);
                    }
                }

                for(int k=0; k<myMaze[i][j].getOccupiedBy().size(); k++){
                    Bitmap icon;
                    if(myMaze[i][j].getOccupiedBy().get(k) == 0){
                        icon = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("iconred","mipmap","edu.up.cs301.game"));
                        icon = Bitmap.createScaledBitmap(icon, 30, 30, true);
                        g.drawBitmap(icon, 125*i+50, 125*j+50, p);
                    }
                    else if(myMaze[i][j].getOccupiedBy().get(k) == 1){
                        icon = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("icongreen","mipmap","edu.up.cs301.game"));
                        icon = Bitmap.createScaledBitmap(icon, 30, 30, true);
                        g.drawBitmap(icon, 125*i+50, 125*j+50, p);
                    }
                    else if(myMaze[i][j].getOccupiedBy().get(k) == 2){
                        icon = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("iconblue","mipmap","edu.up.cs301.game"));
                        icon = Bitmap.createScaledBitmap(icon, 30, 30, true);
                        g.drawBitmap(icon, 125*i+50, 125*j+50, p);
                    }
                    else if(myMaze[i][j].getOccupiedBy().get(k) == 3){
                        icon = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("iconyellow","mipmap","edu.up.cs301.game"));
                        icon = Bitmap.createScaledBitmap(icon, 30, 30, true);
                        g.drawBitmap(icon, 125*i+50, 125*j+(k*25)+50, p);
                    }

                }




//
//                p.setStyle(Paint.Style.STROKE);
//                g.drawRect(i*125, j*125, (i*125)+100, (j*125)+100, p);
//                p.setColor(Color.BLACK);
//                //Drawing the true/false path data to the surface view
//                g.drawText(String.valueOf(myMaze[i][j].getPathMap()[0]), i*125+62, j*125+10, p);
//                g.drawText(String.valueOf(myMaze[i][j].getPathMap()[1]), i*125+100, j*125+62, p);
//                g.drawText(String.valueOf(myMaze[i][j].getPathMap()[2]), i*125+62, j*125+110, p);
//                g.drawText(String.valueOf(myMaze[i][j].getPathMap()[3]), i*125+5, j*125+62, p);
//                if(myMaze[i][j].getTreasureSymbol() != null){
//                    g.drawText(String.valueOf(myMaze[i][j].getTreasureSymbol().getName()), i*125+15, j*125+75, p);
//                }
//                //g.drawText(String.valueOf(myMaze[i][j].getOccupiedBy().size()), i*125+15, j*125+85, p);

           }
        }
    }


    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
