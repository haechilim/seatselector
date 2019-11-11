package com.haechi;

public class Selector {
    public boolean run(Students students) {
        students.init();
        students.sort();

        for (int choice = 0; choice < students.size() - 1; choice++) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Student partner = student.getFavoritePartner(choice);

                if (student.hasPartner() || partner.hasPartner()) continue;
                if (student.isExpartner(partner)) continue;

                if (partner.getFavoritePartner(0).getId() == student.getId()) {
                    makePartner(student, partner);
                    updateScore(student, partner);
                }
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
                    updateScore(student, partner);
                    break;
                }
            }
        }

        return students.everyoneHasPartner();
    }

    private void makePartner(Student student, Student partner) {
        student.addPartner(partner);
        partner.addPartner(student);

        student.setHasPartner(true);
        partner.setHasPartner(true);
    }

    private void updateScore(Student student, Student partner) {
        student.addScore(student.getFavoritePartnerIndex(partner));
        partner.addScore(partner.getFavoritePartnerIndex(student));
    }
}