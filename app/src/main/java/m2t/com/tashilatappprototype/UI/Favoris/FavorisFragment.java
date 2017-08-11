package m2t.com.tashilatappprototype.UI.Favoris;


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoris, container, false);
    }

}
