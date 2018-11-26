package com.hfad.tabletrainer;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trainer1Fragment extends Fragment {


    public Trainer1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_trainer1, container, false);
        final TextView tr1 = v.findViewById(R.id.Tr1);
        final TextView st1 = v.findViewById(R.id.statusTr1);
        final TextView st2 = v.findViewById(R.id.statusAnsvTr1);

        final EditText answ1 = v.findViewById(R.id.inputAnsw1);
        final EditText answ2 = v.findViewById(R.id.inputAnsw2);
        final EditText answ3 = v.findViewById(R.id.inputAnsw3);
        MainActivity.table.createTask();
        tr1.setText(MainActivity.table.getTask());

        Button buttonContr = (Button) v.findViewById(R.id.buttonContr);
        buttonContr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String answersStr1=answ1.getText()+"; "+answ2.getText()+"; "+answ3.getText();
                String ansversStr2 = MainActivity.table.getTaskAnsw();
                if(answersStr1.equalsIgnoreCase(ansversStr2) )
                {
                    st1.setText("Korekt");
                    st1.setTextColor(Color.GREEN);
                    MainActivity.table.setPoint(MainActivity.table.getPoint()+1);
                    MainActivity.textViewPoint.setText("Point: "+MainActivity.table.getPoint());
                }
                else
                {
                    st1.setText("Forkert");
                    st1.setTextColor(Color.RED);
                    st2.setText("Rigtig svar er: "+ansversStr2);
                    if(MainActivity.table.getPoint()>0)
                    {
                        MainActivity.table.setPoint(MainActivity.table.getPoint() - 1);
                    }
                }
                MainActivity.textViewPoint.setText("Point: "+MainActivity.table.getPoint());

            }
        });
        Button buttonNext = (Button) v.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.table.createTask();
                tr1.setText(MainActivity.table.getTask());
                answ1.setText("");
                answ2.setText("");
                answ3.setText("");
                st1.setText("");
                st2.setText("");
            }
        });
        Button buttonBack = (Button) v.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                String title= "";
                fragment = new TrainerHomeFragment();
                title = getResources().getString(R.string.tr_n);

                if (fragment != null) {
                    st1.setText("");
                    st2.setText("");
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
                }
            }
        });

        // Inflate the layout for this fragment
        return v;
    }


}
