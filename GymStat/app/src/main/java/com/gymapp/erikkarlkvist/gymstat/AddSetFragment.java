package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSetFragment extends Fragment {


    public AddSetFragment() {
        // Required empty public constructor
    }

    private TextView excersise;
    private TextView weight;
    private TextView name;
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
        excersise = (TextView) getView().findViewById(R.id.excersise_text);
        weight = (TextView) getView().findViewById(R.id.weight_text);
        name = (TextView) getView().findViewById(R.id.set_name_text);
        add.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ex = excersise.getText().toString();
            String wgt = (weight.getText().toString());
            String setName = name.getText().toString();

            addToPrefs(setName, ex, wgt);
            changeFragment(new MainFragment());

        }
    };

    private void addToPrefs(String setName, String exercise, String weight) {
        String sprefs = getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);
        Set<String> setNames = prefs.getStringSet(sNames, null); // can't be null
        setNames.add(setName);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(sNames, setNames);

        editor.putString(setName+"e", exercise);
        editor.putString(setName+"w", weight);
        editor.commit();

    }

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
