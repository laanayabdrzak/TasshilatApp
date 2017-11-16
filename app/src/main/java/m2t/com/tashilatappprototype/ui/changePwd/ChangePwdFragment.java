package m2t.com.tashilatappprototype.ui.changePwd;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePwdFragment extends Fragment {


    public ChangePwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_pwd, container, false);
        ((MainActivity) getActivity()).enableViews(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.change_pwd_title);

        return rootView;
    }

}
