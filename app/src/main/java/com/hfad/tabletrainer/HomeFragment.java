package com.hfad.tabletrainer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView textViewTime;
    private Toolbar toolbar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_home, container, false);
        final Integer[] minuterArr = { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
        final Integer[] tableArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        textViewTime =  this.getActivity().findViewById(R.id.timerView);
        //The spinner is defined in our xml file
        Spinner spinnerTable = (Spinner) v.findViewById(R.id.spinnerTable);
        Spinner spinnerTime = (Spinner) v.findViewById(R.id.spinnerTimer);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = v.findViewById(R.id.toolbar);

        //we use a predefined simple spinner drop down,
        //you could define your own layout, so that for instance
        //there was pictures in the drop down list.
        ArrayAdapter<Integer> adapterMinuter = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, minuterArr);

        adapterMinuter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTime.setAdapter(adapterMinuter);
        spinnerTime.setSelection(MainActivity.lastTimSel);
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //The AdapterView<?> type means that this can be any type,
            //so we can use both AdapterView<String> or any other
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                if(MainActivity.time.isRun()==false) {
                    String minStr;
                    MainActivity.time.setMinutes(minuterArr[position] - 1);
                    MainActivity.time.setSeconds(59);
                    if (minuterArr[position] >= 10) {
                        minStr = "" + minuterArr[position];
                    } else {
                        minStr = "0" + minuterArr[position];
                    }
                    textViewTime.setText(minStr + ":00");
                    MainActivity.lastTimSel = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //here is another way of initializing a spinner - getting the resources
        //from the xml values file
        ArrayAdapter<Integer> adapterTable = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, tableArr);
        spinnerTable.setAdapter(adapterTable);
        spinnerTable.setSelection(MainActivity.lastTabSel);
        spinnerTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.table.setTable(tableArr[position], false);
                MainActivity.lastTabSel = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button buttonLern = (Button) v.findViewById(R.id.Lern);
        buttonLern.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                String title= "";
                switch ( MainActivity.table.getTable()) {
                    case 1:
                        fragment = new Table1Fragment();
                        title = getResources().getString(R.string.tabel_1);
                        MainActivity.table.setTable(1, false);
                        break;
                    case 2:
                        fragment = new Table2Fragment();
                        title = getResources().getString(R.string.tabel_2);
                        MainActivity.table.setTable(2, false);
                        break;
                    case 3:
                        fragment = new Table3Fragment();
                        title = getResources().getString(R.string.tabel_3);
                        MainActivity.table.setTable(3, false);
                        break;
                    case 4:
                        fragment = new Table4Fragment();
                        title = getResources().getString(R.string.tabel_4);
                        MainActivity.table.setTable(4, false);
                        break;
                    case 5:
                        fragment = new Table5Fragment();
                        title = getResources().getString(R.string.tabel_5);
                        MainActivity.table.setTable(5, false);
                        break;
                    case 6:
                        fragment = new Table6Fragment();
                        title = getResources().getString(R.string.tabel_6);
                        MainActivity.table.setTable(6, false);
                        break;
                    case 7:
                        fragment = new Table7Fragment();
                        title = getResources().getString(R.string.tabel_7);
                        MainActivity.table.setTable(7, false);
                        break;
                    case 8:
                        fragment = new Table8Fragment();
                        title = getResources().getString(R.string.tabel_8);
                        MainActivity.table.setTable(8, false);
                        break;
                    case 9:
                        fragment = new Table9Fragment();
                        title = getResources().getString(R.string.tabel_9);
                        MainActivity.table.setTable(9, false);
                        break;
                    case 10:
                        fragment = new Table10Fragment();
                        title = getResources().getString(R.string.tabel_10);
                        MainActivity.table.setTable(10, false);
                        break;

                } //after switch
                if (fragment != null) {
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
                }
            }
        });

        Button buttonTrainer = (Button) v.findViewById(R.id.Train);
        buttonTrainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                String title= "";
                fragment = new TrainerHomeFragment();
                title = getResources().getString(R.string.tr_n);

                if (fragment != null) {
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
                }
            }
        });

        Button buttonStart = (Button) v.findViewById(R.id.Start);
        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.time.setRun(true);
            }
        });
        Button buttonStop = (Button) v.findViewById(R.id.Stop);
        buttonStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.time.setRun(false);
            }
        });

        return v;

    }

}
