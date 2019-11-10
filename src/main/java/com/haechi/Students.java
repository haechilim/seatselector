 package com.haechi;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> students = new ArrayList<>();

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


    /*public void init() {
        for(int i = 0; i < students.size(); i++) {
            students.get(i).setHasPartner(false);
        }
    }

    public int count() {
        return students.size();
    }

    public void add(Student student) {
        students.add(student);
    }

    public Student get(int index) {
        return validIndex(index) ? students.get(index) : null;
    }

    public Student getStudent(int id) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId() == id) return students.get(i);
        }

        return null;
    }

    public Student getStudent(String name) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equals(name)) return students.get(i);
        }

        return null;
    }

    public Student getCurrentPartner(int id) {
        return getCurrentPartner(getStudent(id));
    }

    public Student getCurrentPartner(String name) {
        return getCurrentPartner(getStudent(name));
    }

    public Student getCurrentPartner(Student student) {
        return getStudent(student.getCurrentPartnerId());
    }

    public void sort() {
        Student temp;

        for (int j = 0; j < (students.size() - 1); j++) {
            for (int i = (j + 1); i < students.size(); i++) {
                if (students.get(j).getScore() > students.get(i).getScore()) {
                    temp = students.get(j);
                    students.set(j, students.get(i));
                    students.set(i, temp);
                    break;
                }
            }
        }
    }

    public static boolean isExPartner(Student student, Student candidate, int round) {
        // 전에 짝이었는지 검사
        for (int i = 0; i < round; i++) {
            if (student.getPartnerId(i) == candidate.getId()) return true;
        }

        return false;
    }

    public void print() {
        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            System.out.print(student.getName() + "  " + student.getScore() + "  " + student.getName());

            for(int j = 0; j < students.size(); j++) {
                System.out.print(" " + students.get(i).getName());
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < students.size();
    }*/
}
