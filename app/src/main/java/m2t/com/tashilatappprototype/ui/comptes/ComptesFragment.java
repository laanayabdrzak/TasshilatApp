package m2t.com.tashilatappprototype.ui.comptes;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.ComptesTabsAdapter;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComptesFragment extends Fragment {


    public ComptesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_comptes, container, false);
        ((MainActivity) getActivity()).enableViews(false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.comptes_title, R.color.thirdColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.thirdColor));
        }
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.vpPager);
        pager.setAdapter(new ComptesTabsAdapter(getChildFragmentManager()));

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        return rootView;
    }

}
