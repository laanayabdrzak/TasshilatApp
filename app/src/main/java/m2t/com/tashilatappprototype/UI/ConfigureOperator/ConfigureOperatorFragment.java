package m2t.com.tashilatappprototype.UI.ConfigureOperator;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureOperatorFragment extends Fragment {

    private ImageView imgOperator;
    private TextView titleLogo;


    public ConfigureOperatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configure_operator, container, false);
        ((MainActivity) getActivity()).enableViews(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.conf_oper_title);
        imgOperator = (ImageView) rootView.findViewById(R.id.img_operator);
        titleLogo = (TextView) rootView.findViewById(R.id.title_logo);

        if (getArguments() != null && !getArguments().getString("logo_operator").trim().equals("")) {
            imgOperator.setImageResource(Integer.valueOf(getArguments().getString("logo_operator")));
            titleLogo.setText(getArguments().getString("title_operator"));
        }


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).setDrawerLocked(false);
    }

}
