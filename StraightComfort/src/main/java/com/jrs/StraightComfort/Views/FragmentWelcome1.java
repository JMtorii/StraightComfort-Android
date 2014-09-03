package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jrs.StraightComfort.R;

/**

 */
public class FragmentWelcome1 extends Fragment {


   // private OnFragmentInteractionListener mListener;
   public static final String ARG_PAGE = "page";
   private int mPageNumber;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentWelcome1.
     */
    /** TODO: Rename and change types and number of parameters
*/

    public static FragmentWelcome1 create(int postion) {
        FragmentWelcome1 fragment = new FragmentWelcome1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentWelcome1() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewpager_layout1, container, false);

        TextView viewText = ((TextView) rootView.findViewById(R.id.tvWelcomePageTitle));
        ImageView viewImage = (ImageView) rootView.findViewById(R.id.ivWelcomePageView);
        viewImage.setImageResource(R.drawable.nexus5home);
        viewText.setText(getArguments().getString(ARG_PAGE));
        viewText.setText("Welcome\nWelcome to Straight Comfort! Use Straight Comfort to improve your workstation");
        return rootView;


    }



    public int getPageNumber() {
        return mPageNumber;
    }
}
