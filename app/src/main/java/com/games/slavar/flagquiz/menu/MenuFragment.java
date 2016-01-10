package com.games.slavar.flagquiz.menu;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.games.slavar.flagquiz.SceneFragment;
import com.games.slavar.flagquiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private Button newGameButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        newGameButton = (Button) view.findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegionDialogFragment regionDialogFragment = new RegionDialogFragment();
                regionDialogFragment.show(getFragmentManager(),"Region fragment");
            //getFragmentManager().beginTransaction().replace(R.id.fragmentConntainer,new SceneFragment()).commit();

            }
        });

        return view;
    }

}
