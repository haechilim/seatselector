package com.haechi;

/* TODO (4) Students 클래스 새로 만들기
 *
 * 아래 표시해둔 함수들을 이 클래스로 이동
 *
 * init(), shuffule, sort(), print()
 *
 *
 * 그리고 다음 함수들 생성
 *
 * Student getStudent(int id);
 * Student getStudent(String name);
 * Student getCurrentPartner(int id);
 * Student getCurrentPartner(String name);
 *
 */

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
        student.addPartner(partner.getId());
        partner.addPartner(student.getId());
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

    //  TODO Students 클래스로 이동
    private void init(Student[] students) {
        for(int i = 0; i < students.length; i++) {
            students[i].setHasPartner(false);
        }
    }

    //  TODO Students 클래스로 이동
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

    //  TODO Students 클래스로 이동
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

    //  TODO Students 클래스로 이동
    public void sort(Student[] students) {
        Student temp;

        for (int j = 0; j < (students.length - 1); j++) {
            for (int i = (j + 1); i < students.length; i++) {
                if (students[j].getScore() > students[i].getScore()) {
                    temp = students[j];
                    students[j] = students[i];
                    students[i] = temp;
                }
            }
        }
    }

    //  TODO (3) Student 클래스로 이동
    private boolean isExPartner(Student student, Student candidate) {
        // 전에 짝이었는지 검사
        for (int i = 0; i < round; i++) {
            if (student.getPartnerId(i) == candidate.getId()) return true;
        }

        return false;
    }

    //  TODO Students 클래스로 이동
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