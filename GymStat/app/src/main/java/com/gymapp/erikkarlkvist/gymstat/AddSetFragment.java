package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSetFragment extends Fragment {


    public AddSetFragment() {
        // Required empty public constructor
    }

    private TextView weight;
    private TextView name;
    private TextView r;
    private TextView r2;
    private TextView desc;
    String sNames = "setNames";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_set, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button add = (Button) getView().findViewById(R.id.add_set);
        weight = (TextView) getView().findViewById(R.id.weight_text);
        name = (TextView) getView().findViewById(R.id.set_name_text);
        r = (TextView) getView().findViewById(R.id.reps_text);
        r2 = (TextView) getView().findViewById(R.id.reps_text2);
        desc = (TextView)getView().findViewById(R.id.set_description);
        add.setOnClickListener(listener);

     //  CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
      //  p.setAnchorId(View.NO_ID);
       // fab.setLayoutParams(p);
        //fab.setVisibility(View.GONE);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String wgt = (weight.getText().toString());
            String setName = name.getText().toString();
            String reps = r.getText().toString();
            String reps2 = r2.getText().toString();
            String description = desc.getText().toString();

            addToPrefs(setName, wgt, reps, reps2, description);
            changeFragment(new MainFragment());

        }
    };

    private void addToPrefs(String setName, String weight, String reps, String reps2, String description) {
        String sprefs = getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);
        Set<String> setNames = prefs.getStringSet(sNames, null); // can't be null
        setNames.add(setName);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(sNames, setNames);

        editor.putString(setName + "w", weight);
        editor.putString(setName + "r", reps);
        editor.putString(setName + "r2", reps2);

        if(description != ""){
            editor.putString(setName + "desc", description);
        } else {
            editor.putString(setName + "desc", "No description");
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String sdate = sdf.format(date);

        editor.putString(setName +"d", sdate);
        editor.commit();

    }

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
