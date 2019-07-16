package com.haechi;

public class Student {
    private int id;
    private boolean hasPartner;
    private String name;
    private boolean male;
    private int[] partners = new int[10];

    Student() {
        for(int i = 0; i < partners.length; i++) {
            partners[i] = -1;
        }
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
}