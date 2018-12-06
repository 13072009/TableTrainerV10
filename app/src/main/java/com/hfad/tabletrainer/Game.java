package com.hfad.tabletrainer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Context context;
    private TextView trView, statusView;
    private int wagx, wagy;
    private Bitmap wagBitmap, ballBitmap;
    private GameView gameView;
    private int h, w; //height and width of screen
    private Ball ball, nextBall;


    Random rand = new Random();


    private ArrayList<Ball> balls = new ArrayList<>();


    public Game(Context context, TextView trView, TextView statusView) {
        this.context = context;
        this.trView = trView;
        this.statusView = statusView;
        wagBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vogen);

    }

    public void setSize(int h, int w) {
        this.h = h;
        this.w = w;
    }

    public void setGameView(GameView view) {
        this.gameView = view;
    }

    public int getWagx() {
        return wagx;
    }

    public void setWagx(int wogx) {
        this.wagx = wogx;
    }

    public Bitmap getWagBitmap() {
        return wagBitmap;
    }

    public void setWagBitmap(Bitmap enemBitmap) {
        this.wagBitmap = enemBitmap;
    }

    public int getWagy() {
        return wagy;
    }

    public void setWagy(int wogy) {
        this.wagy = wogy;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public void newGame()////test ne game
    {
        wagx = 100;
        createBalls();
        gameView.invalidate(); //redraw screen
    }

    public void moveWagRight(int pixels) {
        if (wagx + pixels + wagBitmap.getWidth() < w + 10) {
            wagx = wagx + pixels;
            gameView.invalidate();
        }
    }

    public void moveWagLeft(int pixels) {
        if ((wagx + 10) > pixels) {
            wagx = wagx - pixels;
            gameView.invalidate();
        }
    }

    public void createBalls() {

        String textNum = "";
        int x = 0, y = 10;
        MainActivity.table.createTask(4);

        for (int counter = 0; counter < MainActivity.table.getAnswers().size(); counter++) {
            //Opretter en billede med en tal
            ballBitmap = createImage(125, 125, Color.RED, "" + MainActivity.table.getAnswers().get(counter));

            // Laver billede rundt
            ballBitmap = getCircularBitmap(ballBitmap);

            // Tilføjer en circul rund om
            ballBitmap = addBorderToCircularBitmap(ballBitmap, 15, Color.BLUE);

            //Tilføjer en kant rundt om
            ballBitmap = addShadowToCircularBitmap(ballBitmap, 4, Color.BLACK);

            if (counter == 0) {
                ball = new Ball(200, y, ballBitmap, true);
            } else {
                x = rand.nextInt(600) + 150;
                ball = new Ball(x, y, ballBitmap, false);

            }

            balls.add(ball);
        }


    }


    public Bitmap createImage(int width, int height, int color, String text) {


        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Paint paint2 = new Paint();

        paint2.setColor(color);

        canvas.drawRect(0F, 0F, (float) width, (float) height, paint2);

        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        paint.setTextScaleX(1);
        paint.setTextAlign(Paint.Align.CENTER);

        float textY = height / 2 + 25;
        float textX = width / 2;
        canvas.drawText(text, textX, textY, paint);

        return bitmap;
    }


    ///////////////////////////////
    protected Bitmap getCircularBitmap(Bitmap srcBitmap) {
        // Calculate the circular bitmap width with border
        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());

        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                squareBitmapWidth, // Width
                squareBitmapWidth, // Height
                Bitmap.Config.ARGB_8888 // Config
        );


        // Initialize a new Canvas to draw circular bitmap
        Canvas canvas = new Canvas(dstBitmap);

        // Initialize a new Paint instance
        Paint paint = new Paint();
        paint.setAntiAlias(true);


        // Initialize a new Rect instance
        Rect rect = new Rect(0, 0, squareBitmapWidth, squareBitmapWidth);

        // Initialize a new RectF instance
        RectF rectF = new RectF(rect);

        // Draw an oval shape on Canvas
        canvas.drawOval(rectF, paint);


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // Calculate the left and top of copied bitmap
        float left = (squareBitmapWidth - srcBitmap.getWidth()) / 2;
        float top = (squareBitmapWidth - srcBitmap.getHeight()) / 2;


        // Make a rounded image by copying at the exact center position of source image
        canvas.drawBitmap(srcBitmap, left, top, paint);

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the circular bitmap
        return dstBitmap;
    }

    // Custom method to add a border around circular bitmap
    protected Bitmap addBorderToCircularBitmap(Bitmap srcBitmap, int borderWidth, int borderColor) {
        // Calculate the circular bitmap width with border
        int dstBitmapWidth = srcBitmap.getWidth() + borderWidth * 2;

        // Initialize a new Bitmap to make it bordered circular bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);
        // Draw source bitmap to canvas
        canvas.drawBitmap(srcBitmap, borderWidth, borderWidth, null);

        // Initialize a new Paint instance to draw border
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        paint.setAntiAlias(true);

        // Draw the circular border around circular bitmap
        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getWidth() / 2, // cy
                canvas.getWidth() / 2 - borderWidth / 2, // Radius
                paint // Paint
        );

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the bordered circular bitmap
        return dstBitmap;
    }

    // Custom method to add a shadow around circular bitmap
    protected Bitmap addShadowToCircularBitmap(Bitmap srcBitmap, int shadowWidth, int shadowColor) {
        // Calculate the circular bitmap width with shadow
        int dstBitmapWidth = srcBitmap.getWidth() + shadowWidth * 2;
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawBitmap(srcBitmap, shadowWidth, shadowWidth, null);

        // Paint to draw circular bitmap shadow
        Paint paint = new Paint();
        paint.setColor(shadowColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(shadowWidth);
        paint.setAntiAlias(true);

        // Draw the shadow around circular bitmap
        canvas.drawCircle(
                dstBitmapWidth / 2, // cx
                dstBitmapWidth / 2, // cy
                dstBitmapWidth / 2 - shadowWidth / 2, // Radius
                paint // Paint
        );


        srcBitmap.recycle();

        // Return the circular bitmap with shadow
        return dstBitmap;
    }


    ////////////////////

    public void moveBalls() {
        int pixels = 10;
        for (int counter = 0; counter < balls.size(); counter++) {
            ball = balls.get(counter);

            if (ball.isVisible() == true) {
                ball.setBallY(ball.getBallY() + pixels);

            }
            if (ball.getBallY() > (h / 2)) {

            }
            if (ball.getBallY() > h) {
                if (counter < 9) {
                    nextBall = balls.get(counter + 1);
                } else {
                    nextBall = balls.get(0);
                }
                nextBall.setVisible(true);

                ball.setBallY(10);
                ball.setVisible(false);
            }

            doCollisionCheck();
            gameView.invalidate();

        }
    }


    private int distance(int ax, int ay, int bx, int by) {
        int dx   = ax-bx;//coin.getGoldx() - pacx;
        int dy   = ay-by;//coin.getGoldy() - pacy;
        int d = (int) Math.sqrt( dx*dx + dy*dy );

        return d;
    }



    public void doCollisionCheck() {


        for (int counter = 0; counter < balls.size(); counter++) {
            ball = balls.get(counter);

            if (distance(ball.getBallX(), ball.getBallY(), wagx, wagy) <= wagBitmap.getHeight()  && ball.isVisible() == true) {


                String answersStr1=MainActivity.table.getTaskAnsw();
                String ansversStr2 = ""+MainActivity.table.getAnswers().get(counter);
                if(answersStr1.equalsIgnoreCase(ansversStr2) )
                {
                    statusView.setText(context.getResources().getString(R.string.korr));
                    statusView.setTextColor(Color.GREEN);

                    MainActivity.table.setPoint(MainActivity.table.getPoint()+1);
                }
                else
                {
                    statusView.setText(context.getResources().getString(R.string.fork));
                    statusView.setTextColor(Color.RED);
                    if(MainActivity.table.getPoint()>0)
                    {
                        MainActivity.table.setPoint(MainActivity.table.getPoint() - 1);
                    }
                }
                balls.clear();
                createBalls();
                trView.setText(MainActivity.table.getTask());
                MainActivity.textViewPoint.setText(context.getResources().getString(R.string.point)+MainActivity.table.getPoint());
            }

        }
    }


}
