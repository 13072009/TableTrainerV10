package com.hfad.tabletrainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Game
{
    private Context context;
    private int wogx, wogy;
    private Bitmap wagBitmap;
    private GameView gameView;
    private int h,w; //height and width of screen


    public Game(Context context) {
        this.context = context;
        wagBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vogen);

    }
    public void setSize(int h, int w)
    {
        this.h = h;
        this.w = w;
    }

    public void setGameView(GameView view)
    {
        this.gameView = view;
    }
    public int getWogx() {
        return wogx;
    }

    public void setWogx(int wogx) {
        this.wogx = wogx;
    }

    public Bitmap getWagBitmap() {
        return wagBitmap;
    }

    public void setWagBitmap(Bitmap enemBitmap) {
        this.wagBitmap = enemBitmap;
    }

    public int getWogy() {
        return wogy;
    }

    public void setWogy(int wogy) {
        this.wogy = wogy;
    }

    public void newGame()////test ne game
    {
        wogx = 10;

        gameView.invalidate(); //redraw screen
    }

    public void movePacmanRight(int pixels)
    {
        if (wogx+pixels+wagBitmap.getWidth()<w+10) {
            wogx = wogx + pixels;
            gameView.invalidate();
        }
    }
    public void movePacmanLeft(int pixels)
    {
        if ((wogx+10)>pixels) {
            wogx = wogx - pixels;
            gameView.invalidate();
        }
    }

}
