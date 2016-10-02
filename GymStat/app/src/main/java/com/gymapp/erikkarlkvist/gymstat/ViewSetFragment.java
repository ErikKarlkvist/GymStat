package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        viewName = (TextView)view.findViewById(R.id.view_set_name);
        viewWeight = (TextView)view.findViewById(R.id.view_set_weight);
        viewReps = (TextView)view.findViewById(R.id.view_set_reps);
        viewDate = (TextView)view.findViewById(R.id.view_set_date);
        viewDesc = (TextView) view.findViewById(R.id.view_set_desc);

        delete = (Button) view.findViewById(R.id.delete_set);

        //get save data from sharedpreferences
        String sprefs = getResources().getString(R.string.sharedpreferences);
        prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);

        Resources resources = getResources();
        String weight = resources.getString(R.string.weight2) + " " + prefs.getString(setName+"w", "N/A") + "kg";
        String reps = resources.getString(R.string.reps) + " "
                    + prefs.getString(setName+"r", "N/A") +"x" + prefs.getString(setName+"r2", "N/A");
        String date = resources.getString(R.string.date)+ " " + prefs.getString(setName+"d", "N/A");
        String desc = prefs.getString(setName+"desc", "No description");


        viewName.setText(setName);
        viewWeight.setText(weight);
        viewReps.setText(reps);
        viewDate.setText(date);
        viewDesc.setText(desc);

        delete.setOnClickListener(deleteListener);
    }

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(setName+"w");
            editor.remove(setName+"d");
            editor.remove(setName+"r");
            editor.remove(setName+"r2");
            editor.remove(setName+"desc");

            Set<String> setNames = prefs.getStringSet(sNames, null);
            setNames.remove(setName);
            editor.putStringSet(sNames, setNames);

            MainFragment mainFragment = new MainFragment();
            changeFragment(mainFragment);

        }
    };

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
