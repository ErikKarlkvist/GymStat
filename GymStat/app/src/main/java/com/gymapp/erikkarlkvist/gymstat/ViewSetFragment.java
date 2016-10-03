package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewSetFragment extends Fragment {

    private String setName;
    private TextView viewName;
    private TextView viewWeight;
    private TextView viewReps;
    private TextView viewDate;
    private TextView viewDesc;
    String sNames = "setNames";

    SharedPreferences prefs;

    private Button delete;
    private Button add;
    public ViewSetFragment(){}

    public ViewSetFragment(String setName) {
        this.setName = setName;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.hideFab();
        return inflater.inflate(R.layout.fragment_view_set, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get save data from sharedpreferences
        String sprefs = getResources().getString(R.string.sharedpreferences);
        prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = prefs.getString(setName, "");
        Data data = gson.fromJson(json, Data.class);

        String date = data.getDate(data.size);
        ArrayList<String> dates = data.getDateList();
        generateList(dates,data);

        viewName = (TextView)view.findViewById(R.id.view_set_name);
        viewWeight = (TextView)view.findViewById(R.id.view_set_weight);
        viewReps = (TextView)view.findViewById(R.id.view_set_reps);
        viewDate = (TextView)view.findViewById(R.id.view_set_date);
        viewDesc = (TextView) view.findViewById(R.id.view_set_desc);

        delete = (Button) view.findViewById(R.id.delete_set);
        add = (Button) view.findViewById(R.id.add_to_set_button);

        Resources resources = getResources();
        String weight = resources.getString(R.string.weight2) + " " + data.getWeight(data.size) + "kg";
        String reps = resources.getString(R.string.reps) + " " + data.getRep(data.size);
        String date2 = resources.getString(R.string.date)+ " " + date;
        String desc = data.getDesc();


        viewName.setText(setName);
        viewWeight.setText(weight);
        viewReps.setText(reps);
        viewDate.setText(date2);
        viewDesc.setText(desc);

        delete.setOnClickListener(deleteListener);
        add.setOnClickListener(addListener);
    }

    private void generateList(ArrayList<String> dates, Data data) {
        ArrayAdapter<String> listAdapter = new WeightListAdapter(this.getContext(), R.layout.set_list_view, dates, data);
        ListView setList = (ListView) getView().findViewById(R.id.view_weight_list);
        setList.setAdapter(listAdapter);
    }

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(setName);

            Set<String> setNames = prefs.getStringSet(sNames, null);
            setNames.remove(setName);
            editor.putStringSet(sNames, setNames);
            editor.commit();
            MainFragment mainFragment = new MainFragment();
            changeFragment(mainFragment);
        }
    };

    View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeFragment(new AddToSetFragment(setName));
        }
    };

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
