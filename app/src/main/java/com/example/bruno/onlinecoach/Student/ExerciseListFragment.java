package com.example.bruno.onlinecoach.Student;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruno.onlinecoach.Objects.Exercise;
import com.example.bruno.onlinecoach.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnExerciseListFragmentInteractionListener}
 * interface.
 */
public class ExerciseListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnExerciseListFragmentInteractionListener mListener;

    ArrayList<String> exercises;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExerciseListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExerciseListFragment newInstance(int columnCount) {
        ExerciseListFragment fragment = new ExerciseListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            exercises = getArguments().getStringArrayList("exercises");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_list, container, false);


        RecyclerView rvExerciseList = (RecyclerView) view.findViewById(R.id.rv_exercise_list);
        // Initialize contacts
        //exercises = Exercise.createExerciseArrayList();
        // Create adapter passing in the sample user data
        ExerciseListAdapter adapter = new ExerciseListAdapter(exercises, mListener);
        // Attach the adapter to the recyclerview to populate items
        rvExerciseList.setAdapter(adapter);
        // Set layout manager to position the items
        rvExerciseList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnExerciseListFragmentInteractionListener) {
            mListener = (OnExerciseListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnExerciseListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onExerciseListFragmentInteraction(String item);
    }
}
