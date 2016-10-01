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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayList<String> setNames = getSetNames();
        ArrayAdapter<String> listAdapter = new CustomListAdapter(this.getContext(), R.layout.set_view, setNames);
        ListView setList = (ListView) getView().findViewById(R.id.set_list);
        setList.setAdapter(listAdapter);
        System.out.println(listAdapter.getCount());
        setList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String set = String.valueOf(parent.getItemAtPosition(position));
                //Toast.makeText(MainFragment.this, set, Toast.LENGTH_LONG).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


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
}
