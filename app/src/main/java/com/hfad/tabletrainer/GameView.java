package com.hfad.tabletrainer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

	Game game;
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
		game.setWogy(h-150);
		canvas.drawBitmap(game.getWagBitmap(), game.getWogx(),game.getWogy(), paint);
		//canvas.drawColor(Color.BLACK);
		//update the size for the canvas to the game.
		/*game.setSize(h,w);


		//draw the pacman

		int x,y;
		// loop through the list of goldcoins and draw them.
		coins = game.getCoins();
		for (int counter = 0; counter < coins.size(); counter++) {
			coin = coins.get(counter);
			if(coin.isTaken()==false)
			{
				x=coin.getGoldx();
				if(x>(w-game.getPacBitmap().getWidth()))
				{
					x=x/2;
					coin.setGoldx(x);
				}
				y = coin.getGoldy();
				if(y>(h-game.getPacBitmap().getHeight()))//test
				{

					y=y/2;
                    coin.setGoldy(y);
				}

				canvas.drawBitmap(coin.getGoldBitmap(), x, y, paint);
			}

		}

		enemies = game.getEnemies();
		for (int counter = 0; counter < enemies.size(); counter++) {
			enemy = enemies.get(counter);

			x=enemy.getEnemx();
			if(x>(w-game.getPacBitmap().getWidth()))
			{
				x=x/2;
				enemy.setEnemx(x);
			}
			y = enemy.getEnemy();
			if(y>(h-game.getPacBitmap().getHeight()))//test
			{

				y=y/2;
				enemy.setEnemy(y);
			}

			canvas.drawBitmap(enemy.getEnemBitmap(), x, y, paint);



*/
		super.onDraw(canvas);


	}

}
