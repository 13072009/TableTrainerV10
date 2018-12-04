package com.hfad.tabletrainer;

import android.graphics.Bitmap;

public class Ball {

    private int ballX, ballY;
    private Bitmap ballBitmap;
    private boolean taken;


    public Ball(int ballX, int ballY, Bitmap ballBitmap, boolean taken) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballBitmap = ballBitmap;
        this.taken = taken;
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }







}
