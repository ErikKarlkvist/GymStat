package com.gymapp.erikkarlkvist.gymstat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erik Karlkvist on 2016-09-29.
 */
public class CustomListAdapter extends ArrayAdapter<Set>{

    public CustomListAdapter(Context context, int layout, List<Set> sets) {

        super(context, R.layout.set_view,sets);
        sets.size();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.set_view, parent, false);

        Set set = this.getItem(position);
        String setName = set.getName();
        String ex = set.getExcersise();
        String weigth = set.getweight();
        String img = set.getImage();

        TextView setHead = (TextView) customView.findViewById(R.id.set_header);
        TextView exc = (TextView) customView.findViewById(R.id.set_view_excersise);
        TextView setWeigh = (TextView) customView.findViewById(R.id.set_view_weight);

        System.out.println(setHead);
        System.out.println(exc);
        System.out.println(setWeigh);

        setHead.setText(setName);
        exc.setText(ex);
        setWeigh.setText(weigth);
        return customView;
    }


}
