package com.gymapp.erikkarlkvist.gymstat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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
    private Set data;
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

            Set set = new Set(setName, ex, wgt, "warning");
            MainFragment.addSet(set);
            changeFragment(new MainFragment());

        }
    };

    private void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
