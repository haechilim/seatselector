package com.haechi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void 기본적인_짝짓기_검증() {
        int round = 0;
        Student[] students = new Student[4];

        for(int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }

        students[0].setId(0);
        students[1].setId(1);
        students[2].setId(2);
        students[3].setId(3);

        students[0].setMale(false);
        students[1].setMale(true);
        students[2].setMale(false);
        students[3].setMale(true);

        Main.run(round, students);

        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));
    }

    @Test
    public void 과거의_짝_피하기_검증() {
        int round = 0;
        Student[] students = new Student[4];

        for(int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }

        students[0].setId(0);
        students[1].setId(1);
        students[2].setId(2);
        students[3].setId(3);

        students[0].setMale(false);
        students[1].setMale(true);
        students[2].setMale(false);
        students[3].setMale(true);

        Main.run(round, students);
        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(3));
        assertThat(students[3].getPartnerId(round), is(2));

        Main.run(++round, students);
        assertThat(students[0].getPartnerId(round), is(3));
        assertThat(students[1].getPartnerId(round), is(2));
        assertThat(students[2].getPartnerId(round), is(1));
        assertThat(students[3].getPartnerId(round), is(0));
    }

    @Test
    public void 짝이_없는_경우_혼자_앉게_되는것_검증() {
        int round = 0;
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

        Main.run(round, students);

        assertThat(students[0].getPartnerId(round), is(1));
        assertThat(students[1].getPartnerId(round), is(0));
        assertThat(students[2].getPartnerId(round), is(-1));

        Main.run(++round, students);

        assertThat(students[0].getPartnerId(round), is(-1));
        assertThat(students[1].getPartnerId(round), is(2));
        assertThat(students[2].getPartnerId(round), is(1));
    }

    @Test
    public void 어쩔수_없이_동성_짝이_되어야_하는_경우() {
        int round = 0;
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

        Main.run(round, students);
        //Main.print(round, students);

        assertThat(students[0].getPartnerId(round), is(5));
        assertThat(students[1].getPartnerId(round), is(7));
        assertThat(students[2].getPartnerId(round), is(9));
        assertThat(students[3].getPartnerId(round), is(4));
        assertThat(students[4].getPartnerId(round), is(3));
        assertThat(students[5].getPartnerId(round), is(0));
        assertThat(students[6].getPartnerId(round), is(8));
        assertThat(students[7].getPartnerId(round), is(1));
        assertThat(students[8].getPartnerId(round), is(6));
        assertThat(students[9].getPartnerId(round), is(2));

        Main.run(++round, students);
        //Main.print(round, students);

        assertThat(students[0].getPartnerId(round), is(7));
        assertThat(students[1].getPartnerId(round), is(5));
        assertThat(students[2].getPartnerId(round), is(-1));
        assertThat(students[3].getPartnerId(round), is(9));
        assertThat(students[4].getPartnerId(round), is(6));
        assertThat(students[5].getPartnerId(round), is(1));
        assertThat(students[6].getPartnerId(round), is(4));
        assertThat(students[7].getPartnerId(round), is(0));
        assertThat(students[8].getPartnerId(round), is(-1));
        assertThat(students[9].getPartnerId(round), is(3));
    }
}