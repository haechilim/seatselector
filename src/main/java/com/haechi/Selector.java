package com.haechi;

public class Selector {
    private int round = 0;

    public boolean run(Student[] students, SexOption sexOption) {
        int maleCount = 0;
        int femaleCount = 0;
        SexOption originalSexOption = sexOption;

        init(students);

        for(int i = 0; i < students.length; i++) {
            if(students[i].getMale()) maleCount++;
            else femaleCount++;
        }

        for (int i = 0; i < students.length; i++) {
            Student student = students[i];

            if (maleCount == 0 || femaleCount == 0) sexOption = SexOption.OFF;
            if (student.hasPartner()) continue;   // 짝이 결정된 학생인지 확인

            Student partner = findPartner(students, student, i + 1, sexOption);
            if (partner == null) return false;

            makePartner(student, partner);
            updateScore(student, partner, originalSexOption);

            if (student.getMale()) maleCount--;
            else femaleCount--;

            if (partner.getMale()) maleCount--;
            else femaleCount--;
        }

        round++;

        return true;
    }

    private Student findPartner(Student[] students, Student student, int beginIndex, SexOption sexOption) {
        for (int i = beginIndex; i < students.length; i++) {
            Student candidate = students[i];

            if (candidate.hasPartner()) continue;  // 이미 짝이 결정됐는지 확인
            if (isExPartner(student, candidate)) continue;   // 전에 짝이었는지 확인

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
        student.setPartnerId(round, partner.getId());
        partner.setPartnerId(round, student.getId());
        student.setHasPartner(true);
        partner.setHasPartner(true);     // 두학생의 정보 갱신(짝이 됨)
    }

    private void updateScore(Student student, Student partner, SexOption sexOption) {
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

    private void init(Student[] students) {
        for(int i = 0; i < students.length; i++) {
            students[i].setHasPartner(false);
        }
    }

    public void shuffle(Student[] students, Student loss) {
        students[0] = loss;

        for(int i = 0; i < 100; i++) {
            Student temp;
            int index1 = (int) (Math.random() * students.length);
            int index2 = (int) (Math.random() * students.length);

            if(index1 == 0 || index2 == 0) continue;

            temp = students[index1];
            students[index1] = students[index2];
            students[index2] = temp;
        }
    }

    public void shuffle(Student[] students) {
        for(int i = 0; i < 100; i++) {
            Student temp;
            int index1 = (int) (Math.random() * students.length);
            int index2 = (int) (Math.random() * students.length);

            temp = students[index1];
            students[index1] = students[index2];
            students[index2] = temp;
        }
    }

    private boolean isExPartner(Student student, Student candidate) {
        // 전에 짝이었는지 검사
        for (int i = 0; i < round; i++) {
            if (student.getPartnerId(i) == candidate.getId()) return true;
        }

        return false;
    }

    public void print(Student[] students) {
        for(int i = 0; i < students.length; i++) {
            System.out.println(students[i].getId() + "  " + students[i].getMale() + "  " + students[i].getPartnerId(round));
        }

        System.out.println();
        System.out.println();
    }

    public int getRound() {
        return round;
    }
}