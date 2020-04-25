package com.eaglebjkbv.surfaceviewexample3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{

    SurfaceHolder holder;
    Thread gameThread=null;
    boolean gameReady=false;
    Canvas canvas;
    Paint paint;
    final static String TAG="mesaj";
    long mFPS=0;
    Bitmap walkingSprite;
    Rect srcRect,dstRect;
    int srcX,srcY,srcWidth,srcHeight;
    int dstX,dstY;
    int sayac=0;
    boolean yonIleri=true;

    public GameView(Context context) {
        super(context);
        holder=getHolder();
        paint=new Paint();
        srcX=0;srcY=0;srcWidth=250;srcHeight=380;
        dstX=0;dstY=300;
        walkingSprite= BitmapFactory.decodeResource(getResources(),R.drawable.walking);


    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void run() {
        while(gameReady){
            long startFrameTime=System.currentTimeMillis();
            if(holder.getSurface().isValid()){
                canvas=holder.lockCanvas();
                calculate();
                draw();
                holder.unlockCanvasAndPost(canvas);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            long timeThisFrame=System.currentTimeMillis()-startFrameTime;
            if(timeThisFrame>1){
                mFPS=1000/timeThisFrame;
            }
        }
    }

    private void calculate() {

        sayac++;
        if(sayac%8==0){
            sayac=0;

            srcX += 250;
            if (srcX > 1250) srcX = 0;

        }
        if(yonIleri){
            dstX+=4;
            srcY=0;
            if(dstX>800) yonIleri=!yonIleri;
        }else {
            srcY=380;
            dstX-=4;
            if(dstX<0) yonIleri=!yonIleri;

        }

    }

    private void draw() {


        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("FPS : "+mFPS,10,50,paint);
        paint.setColor(Color.RED);
        canvas.drawText("Canvas tan merhaba",100,300,paint);
        srcRect=new Rect(srcX,srcY,srcX+srcWidth,srcY+srcHeight);
        dstRect=new Rect(dstX,dstY,dstX+srcWidth,dstY+srcHeight);
        canvas.drawBitmap(walkingSprite,srcRect,dstRect,null);


    }

    public void resume() {
        if(gameThread==null){
            Log.d(TAG, "resume: ");
            gameThread=new Thread(this);
            gameThread.start();
            gameReady=true;

        }
    }

    public void pause() {
            gameReady=false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameThread=null;
    }


}
