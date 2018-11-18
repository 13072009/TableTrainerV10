package com.hfad.tabletrainer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainerHomeFragment extends Fragment {


    public TrainerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trainer_home, container, false);
        final String[] tableArr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "MIX"};
        Spinner spinnerTable = (Spinner) v.findViewById(R.id.spinnerTableTr);

        ArrayAdapter<String> adapterTable = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tableArr);
        spinnerTable.setAdapter(adapterTable);
        spinnerTable.setSelection(MainActivity.lastTabSel);
        spinnerTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.table.setTable(position);
                MainActivity.table.createAnswers();
                MainActivity.lastTabSel = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }


}
