package com.example.bruno.onlinecoach.Objects;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student {
    String id, name, coach, phone, coach_name, coach_phone;
    int height;
    int weight;
    int age;



    int weight_goal;
    ArrayList<String> exercises = new ArrayList<>();
    ArrayList<Measure> measures_history = new ArrayList<>();
    //Exercise history;

    public Student() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight_goal() {
        return weight_goal;
    }

    public void setWeight_goal(int weight_goal) {
        this.weight_goal = weight_goal;
    }

    public String getCoach_name() {
        return coach_name;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public String getCoach_phone() {
        return coach_phone;
    }

    public void setCoach_phone(String coach_phone) {
        this.coach_phone = coach_phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
