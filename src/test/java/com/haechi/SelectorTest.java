package com.haechi;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectorTest {
    @Test
    public void 이성우선_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[4];

        students[0] = new Student(0, false);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, true);

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
    }

    @Test
    public void 동성우선_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[4];

        students[0] = new Student(0, false);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, true);

        round = selector.getRound();
        selector.run(students, SexOption.SAME);
        assertThat(students[0].getPartnerId(round), is(2));
        assertThat(students[1].getPartnerId(round), is(3));
        assertThat(students[2].getPartnerId(round), is(0));
        assertThat(students[3].getPartnerId(round), is(1));
    }

    @Test
    public void 성별무관_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[6];

        students[0] = new Student(0, false);
        students[1] = new Student(1, false);
        students[2] = new Student(2, true);
        students[3] = new Student(3, false);
        students[4] = new Student(4, true);
        students[5] = new Student(5, true);

        round = selector.getRound();
        selector.run(students, SexOption.OFF);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
        assertThat(students[4].getPartnerId(round), is(5));
        assertThat(students[5].getPartnerId(round), is(4));
    }

    @Test
    public void 이성우선_가중치_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[5];

        students[0] = new Student(0, false);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, false);
        students[4] = new Student(4, false);

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
        assertThat(students[4].getPartnerId(round), is(-1));

        assertThat(students[0].getScore(), is(1));
        assertThat(students[1].getScore(), is(1));
        assertThat(students[2].getScore(), is(0));
        assertThat(students[3].getScore(), is(0));
        assertThat(students[4].getScore(), is(0));
    }

    @Test
    public void 동성우선_가중치_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[5];

        students[0] = new Student(0, true);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, false);
        students[4] = new Student(4, false);

        round = selector.getRound();
        selector.run(students, SexOption.SAME);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
        assertThat(students[4].getPartnerId(round), is(-1));

        assertThat(students[0].getScore(), is(1));
        assertThat(students[1].getScore(), is(1));
        assertThat(students[2].getScore(), is(1));
        assertThat(students[3].getScore(), is(1));
        assertThat(students[4].getScore(), is(0));
    }

    @Test
    public void 성별무관_가중치_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[5];

        students[0] = new Student(0, true);
        students[1] = new Student(1, false);
        students[2] = new Student(2, false);
        students[3] = new Student(3, false);
        students[4] = new Student(4, true);

        round = selector.getRound();
        selector.run(students, SexOption.OFF);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
        assertThat(students[4].getPartnerId(round), is(-1));

        assertThat(students[0].getScore(), is(1));
        assertThat(students[1].getScore(), is(1));
        assertThat(students[2].getScore(), is(1));
        assertThat(students[3].getScore(), is(1));
        assertThat(students[4].getScore(), is(0));
    }

    @Test
    public void 과거의_짝_피하기_검증() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[4];

        students[0] = new Student(0, false);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, true);

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(3));
        assertThat(students[1].getPartnerId(round), is(2));
        assertThat(students[2].getPartnerId(round), is(1));
        assertThat(students[3].getPartnerId(round), is(0));
    }

    @Test
    public void 어쩔수_없이_동성_짝이_되어야_하는_경우() {
        int round;
        Selector selector = new Selector();
        Student[] students = new Student[4];

        students[0] = new Student(0, true);
        students[1] = new Student(1, true);
        students[2] = new Student(2, false);
        students[3] = new Student(3, true);

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(2));
        assertThat(students[1].getPartnerId(round), is(3));
        assertThat(students[2].getPartnerId(round), is(0));
        assertThat(students[3].getPartnerId(round), is(1));

        round = selector.getRound();
        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(round), is(3));
        assertThat(students[1].getPartnerId(round), is(2));
        assertThat(students[2].getPartnerId(round), is(1));
        assertThat(students[3].getPartnerId(round), is(0));
    }

    @Ignore
    @Test
    public void 짝이_없는_경우_혼자_앉게_되는것_검증() {
        Selector selector = new Selector();
        Student[] students = new Student[3];

        for(int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }

        students[0].setId(0);
        students[1].setId(1);
        students[2].setId(2);

        students[0].setMale(false);
        students[1].setMale(true);
        students[2].setMale(false);

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(-1));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(-1));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(1));
    }

    @Ignore
    @Test
    public void Ver2_우선되는_성별을_우선으로_찾기() {
        final int same = 0;
        final int different = 1;
        final int HAVE_NOTHING_TO_DO_WITH = 2;

        Selector selector = new Selector();
        Student[] students = new Student[10];

        for(int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }

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

        selector.run(students, SexOption.OFF);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(3));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(6));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(9));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(8));

        selector.run(students, SexOption.OFF);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(3));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(6));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(-1));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(-1));

        /*while (true) {
            if(!Main.run(++round, students, HAVE_NOTHING_TO_DO_WITH)) Main.shuffle(students);
            else break;
        }
        //Main.print(round, students);

        while (true) {
            if(!Main.run(++round, students, HAVE_NOTHING_TO_DO_WITH)) Main.shuffle(students);
            else break;
        }
        //Main.print(round, students);*/
    }

    @Ignore
    @Test
    public void 나중에_지우자() {
        Selector selector = new Selector();
        Student[] students = new Student[10];

        for(int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }

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

        students[0].setMale(false); // 5, 7, 9, 4, 1
        students[1].setMale(false); // 7, 5, 6, 9, 0
        students[2].setMale(false); // 9, 8, 5, 7, 4
        students[3].setMale(false); // 4, 9, 7, 5, 5
        students[4].setMale(false); // 3, 6, 8, 0, 2
        students[5].setMale(true);  // 0, 1, 2, 3, 3
        students[6].setMale(false); // 8, 4, 1, ?, 9
        students[7].setMale(true);  // 1, 0, 3, 2, 8
        students[8].setMale(false); // 6, 2, 4, ?, 7
        students[9].setMale(true);  // 2, 3, 0, 1, 6

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(9));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(3));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(8));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(6));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(2));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(8));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(9));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(6));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(3));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(9));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(6));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(8));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(1));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(3));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(0));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students[0].getPartnerId(selector.getRound() - 1), is(4));
        assertThat(students[1].getPartnerId(selector.getRound() - 1), is(9));
        assertThat(students[2].getPartnerId(selector.getRound() - 1), is(7));
        assertThat(students[3].getPartnerId(selector.getRound() - 1), is(5));
        assertThat(students[4].getPartnerId(selector.getRound() - 1), is(0));
        assertThat(students[5].getPartnerId(selector.getRound() - 1), is(3));
        assertThat(students[6].getPartnerId(selector.getRound() - 1), is(-1));
        assertThat(students[7].getPartnerId(selector.getRound() - 1), is(2));
        assertThat(students[8].getPartnerId(selector.getRound() - 1), is(-1));
        assertThat(students[9].getPartnerId(selector.getRound() - 1), is(1));
    }
}