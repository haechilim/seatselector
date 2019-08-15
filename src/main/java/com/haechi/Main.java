package com.haechi;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student[] array = new Student[10];
        array[0] = new Student();
        array[1] = new Student();


        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        System.out.println(students.size());
        students.remove(0);
        System.out.println(students.size());


        List<String> strings = new ArrayList<>();
        strings.add("임준형");
        strings.add("임동욱");


        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(4);


        List<Double> doubles = new ArrayList<>();
        doubles.add(12.3);
        doubles.add(13.3);
    }
}