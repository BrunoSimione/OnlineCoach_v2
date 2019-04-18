package com.example.bruno.onlinecoach;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.onlinecoach.Coach.StudentListAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoachStudentInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoachStudentInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoachStudentInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> student_exercise;
    Double weight, fat, muscle;
    String name, phone, id;

    private OnFragmentInteractionListener mListener;

    public CoachStudentInfoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CoachStudentInfoFragment newInstance(String param1, String param2) {
        CoachStudentInfoFragment fragment = new CoachStudentInfoFragment();
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
            student_exercise = getArguments().getStringArrayList("student_exercise");
            weight = getArguments().getDouble("weight");
            fat = getArguments().getDouble("fat");
            muscle = getArguments().getDouble("muscle");
            name = getArguments().getString("name");
            phone = getArguments().getString("phone");
            id = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coach_student_info, container, false);

        TextView tv_w = view.findViewById(R.id.tv_value_w);
        TextView tv_name = view.findViewById(R.id.tv_value_name);
        TextView tv_f = view.findViewById(R.id.tv_value_f);
        TextView tv_m = view.findViewById(R.id.tv_value_m);
        tv_w.setText(weight.toString());
        tv_m.setText(muscle.toString());
        tv_f.setText(fat.toString());
        tv_name.setText(name);


        Button btnNewExercise = (Button) view.findViewById(R.id.btn_new_exercise);
        btnNewExercise.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(getContext());
                inputAlert.setTitle("New Exercise");
                inputAlert.setMessage("Exercise Name");
                final EditText userInput = new EditText(getContext());
                inputAlert.setView(userInput);
                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInputValue = userInput.getText().toString();
                        mListener.onFragmentInteractionButton(id, userInputValue);
                        Toast.makeText(getContext(), userInputValue + " updated", Toast.LENGTH_SHORT).show();
                    }
                });
                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();
            }
        });

        Button btnMessage = (Button) view.findViewById(R.id.btn_new_message);
        btnMessage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMessageClicked();
            }
        });

        RecyclerView rvStudentExList = (RecyclerView) view.findViewById(R.id.rv_ex_student_list);
        rvStudentExList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStudentExList.setAdapter(new ExListItemRecyclerViewAdapter(student_exercise , mListener));


        return view;
    }

    private void newMessageClicked() {
        String number = phone;  // The number on which you want to send SMS
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("");
        }
    }

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

    public void newExercise(){

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String ex);
        void onFragmentInteractionButton(String id, String ex);
    }
}
