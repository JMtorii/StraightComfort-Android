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
        args.putInt("PAGE",postion);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentWelcome1() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewpager_layout1, container, false);
        TextView viewTextTitle = ((TextView) rootView.findViewById(R.id.tvWelcomePageTitle));
        TextView viewTextContent = ((TextView) rootView.findViewById(R.id.tvContent));
        TextView viewTextButton = ((TextView) rootView.findViewById(R.id.tvStartButton));
        ImageView viewImage = (ImageView) rootView.findViewById(R.id.ivWelcomePageView);

           if (getArguments().getInt("PAGE")==0){
               viewImage.setImageResource(R.drawable.nexus5home);
               viewTextTitle.setText("Welcome");
               viewTextContent.setText("Welcome to Straight Comfort! Use Straight Comfort to improve your workstation.");
               viewTextButton.setVisibility(View.INVISIBLE);
           }
           else if (getArguments().getInt("PAGE")==1)
           {
               viewImage.setImageResource(R.drawable.nexus5workstation);
               viewTextTitle.setText("Improve your entire workstation");
               viewTextContent.setText("Go through the entire workstation setup to make your work life more comfortable.");
               viewTextButton.setVisibility(View.INVISIBLE);
           }
           else if (getArguments().getInt("PAGE")==2)
           {
               viewImage.setImageResource(R.drawable.nexus5shortcuts);
               viewTextTitle.setText("Improve a certain component");
               viewTextContent.setText("Don't want to go through the entire workstation setup? Improve just a specific part.");
               viewTextButton.setVisibility(View.INVISIBLE);
           }
           else
           {
               viewImage.setImageResource(R.drawable.nexus5discomfort);
               viewTextTitle.setText("Improve based on body part");
               viewTextContent.setText("Does one of your body parts hurt while working? Filter your workstation solutions based on a certain body part.");
               viewTextButton.setVisibility(View.VISIBLE);

               viewTextButton.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                        getActivity().onBackPressed();
                   }
               });
           }

        return rootView;

    }



    public int getPageNumber() {
        return mPageNumber;
    }
}
