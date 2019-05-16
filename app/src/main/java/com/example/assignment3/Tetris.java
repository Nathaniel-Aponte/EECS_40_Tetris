package com.example.assignment3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Tetris extends AppCompatActivity implements View.OnTouchListener {
    TetrisView view;
    Bitmap square;
    float[] gameboardx = new float[10];
    float[] gameboardy = new float[16];
    float[] gameboardv = new float[160];
    float x = 0;
    float y = 0;
    final float gridW = 108;
    final float gridH = 99;
    Shapes shape = new Shapes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        view = new TetrisView(this);
        view.setOnTouchListener(this);
        square = BitmapFactory.decodeResource(getResources(), R.drawable.yellowblock);
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.resume();
    }


    public class TetrisView extends SurfaceView implements Runnable {
        //float x = 0;
        //float y = 0;
        //static Bitmap square;
        Thread game = null;
        SurfaceHolder holder;
        boolean running = false;

        public TetrisView(Context context) {
            super(context);
            holder = getHolder();
        }


        @Override
        public void run() {
            for (int k = 0; k < 10; k++) {
                gameboardx[k] = gridW * k;
                System.out.println(gameboardx[k]);

            }
            for (int k = 0; k < 16; k++) {
                gameboardy[k] = gridH * k;
                System.out.println(gameboardy[k]);

            }

            while (running == true) {
                //draw shapes
                if (!holder.getSurface().isValid()) {
                    continue;
                }

                Canvas grid = holder.lockCanvas();
                //System.out.println(grid.getHeight());
                //System.out.println(grid.getWidth());
                grid.drawARGB(255, 150, 150, 150);
                shape.OShape();
                grid.drawBitmap(square, shape.x, shape.y, null);
                /*grid.drawBitmap(square, gameboardx[0],y + gameboardy[1], null);
                grid.drawBitmap(square, gameboardx[1],y + gameboardy[0], null);
                grid.drawBitmap(square, gameboardx[1],y + gameboardy[1], null); */
                // for (int i = 0; i < 2; i++) {
                //   for (int j = 0; j < 2; j++) {
                //grid.drawBitmap(square, x + i * square.getWidth(), y + j * square.getHeight(), null);
                //  }
                //}
                gameboardv[150] = 1;
                /*gameboardv[141] = 1;
                gameboardv[150] = 1;
                gameboardv[151] = 1;*/


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (gameboardv[150] <= 0) {
                    y = y + gridH;

                }
                //else if(y < 1584) {
                //  y = y + gridH;
                //}

                /*if(y >= grid.getHeight() - square.getHeight()){
                    //grid.drawBitmap(square, );

                }*/
                //grid.drawBitmap(square,x,y,null);
                holder.unlockCanvasAndPost(grid);
            }
        }

        public void pause() {
            running = false;
            while (true) {
                try {
                    game.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            game = null;
        }

        public void resume() {
            running = true;
            game = new Thread(this);
            game.start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        /*try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
        }


        return true;
    }

}

