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

import com.google.gson.Gson;

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
        Gson gson = new Gson();
        String json = prefs.getString(setName, "");
        Data data = gson.fromJson(json, Data.class);


        TextView setHead = (TextView) customView.findViewById(R.id.set_header);
        TextView setWeigh = (TextView) customView.findViewById(R.id.set_view_weight);
        TextView setReps = (TextView) customView.findViewById(R.id.set_view_reps);
        TextView setDate = (TextView) customView.findViewById(R.id.set_view_date);

        String date = data.getDate(data.size);

        setHead.setText(setName);
        setWeigh.setText(getContext().getResources().getString(R.string.weight2) + " " +data.getWeight(data.size)+ "kg");
        setReps.setText(getContext().getResources().getString(R.string.reps) + " " + data.getRep(data.size));
        setDate.setText(getContext().getResources().getString(R.string.date) + " " + date);

        return customView;
    }



}
