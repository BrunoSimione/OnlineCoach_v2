package com.example.bruno.onlinecoach.Objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Exercise {
    private String date_current;
    private HashMap<String, String> current;


    public Exercise() {

    }

    public String getDate_current() {
        return date_current;
    }

    public void setDate_current(String date_current) {
        this.date_current = date_current;
    }

    public HashMap<String, String> getCurrent() {
        return current;
    }

    public void setCurrent(HashMap<String, String> current) {
        this.current = current;
    }
 /*
    public static ArrayList<Exercise> createExerciseArrayList() {

        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        Exercise e1 = new Exercise("Bench 12x3");
        Exercise e2 = new Exercise("Dumbbell 10x3");
        Exercise e3 = new Exercise("Running 2km");
        Exercise e4 = new Exercise("Leg press 12x4");

        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);
        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);

        return exercises;
    }
    */

}
