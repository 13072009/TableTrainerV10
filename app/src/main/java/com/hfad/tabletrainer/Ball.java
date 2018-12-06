package com.hfad.tabletrainer;

import android.graphics.Bitmap;

public class Ball {

    private int             ballX, ballY;
    private Bitmap          ballBitmap;

    private boolean         visible;

    public Ball(int ballX, int ballY, Bitmap ballBitmap,  boolean visible) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballBitmap = ballBitmap;
        this.visible = visible;
    }



    public Ball(int ballX, int ballY, Bitmap ballBitmap) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballBitmap = ballBitmap;
    }

    public Ball() {

    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public Bitmap getBallBitmap() {
        return ballBitmap;
    }

    public void setBallBitmap(Bitmap ballBitmap) {
        this.ballBitmap = ballBitmap;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }







}
