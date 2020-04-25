package com.eaglebjkbv.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private SurfaceHolder holder;
    private Thread drawThread;
    private boolean surfaceReady = false;
    private boolean drawingActive = false;
    int i;
    Bitmap walking;
    int spriteX, spriteY, spriteWidth, spriteHeight;
    int srcX, srcY;
    boolean ileri = true;

    Thread t;
    Context context;

    public GameView(Context context) {
        super(context);
        this.context = context;
        holder = getHolder();
        spriteX = 100;
        spriteY = 100;
        spriteWidth = 218;
        spriteHeight = 330;
        srcX = 0;
        srcY = 0;

        walking = BitmapFactory.decodeResource(getResources(), R.drawable.walking);
    }

    @Override
    public void run() {
        Log.d("mesaj", "Dongu Çalıştı");
        while (surfaceReady) {
            if (!holder.getSurface().isValid()) {
                continue;
            }

            Canvas c = holder.lockCanvas();
            c.drawColor(Color.WHITE);
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            srcX += 218;
            if (srcX > 1000)
                srcX = 0;
            if (ileri) {
                srcY = 0;
                spriteX += 20;
                if (spriteX > 800)
                    ileri = !ileri;
            } else {
                srcY = 330;
                spriteX -= 20;
                if (spriteX < 100)
                    ileri = !ileri;
            }

            Paint p = new Paint();
            Rect src = new Rect(srcX, srcY, srcX + spriteWidth, srcY + spriteHeight);
            Rect dst = new Rect(spriteX, spriteY, spriteX + spriteWidth, spriteY + spriteHeight);

            c.drawBitmap(walking, src, dst, null);
            holder.unlockCanvasAndPost(c);
        }
    }

    public void resumed() {
        surfaceReady = true;
        t = new Thread(this);
        t.start();
        Log.d("mesaj", "Created" + surfaceReady);
    }

    public void pasused() {
        surfaceReady = false;
        Log.d("mesaj", "Destroyed" + surfaceReady);
        while (true) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        t = null;
    }
}
