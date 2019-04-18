package com.example.bruno.onlinecoach.Student;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bruno.onlinecoach.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    double student_weight;
    TextView txt_weight;

    private OnFragmentInteractionListener mListener;

    ImageButton option1, option2, option3, option4;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragment newInstance(String param1, String param2) {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (getArguments() != null) {
            student_weight = getArguments().getDouble("weight");
        }


        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        option1 = view.findViewById(R.id.option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClicked(v, 1);
            }
        });

        option2 = view.findViewById(R.id.option2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClicked(v, 2);
            }
        });

        option3 = view.findViewById(R.id.option3);
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClicked(v, 3);
            }
        });

        option4 = view.findViewById(R.id.option4);
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClicked(v, 4);
            }
        });

        txt_weight = view.findViewById(R.id.txt_weight);
        txt_weight.setText(student_weight + " kg");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMenuFragmentInteraction(2);
        }
    }
    */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void optionClicked(View v, int option){
        //Toast.makeText(getContext(), "Button clicked", Toast.LENGTH_SHORT);
        if (mListener != null) {
            mListener.onMenuFragmentInteraction(option);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMenuFragmentInteraction(int i);
    }
}
