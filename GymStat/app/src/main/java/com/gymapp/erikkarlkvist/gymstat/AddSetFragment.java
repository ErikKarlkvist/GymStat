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
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;

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
    private String sNames = "setNames";
    private RadioButton easy, medium, hard;

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


        easy = (RadioButton)view.findViewById(R.id.easy);
        medium = (RadioButton)view.findViewById(R.id.medium);
        hard = (RadioButton)view.findViewById(R.id.hard);

        easy.setOnClickListener(onClickEasy);
        medium.setOnClickListener(onClickMedium);
        hard.setOnClickListener(onClickHard);

     //  CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
      //  p.setAnchorId(View.NO_ID);
       // fab.setLayoutParams(p);
        //fab.setVisibility(View.GONE);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double wgt = Double.parseDouble(weight.getText().toString());
            String setName = name.getText().toString();
            String reps = r.getText().toString();
            String reps2 = r2.getText().toString();
            String description = desc.getText().toString();

            addToPrefs(setName, wgt, reps, reps2, description);
            changeFragment(new MainFragment());

        }
    };

    private void addToPrefs(String setName, double weight, String reps, String reps2, String description) {
        String sprefs = getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);
        Set<String> setNames = prefs.getStringSet(sNames, null); // can't be null
        setNames.add(setName);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(sNames, setNames);

        String diff;
        if(easy.isChecked()) {
            diff = "easy";
        } else if (medium.isChecked()){
            diff = "medium";
        } else {
            diff = "hard";
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String sdate = sdf.format(date);
        Data data;
        if(description != ""){
            data = new Data(sdate, weight, reps + "x" + reps2, description, diff);
        } else {
            data = new Data(sdate, weight, reps + "x" + reps2, "No description", diff);
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(setName, json);

        editor.commit();

    }

    View.OnClickListener onClickHard = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            hard.setChecked(true);
            medium.setChecked(false);
            easy.setChecked(false);
        }
    };

    View.OnClickListener onClickMedium = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            hard.setChecked(false);
            medium.setChecked(true);
            easy.setChecked(false);
        }
    };

    View.OnClickListener onClickEasy = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            hard.setChecked(false);
            medium.setChecked(false);
            easy.setChecked(true);
        }
    };

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
