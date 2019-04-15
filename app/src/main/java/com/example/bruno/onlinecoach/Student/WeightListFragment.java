package com.example.bruno.onlinecoach.Student;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruno.onlinecoach.Objects.Measure;
import com.example.bruno.onlinecoach.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeightListFragment.OnWeightListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeightListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Measure> measure_list = new ArrayList<>();

    private OnWeightListFragmentInteractionListener mListener;

    //ArrayList<WeightSnapshot> weight_snapshots;

    public WeightListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeightListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeightListFragment newInstance(String param1, String param2) {
        WeightListFragment fragment = new WeightListFragment();
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
            measure_list = getArguments().getParcelableArrayList("measures");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weight_list, container, false);

        RecyclerView rvWeightList = (RecyclerView) view.findViewById(R.id.rv_weight_list);
        // Initialize contacts
        //weight_snapshots = WeightSnapshot.createWeightSnapshotArrayList();
        // Create adapter passing in the sample user data

        Collections.sort(measure_list, new Comparator<Measure>() {
            public int compare(Measure o1, Measure o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        WeightListAdapter adapter = new WeightListAdapter(measure_list);
        // Attach the adapter to the recyclerview to populate items
        rvWeightList.setAdapter(adapter);
        // Set layout manager to position the items
        rvWeightList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_weight);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                mListener.onWeightListFragmentInteraction(getContext());
            }
        });



        DataPoint[] dp_w = new DataPoint[5];
        DataPoint[] dp_f = new DataPoint[5];
        DataPoint[] dp_m = new DataPoint[5];
        //ArrayList<DataPoint> dp_f = new ArrayList<>();
        //ArrayList<DataPoint> dp_m = new ArrayList<>();

        List<Measure> last_5 =  measure_list.subList(measure_list.size()-5 ,measure_list.size());

        //int list_size = measure_list.size();
        for(int i = 0; i < 5; i++){
            dp_w[i] = (new DataPoint(i + 1, last_5.get(i).getWeight()));
            dp_f[i] = (new DataPoint(i + 1, last_5.get(i).getFat()));
            dp_m[i] = (new DataPoint(i + 1, last_5.get(i).getMuscle()));

        }

        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> seriesW = new LineGraphSeries<>(dp_w);
        LineGraphSeries<DataPoint> seriesF = new LineGraphSeries<>(dp_f);
        LineGraphSeries<DataPoint> seriesM = new LineGraphSeries<>(dp_m);

        seriesW.setColor(Color.RED);
        seriesF.setColor(Color.BLUE);
        seriesM.setColor(Color.GREEN);

        seriesW.setTitle("Weight");
        seriesF.setTitle("Fat");
        seriesM.setTitle("Muscle");

        graph.addSeries(seriesW);
        graph.addSeries(seriesF);
        graph.addSeries(seriesM);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onWeightListFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWeightListFragmentInteractionListener) {
            mListener = (OnWeightListFragmentInteractionListener) context;
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
    public interface OnWeightListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onWeightListFragmentInteraction(Context ctx);
    }
}
