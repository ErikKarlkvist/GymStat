package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddToSetFragment extends Fragment {

    private String setName;
    public AddToSetFragment() {
        // Required empty public constructor
    }

    public AddToSetFragment(String setName) {
        this.setName = setName;
    }

    private EditText weight;
    private EditText rep1;
    private EditText rep2;
    private Data data;
    private SharedPreferences prefs;
    private RadioButton easy, medium, hard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_set, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String sprefs = getResources().getString(R.string.sharedpreferences);
        prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);
        System.out.println("kek");

        Gson gson = new Gson();
        String json = prefs.getString(setName, "");
        data = gson.fromJson(json, Data.class);

        Button done = (Button) view.findViewById(R.id.add_to_set_done);
        done.setOnClickListener(doneListener);

        weight = (EditText) view.findViewById(R.id.add_to_set_weight);
        rep1 = (EditText)view.findViewById(R.id.add_to_set_rep1);
        rep2 = (EditText)view.findViewById(R.id.add_to_set_rep2);
        easy = (RadioButton)view.findViewById(R.id.easy);
        medium = (RadioButton)view.findViewById(R.id.medium);
        hard = (RadioButton)view.findViewById(R.id.hard);

        easy.setOnClickListener(onClickEasy);
        medium.setOnClickListener(onClickMedium);
        hard.setOnClickListener(onClickHard);

    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //hides the keyboard
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);


            double newWeight = Double.parseDouble(weight.getText().toString());
            String newRep = (rep1.getText()).toString()
                    + "x" + (rep2.getText()).toString();

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            String sdate = sdf.format(date);

            String desc = data.getDesc();

            String diff;
            if(easy.isChecked()) {
                diff = "easy";
            } else if (medium.isChecked()){
                diff = "medium";
            } else {
                diff = "hard";
            }

            data.addData(sdate, newWeight, newRep, desc, diff);

            Gson gson = new Gson();
            String json = gson.toJson(data);
            prefs.edit().putString(setName, json).commit();
            changeFragment(new ViewSetFragment(setName));
        }
    };

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
