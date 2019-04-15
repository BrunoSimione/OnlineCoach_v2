package com.example.bruno.onlinecoach.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Measure implements Parcelable{
    private String date;
    private double weight, fat, muscle;

    public Measure(String date, double weight, double fat, double muscle) {
        this.date = date;
        this.weight = weight;
        this.fat = fat;
        this.muscle = muscle;
    }

    public Measure() {
    }

    protected Measure(Parcel in) {
        date = in.readString();
        weight = in.readDouble();
        fat = in.readDouble();
        muscle = in.readDouble();
    }

    public static final Creator<Measure> CREATOR = new Creator<Measure>() {
        @Override
        public Measure createFromParcel(Parcel in) {
            return new Measure(in);
        }

        @Override
        public Measure[] newArray(int size) {
            return new Measure[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeDouble(weight);
        dest.writeDouble(fat);
        dest.writeDouble(muscle);
    }
}
