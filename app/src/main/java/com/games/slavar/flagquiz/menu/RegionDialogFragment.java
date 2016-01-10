package com.games.slavar.flagquiz.menu;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.games.slavar.flagquiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegionDialogFragment extends DialogFragment {
    private ListView regionListView;


    public RegionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_region_dialog, container, false);
        getDialog().setTitle("Choose Region");
        String regionArray[] = getResources().getStringArray(R.array.regionArray);
        regionListView = (ListView) view.findViewById(R.id.regionListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.region_row_list,regionArray);
        regionListView.setAdapter(adapter);
        return view;
    }

}
