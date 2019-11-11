package com.haechi;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private boolean hasPartner;
    private String name;
    private boolean male;
    private List<Student> favoritePartners = new ArrayList<>();
    private List<Student> partners = new ArrayList<>();
    private int score = 0;

    public Student() {}

    public Student(int id, boolean male) {
        this();

        this.id = id;
        this.male = male;
    }

    public Student(int id, boolean male, String name) {
        this();

        this.id = id;
        this.male = male;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setHasPartner(boolean hasPartner) {
        this.hasPartner = hasPartner;
    }

    public boolean hasPartner() {
        return hasPartner;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean getMale() {
        return male;
    }

    public void addFavoritePartner(Student partner) {
        favoritePartners.add(partner);
    }

    public Student getFavoritePartner(int choice) {
        return choice >= 0 && choice < favoritePartners.size() ? favoritePartners.get(choice) : null;
    }

    public int getFavoritePartnerIndex(Student student) {
        for(int index = 0; index < favoritePartners.size(); index++) {
            if(favoritePartners.get(index).getId() == student.getId()) return index;
        }

        return -1;
    }

    public void addPartner(Student student) {
        partners.add(student);
    }

    public String getPartnerName(int round) {
        return partners.get(round).getName();
    }

    public boolean isExpartner(Student partner) {
        for(int i = 0; i < partners.size(); i++) {
            if(partners.get(i).getId() == partner.getId()) return true;
        }

        return false;
    }

    public void addScore(int number) {
        score += number;
    }

    public int getScore() {
        return score;
    }
}