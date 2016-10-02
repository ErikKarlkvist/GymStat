package com.gymapp.erikkarlkvist.gymstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Erik Karlkvist on 2016-09-29.
 */
public class CustomListAdapter extends ArrayAdapter<String>{

    private String sName = "setNames";
    private ArrayList<String> sets;

    public CustomListAdapter(Context context, int layout, ArrayList<String> sets) {

        super(context, R.layout.set_list_view,sets);
        this.sets = sets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.set_list_view, parent, false);
        String sprefs = getContext().getResources().getString(R.string.sharedpreferences);
        SharedPreferences prefs = getContext().getSharedPreferences(sprefs, Context.MODE_PRIVATE);

        String setName = sets.get(position);

        String weigth = prefs.getString(setName + "w", "N/A");
        String reps = prefs.getString(setName + "r", "N/A");
        String reps2 = prefs.getString(setName+"r2", "N/A");
        String date = prefs.getString(setName+"d", "N/A");

        TextView setHead = (TextView) customView.findViewById(R.id.set_header);
        TextView setWeigh = (TextView) customView.findViewById(R.id.set_view_weight);
        TextView setReps = (TextView) customView.findViewById(R.id.set_view_reps);
        TextView setDate = (TextView) customView.findViewById(R.id.set_view_date);

        setHead.setText(setName);
        setWeigh.setText(getContext().getResources().getString(R.string.weight2) + " " +weigth+ "kg");
        setReps.setText(getContext().getResources().getString(R.string.reps) + " " + reps + "x" + reps2);
        setDate.setText(getContext().getResources().getString(R.string.date) + " " + date);

        return customView;
    }



}
