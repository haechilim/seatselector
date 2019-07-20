package com.haechi;

public class Main {
    public static void main(String[] args) {
        //test1();
        //run();
    }

    public static void run(int round, Student[] students) {
        boolean check = true;
        int maleCount = 0;
        int femaleCount = 0;

        init(students);

        for(int i = 0; i < students.length; i++) {
            if(students[i].getMale()) maleCount++;
            else femaleCount++;
        }

        for (int i = 0; i < students.length; i++) {
            Student student = students[i];

            if(maleCount == 0 || femaleCount == 0) check = false;

            if (student.hasPartner()) continue;   // 짝이 결정된 학생인지 확인

            for (int j = i + 1; j < students.length; j++) {
                Student candidate = students[j];

                if (candidate.hasPartner()) continue;  // 짝이 결정된 학생인지 확인
                if (wasPartnerInThePast(student, candidate, round)) continue;   // 전에 짝이었는지 확인
                if (student.getMale() == candidate.getMale() && check) continue;   // 동성인지 확인

                student.setPartnerId(round, candidate.getId());
                candidate.setPartnerId(round, student.getId());
                student.setHasPartner(true);
                candidate.setHasPartner(true);     // 두학생의 정보 갱신(짝이 됨)

                if(check) {
                    maleCount--;
                    femaleCount--;
                }

                /*System.out.println(maleCount);
                System.out.println(femaleCount);
                System.out.println();
                System.out.println();*/
                break;
            }
        }
    }

    private static void init(Student[] students) {
        for(int i = 0; i < students.length; i++) {
            students[i].setHasPartner(false);
        }
    }

    private static void shuffle(Student[] students) {
        for(int i = 0; i < 100; i++) {
            Student temp;
            int index1 = (int) (Math.random() * students.length);
            int index2 = (int) (Math.random() * students.length);

            temp = students[index1];
            students[index1] = students[index2];
            students[index2] = temp;
        }
    }

    private static boolean wasPartnerInThePast(Student student, Student candidate, int round) {
        // 전에 짝이었는지 검사
        for (int i = 0; i < round; i++) {
            if (student.getPartnerId(i) == candidate.getId()) return true;
        }

        return false;
    }

    public static void print(int round, Student[] students) {
        for(int i = 0; i < students.length; i++) {
            System.out.println(students[i].getId() + "  " + students[i].getMale() + "  " + students[i].getPartnerId(round));
        }

        System.out.println();
        System.out.println();
    }


    /*
    students[0].setId(0);
    students[1].setId(1);
    students[2].setId(2);
    students[3].setId(3);
    students[4].setId(4);
    students[5].setId(5);
    students[6].setId(6);
    students[7].setId(7);
    students[8].setId(8);
    students[9].setId(9);
    students[10].setId(11);
    students[11].setId(12);
    students[12].setId(13);
    students[13].setId(14);
    students[14].setId(15);
    students[15].setId(16);
    students[16].setId(17);
    students[17].setId(18);
    students[18].setId(19);
    students[19].setId(20);
    students[20].setId(21);
    students[21].setId(22);
    students[22].setId(23);
    students[23].setId(24);
    students[24].setId(25);
    students[25].setId(26);
    students[26].setId(27);
    students[27].setId(28);
    students[28].setId(29);
    students[29].setId(30);

    students[0].setMale(false);
    students[1].setMale(false);
    students[2].setMale(false);
    students[3].setMale(false);
    students[4].setMale(false);
    students[5].setMale(true);
    students[6].setMale(false);
    students[7].setMale(true);
    students[8].setMale(false);
    students[9].setMale(true);
    students[10].setMale(false);
    students[11].setMale(true);
    students[12].setMale(false);
    students[13].setMale(true);
    students[14].setMale(false);
    students[15].setMale(true);
    students[16].setMale(false);
    students[17].setMale(true);
    students[18].setMale(false);
    students[19].setMale(true);
    students[20].setMale(false);
    students[21].setMale(true);
    students[22].setMale(false);
    students[23].setMale(true);
    students[24].setMale(false);
    students[25].setMale(true);
    students[26].setMale(false);
    students[27].setMale(true);
    students[28].setMale(false);
    students[29].setMale(true);*/
}