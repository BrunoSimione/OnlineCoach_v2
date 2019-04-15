package com.example.bruno.onlinecoach.Coach;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruno.onlinecoach.Objects.Exercise;
import com.example.bruno.onlinecoach.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentInfoFragment.OnTrainingListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Exercise> training_list;

    private OnTrainingListFragmentInteractionListener mListener;

    public StudentInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentInfoFragment newInstance(String param1, String param2) {
        StudentInfoFragment fragment = new StudentInfoFragment();
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

        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        RecyclerView rvTrainingtList = (RecyclerView) view.findViewById(R.id.rv_training_list);
        // Initialize contacts
        //training_list = Exercise.createExerciseArrayList();
        // Create adapter passing in the sample user data
        StudentListTrainingAdapter adapter = new StudentListTrainingAdapter(training_list, mListener);
        // Attach the adapter to the recyclerview to populate items
        rvTrainingtList.setAdapter(adapter);
        // Set layout manager to position the items
        rvTrainingtList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_send_message);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Exercise exercise) {
        if (mListener != null) {
            mListener.OnTrainingListFragmentInteractionListener(exercise);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTrainingListFragmentInteractionListener) {
            mListener = (OnTrainingListFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTrainingListFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnTrainingListFragmentInteractionListener(Exercise exercise);
    }
}
