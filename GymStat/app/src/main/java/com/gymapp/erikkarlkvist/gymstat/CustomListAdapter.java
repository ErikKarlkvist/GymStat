package com.gymapp.erikkarlkvist.gymstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Erik Karlkvist on 2016-09-29.
 */
public class CustomListAdapter extends ArrayAdapter<String>{

    private String sName = "setNames";
    private ArrayList<String> sets;

    public CustomListAdapter(Context context, int layout, ArrayList<String> sets) {

        super(context, R.layout.set_view,sets);
        this.sets = sets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.set_view, parent, false);

        String sprefs = getContext().getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);

        String setName = sets.get(position);

        String ex = prefs.getString(setName+"e","N/A");
        String weigth = prefs.getString(setName+"w","N/A");

        TextView setHead = (TextView) customView.findViewById(R.id.set_header);
        TextView exc = (TextView) customView.findViewById(R.id.set_view_excersise);
        TextView setWeigh = (TextView) customView.findViewById(R.id.set_view_weight);

        setHead.setText(setName);
        exc.setText(getContext().getResources().getString(R.string.excersises) + " " +ex);
        setWeigh.setText(getContext().getResources().getString(R.string.weight) + " " +weigth);
        return customView;
    }


}
