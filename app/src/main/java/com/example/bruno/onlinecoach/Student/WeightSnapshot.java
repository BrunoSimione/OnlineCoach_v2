package com.example.bruno.onlinecoach.Student;

import java.util.ArrayList;
import java.util.Date;

public class WeightSnapshot {

    Date date;
    double weight, fat, muscle;

    public WeightSnapshot(Date date, double weight) {
        this.date = date;
        this.weight = weight;
        this.fat = 0.0;
        this.muscle = 0.0;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getMuscle() {
        return muscle;
    }

    public void setMuscle(double muscle) {
        this.muscle = muscle;
    }

    public static ArrayList<WeightSnapshot> createWeightSnapshotArrayList() {
        ArrayList<WeightSnapshot> weightSnapshots = new ArrayList<WeightSnapshot>();

        WeightSnapshot snap1 = new WeightSnapshot(new Date(), 80.0);
        WeightSnapshot snap2 = new WeightSnapshot(new Date(), 82.0);
        WeightSnapshot snap3 = new WeightSnapshot(new Date(), 84.5);
        WeightSnapshot snap4 = new WeightSnapshot(new Date(), 86.7);
        WeightSnapshot snap5 = new WeightSnapshot(new Date(), 85.0);

        weightSnapshots.add(snap1);
        weightSnapshots.add(snap2);
        weightSnapshots.add(snap3);
        weightSnapshots.add(snap4);
        weightSnapshots.add(snap5);

        return weightSnapshots;
    }
}
