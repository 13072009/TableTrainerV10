package com.hfad.tabletrainer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trainer4Fragment extends Fragment {

    //reference to the main view
    GameView gameView;
    //reference to the game class.
    Game game;
    TextView st1;
    TextView tr;


    private int direction = 0;
    private boolean running = false;
    private Timer wagTimer;


    public Trainer4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_trainer4, container, false);
        st1 = v.findViewById(R.id.statusTr4);
        tr = v.findViewById(R.id.Tr4);

        gameView =  v.findViewById(R.id.gameView);

        game = new Game(this.getContext());
        game.setGameView(gameView);
        gameView.setGame(game);

        game.newGame();
        createTask();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonLeft = (Button) v.findViewById(R.id.buttonLeftTr4);
        buttonLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                direction = 1;
                running = true;
            }
        });

        Button buttonRight = (Button) v.findViewById(R.id.buttonRightTr4);
        buttonRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                direction = 2;
                running = true;
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonStart = (Button) v.findViewById(R.id.buttonStartTr4);
        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                running = true;
            }
        });
        Button buttonStop = (Button) v.findViewById(R.id.buttonStopTr4);
        buttonStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                running = false;
            }
        });
        Button buttonBack = (Button) v.findViewById(R.id.buttonBackTr4);
        buttonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                String title= "";
                fragment = new TrainerHomeFragment();
                title = getResources().getString(R.string.tr_n);
                running = false;
                wagTimer.cancel();

                if (fragment != null) {
                    st1.setText("");
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
                }
            }
        });
////////////////////////////////////////////////////////////////////

        newTime();
        return  v;
    }
    @Override
    public void onStop() {
        super.onStop();
        //just to make sure if the app is killed, that we stop the timer.
        wagTimer.cancel();
    }

    void createTask() {
        st1.setText("");
        MainActivity.table.createTask(4);
        tr.setText(MainActivity.table.getTask());

    }

    private void WagTimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(WagTimer_Tick);

    }
    private Runnable WagTimer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.
            // so we can draw
            if (running)
            {
                if(direction == 1)
                {
                    game.moveWagLeft(20);
                }
                else if( direction == 2) {
                    game.moveWagRight(20);

                }
            }
        }
    };

    private void newTime()
    {
        //make a new timer
        wagTimer = new Timer();

        wagTimer.schedule(new TimerTask() {
            //   @Override
            public void run() {
                WagTimerMethod();
            }

        }, 0, 100);
    }

}
