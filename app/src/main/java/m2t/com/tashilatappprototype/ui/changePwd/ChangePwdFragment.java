package m2t.com.tashilatappprototype.ui.changePwd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

    // newInstance constructor for creating fragment with arguments
    public static ChangePwdFragment newInstance(int page, String title) {
        ChangePwdFragment fifth = new ChangePwdFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fifth.setArguments(args);
        return fifth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_pwd, container, false);
        ((MainActivity) getActivity()).enableViews(true);
        //((MainActivity) getActivity()).setActionBarTitle(R.string.change_pwd_title);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_log_out).setVisible(true);
        menu.findItem(R.id.action_favoris).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}
