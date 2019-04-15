package com.example.bruno.onlinecoach.Objects;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student {
    String id, name, coach;
    int height, weight;
    ArrayList<String> exercises = new ArrayList<>();
    ArrayList<Measure> measures_history = new ArrayList<>();
    //Exercise history;

    public Student() {

    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public double getLastWeight(){
        return measures_history.get(0).getWeight();
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<String> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<Measure> getMeasures_history() {
        return measures_history;
    }

    public void setMeasures_history(ArrayList<Measure> measures_history) {
        this.measures_history = measures_history;
    }
}
