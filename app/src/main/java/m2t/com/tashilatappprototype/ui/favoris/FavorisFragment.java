package m2t.com.tashilatappprototype.ui.favoris;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import m2t.com.tashilatappprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavorisFragment extends Fragment {


    public FavorisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favoris, container, false);

        return rootView;
    }

}
