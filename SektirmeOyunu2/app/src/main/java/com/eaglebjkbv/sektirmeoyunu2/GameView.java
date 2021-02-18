package com.eaglebjkbv.sektirmeoyunu2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class GameView extends SurfaceView implements Runnable {

    public enum Yon {
        ASAGI, YUKARI, SAGA, SOLA
    }

    Thread t = null;
    SurfaceHolder surfaceHolder;
    boolean isItOk = false;
    Bitmap top, saha, sahaStreched, soundOn, soundOff, soundOnScaled, sounOffScaled;
    float topX, topY;
    int adimMiktari = 8;
    int topYatayHiz = 0;
    Context context;
    Yon topYon = Yon.ASAGI;
    float topDonmeAcisi = 0;
    float aciArtisi = 3;
    Matrix src, dst;
    Rect rect;
    boolean oneTime = false;
    boolean isSoundOn = false;
    int sayac = 0;
    int adimLimit = 22;
    int skor = 0, enYuksekSkor = 0;
    Paint paint = new Paint();
    final MediaPlayer sound;

    public GameView(Context context) {
        super(context);
        this.context = context;
        top = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        saha = BitmapFactory.decodeResource(getResources(), R.drawable.saha_mobile);
        soundOn = BitmapFactory.decodeResource(getResources(), R.drawable.soundon);
        soundOff = BitmapFactory.decodeResource(getResources(), R.drawable.soundoff);
        sound = MediaPlayer.create(context, R.raw.seyircikisa);
        // sound.start();
        // sound.pause();

        topX = topY = 0;
        surfaceHolder = getHolder();

        // surfaceView de herhangi bir noktaya dokunulduğunda çalışıyor.
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // topa vurulunca ....
                if (event.getX() > topX && event.getX() < topX + top.getWidth() && event.getY() > topY
                        && event.getY() < topY + top.getHeight()) {
                    if (adimMiktari > 0)
                        adimMiktari = -adimLimit;

                    topYatayHiz = -Math.round(event.getX() - (topX + top.getWidth() / 2)) / 10;
                    aciArtisi = -Math.round(event.getX() - (topX + top.getWidth() / 2)) / 20;

                }
                // ses kapat aça dokunulursa....
                if (event.getX() > getWidth() - soundOff.getWidth() - 5 && event.getX() < getWidth() - 5
                        && event.getY() > 5 && event.getY() < soundOff.getHeight() + 5) {
                    isSoundOn = !isSoundOn; // ses açıksa kapat kapalıysa aç.
                }
                return false;
            }
        });

    }

    // run fonksiyonu saniyede yaklaşık 60 defa çalışıyor. 60FPS
    // run fonsiyonunun çalışması için thread start edilmiş olmalı
    // thread resume fonksiyonu içinde başlatılıyor....
    @Override
    public void run() {
        while (isItOk) {
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }

            Canvas c = surfaceHolder.lockCanvas();
            calculate();// Hesaplamalar burada yapılıyor. Mesala topun yukarı giderkenki hızı...
            drawShapes(c); // Şekiller hseaplana datalara göre burada çizdiriliyor...

            surfaceHolder.unlockCanvasAndPost(c);
        }
    }

    private void calculate() {

        topDonmeAcisi += aciArtisi;
        if (topDonmeAcisi > 360)
            topDonmeAcisi = 0;

        if (topY < 0 || topY > getHeight() - top.getHeight())
            adimMiktari = -adimMiktari;

        if (topY > getHeight() - top.getHeight()) {
            if (skor > enYuksekSkor)
                enYuksekSkor = skor;
            skor = 0;
        }
        sayac++;
        if (sayac % 10 == 0) {
            adimMiktari += 3;
        }
        if (sayac == 100) {
            sayac = 0;
        }
        if (sayac % 50 == 0) {
            skor++;
        }

        if (adimMiktari < -adimLimit)
            adimMiktari = -adimLimit;
        if (adimMiktari > +adimLimit)
            adimMiktari = adimLimit;

        topY += adimMiktari;

        topX = topX + topYatayHiz;
        if (topX < 0 || topX > this.getWidth() - top.getWidth())
            topYatayHiz = -topYatayHiz;

    }

    private void drawShapes(Canvas c) {

        if (!oneTime) { // bir kereliğine resimler ekran boyutuna göre boyutlandırılıyor.
            sahaStreched = Bitmap.createScaledBitmap(saha, getWidth(), getHeight(), false);
            sounOffScaled = Bitmap.createScaledBitmap(soundOff, getWidth() / 100, getHeight(), false);
            soundOnScaled = Bitmap.createScaledBitmap(soundOn, getWidth(), getHeight(), false);

            oneTime = true;
        }

        c.drawARGB(255, 255, 0, 0);
        c.drawBitmap(sahaStreched, 0, 0, null);

        c.save();
        // c.translate(topX+200,topY);
        c.rotate(topDonmeAcisi, topX + top.getWidth() / 2, topY + top.getHeight() / 2);
        c.drawBitmap(top, topX, topY, null);
        c.restore();

        paint.setAlpha(80);
        if (!isSoundOn) {
            c.drawBitmap(soundOff, getWidth() - soundOff.getWidth() - 5, 5, paint);
            if (sound.isPlaying())
                sound.pause();
        } else {
            c.drawBitmap(soundOn, getWidth() - soundOff.getWidth() - 5, 5, paint);
            if (!sound.isPlaying())
                sound.start();
        }
        paint.setARGB(100, 255, 0, 0);
        paint.setStyle(Paint.Style.FILL);
        c.drawRect(10, 10, 200, 150, paint);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(255, 255, 255, 255);
        c.drawRect(10, 10, 200, 150, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText("E.Y.S :" + Integer.toString(enYuksekSkor), 100, 60, paint);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText(Integer.toString(skor), 100, 120, paint);

    }

    // Bu foksiyonları kendimiz oluşturuyoruz.
    public void pause() {
        isItOk = false;
        while (true) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        t = null;
        sound.pause();
    }

    // Bu foksiyonları kendimiz oluşturuyoruz.
    public void resume() {
        isItOk = true;
        t = new Thread(this);
        t.start();
    }
}
