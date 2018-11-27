package com.hfad.tabletrainer;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trainer2Fragment extends Fragment  {


    TextView tr;
    TextView st1;
    TextView st2;
    ListView lstItems;

    public Trainer2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_trainer2, container, false);
        tr = v.findViewById(R.id.Tr2);
        st1 = v.findViewById(R.id.statusTr2);
        st2 = v.findViewById(R.id.statusAnsvTr2);
        lstItems = v.findViewById(R.id.listAnsw);
        createTask();


        Button buttonNext = (Button) v.findViewById(R.id.buttonNextTr2);
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createTask();
                st1.setText("");
                st2.setText("");
            }
        });
        Button buttonBack = (Button) v.findViewById(R.id.buttonBackTr2);
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

    void createTask()
    {

        MainActivity.table.createTask(2);
        tr.setText(MainActivity.table.getTask());
        // Inflate the layout for this fragment


        ArrayList<String> choose = new ArrayList<String>();

        choose = MainActivity.table.getChoose();

        ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_checked,choose);

        lstItems.setAdapter(allItemsAdapter);
        lstItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               /* String answersStr1=answ1.getText()+"; "+answ2.getText()+"; "+answ3.getText();
                String ansversStr2 = MainActivity.table.getTaskAnsw();
                if(answersStr1.equalsIgnoreCase(ansversStr2) )
                {
                    st1.setText("Korrekt");
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
                MainActivity.textViewPoint.setText("Point: "+MainActivity.table.getPoint());*/

            }
        });
    }

}
