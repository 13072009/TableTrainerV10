package com.hfad.tabletrainer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {

	Game game;
	private ArrayList<Ball> balls = new ArrayList<>();
	private Ball ball;
    int h,w, counter; //used for storing our height and width of the view


	public void setGame(Game game)
	{
		this.game = game;
	}


	/* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
	public GameView(Context context) {
		super(context);

	}

	public GameView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}


	public GameView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context,attrs,defStyleAttr);
	}


	//In the onDraw we put all our code that should be
	//drawn whenever we update the screen.
	@Override
	protected void onDraw(Canvas canvas) {
		//Here we get the height and weight
		h = canvas.getHeight();
		w = canvas.getWidth();
		game.setSize(h,w);

		Paint paint = new Paint();
		game.setWagy(h-150);
		canvas.drawBitmap(game.getWagBitmap(), game.getWagx(),game.getWagy(), paint);

		int x,y;
		balls = game.getBalls();
		for (int counter = 0; counter < balls.size(); counter++) {
			ball = balls.get(counter);
			if(ball.isVisible()==true)
			{
				x=ball.getBallX();
				y = ball.getBallY();
				canvas.drawBitmap(ball.getBallBitmap(), x, y, paint);
			}

		}


	}

}
