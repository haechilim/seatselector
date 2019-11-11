 package com.haechi;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> students = new ArrayList<>();

    public void init() {
        for(int i = 0; i < students.size(); i++) {
            students.get(i).setHasPartner(false);
        }
    }

    public boolean everyoneHasPartner() {
        for(int i = 0; i < students.size(); i++) {
            if(!students.get(i).hasPartner()) return false;
        }

        return true;
    }

    public Student get(int index) {
        return validIndex(index) ? students.get(index) : null;
    }

    public Student get(String name) {
        for(int i = 0; i < students.size(); i++) {
            if(get(i).getName() == name) return get(i);
        }

        return null;
    }

    public int getId(String name) {
        for(int i = 0; i < students.size(); i++) {
            if(get(i).getName() == name) return get(i).getId();
        }

        return -1;
    }

    public int size() {
        return students.size();
    }

    public void add(Student student) {
        students.add(student);
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < students.size();
    }

    public void sort() {
        Student temp;

        for (int j = 0; j < (students.size() - 1); j++) {
            for (int i = (j + 1); i < students.size(); i++) {
                if (students.get(j).getScore() < students.get(i).getScore()) {
                    temp = students.get(j);
                    students.set(j, students.get(i));
                    students.set(i, temp);
                    break;
                }
            }
        }
    }
}