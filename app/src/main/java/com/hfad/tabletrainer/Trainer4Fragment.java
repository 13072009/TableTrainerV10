package com.hfad.tabletrainer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trainer4Fragment extends Fragment {

    //reference to the main view
    GameView gameView;
    //reference to the game class.
    Game game;
    TextView st1;


    public Trainer4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_trainer4, container, false);
        st1 = v.findViewById(R.id.statusTr4);
        gameView =  v.findViewById(R.id.gameView);

        game = new Game(this.getContext());
        game.setGameView(gameView);
        gameView.setGame(game);

        game.newGame();

        return  v;
    }

}
