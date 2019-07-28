package com.haechi;

public class Student {
    private int id;
    private boolean hasPartner;
    private String name;
    private boolean male;
    private int[] partners = new int[10];
    private int score = 0;

    public Student() {
        for(int i = 0; i < partners.length; i++) {
            partners[i] = -1;
        }
    }

    public Student(int id, boolean male) {
        this();

        this.id = id;
        this.male = male;
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

    public void setPartnerId(int index, int partnerId) {
        if(index >= 0 && index < partners.length) partners[index] = partnerId;
    }

    public int getPartnerId(int index) {
        return (index >= 0 && index < partners.length) ? partners[index] : -1;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }

    public int getScore() {
        return score;
    }
}