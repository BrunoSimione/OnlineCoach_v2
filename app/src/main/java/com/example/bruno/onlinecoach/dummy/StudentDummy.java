package com.example.bruno.onlinecoach.dummy;

import java.util.ArrayList;

public class StudentDummy {

    public String name;

    public StudentDummy(String name) {
        this.name = name;
    }

    public static ArrayList<StudentDummy> createDummyStudents(){
        ArrayList<StudentDummy> list = new ArrayList<>();
        list.add(new StudentDummy("John"));
        list.add(new StudentDummy("Mary"));
        list.add(new StudentDummy("Peter"));
        list.add(new StudentDummy("Wally"));
        list.add(new StudentDummy("Liza"));

        return list;
    }
}
