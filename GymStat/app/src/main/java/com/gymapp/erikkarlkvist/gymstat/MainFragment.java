package com.gymapp.erikkarlkvist.gymstat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public static int size;
    String sNames = "setNames";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.showFab();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayList<String> setNames = getSetNames();
        ArrayAdapter<String> listAdapter = new CustomListAdapter(this.getContext(), R.layout.set_list_view, setNames);
        ListView setList = (ListView) getView().findViewById(R.id.set_list);
        setList.setAdapter(listAdapter);
        setList.setOnItemClickListener(listListener);
        super.onViewCreated(view, savedInstanceState);
    }

    private AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String sprefs = getResources().getString(R.string.sharedpreferences);
            SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);
            Set<String> setNames = prefs.getStringSet(sNames, null);
            ArrayList<String> listSetNames = new ArrayList<>(setNames);

            ViewSetFragment viewSet = new ViewSetFragment(listSetNames.get(position));
            changeFragment(viewSet);

        }
    };


    private ArrayList<String> getSetNames() {
        String sprefs = getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);

        Set<String> setNames;
        if(prefs.getStringSet(sNames, null) == null){
            setNames = new HashSet<>();
            SharedPreferences.Editor editor = prefs.edit();
            System.out.println("Hello");
            editor.putStringSet(sNames, setNames);
            editor.commit();
        } else {
            setNames = prefs.getStringSet(sNames, null);
        }
        ArrayList<String> aSetNames = new ArrayList<>(setNames);
        return aSetNames;
    }

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
