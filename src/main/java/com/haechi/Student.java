package com.haechi;

/* TODO (2) 다음 함수 만들기
 *
 * int getCurrentPartnerId()  //  현재 파트너의 ID를 리턴하는 함수
 *
 */

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

    public Student() {
        for(int i = 0; i < favoritePartners.size(); i++) {
            favoritePartners.set(i, null);
        }
    }

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

    public String getPartnerName(int round) {
        return partners.get(round).getName();
    }

    public void addPartner(Student student) {
        partners.add(student);
    }

    public boolean isExpartner(Student partner) {
        for(int i = 0; i < partners.size(); i++) {
            if(partners.get(i).getId() == partner.getId()) return true;
        }

        return false;
    }

    /*private int getStudentId() {
        for(int i = 0; i < favoritePartners.size(); i++) {
            favoritePartners.get(i).getName
        }
    }*/

    /*public Student getParteners() {
        return partners.get(partners.size() - 1);
    }*/



























    /*public Student() {
        for(int i = 0; i < preferPartners.size(); i++) {
            preferPartners.set(i, -1);
        }
    }

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

    public void addPreferPartners(String name) {
        preferPartners.add(name);
    }

    public int getpreferPartnersId(int index) {
        return (index >= 0 && index < preferPartners.size()) ? preferPartners.get(index) : -1;
    }

    private int searchPreferPartnerId(String name) {
        for(int i = 0; i < preferPartners.size(); i++) {
            preferPartners.get(i)
        }
    }

    public int getCurrentPartnerId() {
        return preferPartners.get(preferPartners.size() - 1);
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }*/
}