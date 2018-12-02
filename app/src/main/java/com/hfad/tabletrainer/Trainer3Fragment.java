package com.hfad.tabletrainer;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trainer3Fragment extends Fragment {
    TextView tr;
    TextView st1;
    TextView st2;
    EditText answ;

    public Trainer3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_trainer3, container, false);
        tr = v.findViewById(R.id.Tr3);
        st1 = v.findViewById(R.id.statusTr3);
        st2 = v.findViewById(R.id.statusAnsvTr3);
        answ= v.findViewById(R.id.inputAnswTr3);
        createTask();

        Button buttonContr = (Button) v.findViewById(R.id.buttonContr3);
        buttonContr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String answersStr1=answ.getText()+"";
                String ansversStr2 = MainActivity.table.getTaskAnsw();
                if(answersStr1.equalsIgnoreCase(ansversStr2) )
                {
                    st1.setText(getResources().getString(R.string.korr));
                    st1.setTextColor(Color.GREEN);
                    st2.setText("");
                    MainActivity.table.setPoint(MainActivity.table.getPoint()+1);

                }
                else
                {
                    st1.setText(getResources().getString(R.string.fork));
                    st1.setTextColor(Color.RED);
                    st2.setText(getResources().getString(R.string.korr_sv)+" "+ansversStr2);
                    if(MainActivity.table.getPoint()>0)
                    {
                        MainActivity.table.setPoint(MainActivity.table.getPoint() - 1);
                    }
                }
                MainActivity.textViewPoint.setText(getResources().getString(R.string.point)+MainActivity.table.getPoint());

            }
        });

        Button buttonNext = (Button) v.findViewById(R.id.buttonNext3);
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createTask();
            }
        });

        Button buttonBack = (Button) v.findViewById(R.id.buttonBack3);
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
        return v;
    }


    void createTask() {
        st1.setText("");
        st2.setText("");
        answ.setText("");
        MainActivity.table.createTask(3);
        tr.setText(MainActivity.table.getTask());
        // Inflate the layout for this fragment
    }

}
