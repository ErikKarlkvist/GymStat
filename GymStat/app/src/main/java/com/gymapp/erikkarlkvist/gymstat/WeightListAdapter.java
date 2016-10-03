package com.gymapp.erikkarlkvist.gymstat;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Erik Karlkvist on 2016-10-03.
 */
public class WeightListAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dates;
    private Data data;

    public WeightListAdapter(Context context, int layout, ArrayList<String> dates, Data data) {

        super(context, R.layout.set_list_view,dates);
        this.dates = dates;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.weight_list, parent, false);

        TextView viewWeight = (TextView)customView.findViewById(R.id.weight_list_weight);
        TextView viewReps = (TextView)customView.findViewById(R.id.weight_list_reps);
        TextView viewDate  = (TextView)customView.findViewById(R.id.weight_list_date);

        String date = dates.get(position);

        Resources resources = getContext().getResources();
        viewWeight.setText(resources.getText(R.string.weight2) + " " + data.getWeight(position) + "kg");
        viewReps.setText(resources.getText(R.string.reps) + " " + data.getRep(position));
        viewDate.setText(resources.getText(R.string.date) + " " + date);
        return customView;
    }


}
