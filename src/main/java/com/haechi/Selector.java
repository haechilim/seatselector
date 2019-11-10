package com.haechi;

public class Selector {
    public boolean run(Students students) {
        students.init();

        for (int choice = 0; choice < students.size() - 1; choice++) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Student partner = student.getFavoritePartner(choice);

                if (student.hasPartner() || partner.hasPartner()) continue;
                if (student.isExpartner(partner)) continue;
                if (partner.getFavoritePartner(0).getId() == student.getId()) makePartner(student, partner);
            }
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            if(student.hasPartner()) continue;

            for (int choice = 0; choice < students.size() - 1; choice++) {
                Student partner = student.getFavoritePartner(choice);

                if(student.isExpartner(partner)) continue;
                if(!partner.hasPartner()) {
                    makePartner(student, partner);
                    break;
                }
            }
        }

        return students.everyStudentsHasPartner();
    }

    private void makePartner(Student student, Student partner) {
        student.addPartner(partner);
        partner.addPartner(student);

        student.setHasPartner(true);
        partner.setHasPartner(true);
    }











        /*for(int j = 0; j < students.size(); j++) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Student partner = student.getPreferPartners(students, j);

                if (student.hasPartner() || partner.hasPartner()) continue;

                if (partner.getFirstPreferPartners(students) == student) makePartner(student, partner);
            }
        }

        for(int i = 0; i < students.size(); i++) {
            if(!students.get(i).hasPartner()) {
                for(int j = 0; j < students.size(); j++) {
                    makePartner(students.get(i) ,students.get(j));
                }
            }
        }
    }



































    /*public boolean run(Students students, SexOption sexOption) {
        int maleCount = 0;
        int femaleCount = 0;
        SexOption originalSexOption = sexOption;

        students.init();

        for(int i = 0; i < students.count(); i++) {
            if(students.get(i).getMale()) maleCount++;
            else femaleCount++;
        }

        for (int i = 0; i < students.count(); i++) {
            Student student = students.get(i);

            if (maleCount == 0 || femaleCount == 0) sexOption = SexOption.OFF;
            if (student.hasPartner()) continue;   // 짝이 결정된 학생인지 확인

            Student partner = findPartner(students, student, i + 1, sexOption);
            //if (partner == null) return false;

            makePartner(student, partner);
            updateScore(student, partner, originalSexOption);

            if (student.getMale()) maleCount--;
            else femaleCount--;

            if(partner != null) {
                if (partner.getMale()) maleCount--;
                else femaleCount--;
            }
        }

        round++;

        return true;
    }

    private Student findPartner(Students students, Student student, int beginIndex, SexOption sexOption) {
        for (int i = beginIndex; i < students.count(); i++) {
            Student candidate = students.get(i);

            if (candidate.hasPartner()) continue;  // 이미 짝이 결정됐는지 확인
            if (Students.isExPartner(student, candidate, round)) continue;   // 전에 짝이었는지 확인

            if(sexOption == SexOption.DIFFERENT) { // 이성우선
                if (student.getMale() == candidate.getMale()) continue;
            }
            else if(sexOption == SexOption.SAME) { // 동성우선
                if (student.getMale() != candidate.getMale()) continue;
            }

            return candidate;
        }

        return null;
    }

    private void makePartner(Student student, Student partner) {
        student.addPreferPartners(partner != null ? partner.getId() : -1);

        if(partner !=  null) {
            partner.addPreferPartners(student.getId());
            student.setHasPartner(true);
            partner.setHasPartner(true);     // 두학생의 정보 갱신(짝이 됨)
        }
    }

    private void updateScore(Student student, Student partner, SexOption sexOption) {
        if(partner == null) return;

        if(sexOption == SexOption.DIFFERENT) {
            if(student.getMale() != partner.getMale()) {
                student.increaseScore();
                partner.increaseScore();
            }
        }
        else if(sexOption == SexOption.SAME) {
            if(student.getMale() == partner.getMale()) {
                student.increaseScore();
                partner.increaseScore();
            }
        }
        else if(sexOption == SexOption.OFF) {
            if(student.hasPartner()) student.increaseScore();
            if(partner.hasPartner()) partner.increaseScore();
        }
    }

    *//*public Student getCurrentPartner(Student[] students, String name) {
        for(int i = 0; i < students.length; i++) {
            if(students[i].getName() == name) {
                students[i].getCurrentPartnerId();


            }
        }
    }*//*

    public int getRound() {
        return round;
    }*/
}