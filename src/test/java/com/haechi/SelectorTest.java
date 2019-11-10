package com.haechi;

import org.junit.Test;
import org.junit.Ignore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SelectorTest {
    @Test
    public void 기본_로직_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        int round = 0;

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, true, "이기택"));

        students.get("임준형").addFavoritePartner(students.getId("장진원"));
        students.get("임준형").addFavoritePartner(students.getId("이기택"));
        students.get("임준형").addFavoritePartner(students.getId("유현서"));

        students.get("유현서").addFavoritePartner(students.getId("장진원"));
        students.get("유현서").addFavoritePartner(students.getId("임준형"));
        students.get("유현서").addFavoritePartner(students.getId("이기택"));

        students.get("장진원").addFavoritePartner(students.getId("임준형"));
        students.get("장진원").addFavoritePartner(students.getId("유현서"));
        students.get("장진원").addFavoritePartner(students.getId("이기택"));

        students.get("이기택").addFavoritePartner(students.getId("유현서"));
        students.get("이기택").addFavoritePartner(students.getId("임준형"));
        students.get("이기택").addFavoritePartner(students.getId("장진원"));

        selector.run(students);
        assertThat(students.get("임준형").getParteners(students, round), is("장진원"));
        assertThat(students.get("유현서").getParteners(students, round), is("이기택"));
        assertThat(students.get("장진원").getParteners(students, round), is("임준형"));
        assertThat(students.get("이기택").getParteners(students, round), is("유현서"));
        round++;
    }

    /*@Test
    public void 이성우선_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, true, "이기택"));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));
    }

    @Test
    public void 동성우선_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, true, "이기택"));

        selector.run(students, SexOption.SAME);
        assertThat(students.getCurrentPartner("임준형").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("유현서"));
    }

    @Test
    public void 성별무관_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, false, "유현서"));
        students.add(new Student(2, true, "장진원"));
        students.add(new Student(3, false, "이기택"));
        students.add(new Student(4, true, "유다용"));
        students.add(new Student(5, true, "김준식"));

        selector.run(students, SexOption.OFF);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("유다용").getName(), is("김준식"));
        assertThat(students.getCurrentPartner("김준식").getName(), is("유다용"));
    }

    @Test
    public void 이성우선_가중치_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, false, "이기택"));
        students.add(new Student(4, false, "유다용"));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("유다용"), is(nullValue()));

        assertThat(students.getCurrentPartner("임준형").getScore(), is(1));
        assertThat(students.getCurrentPartner("유현서").getScore(), is(1));
        assertThat(students.getCurrentPartner("장진원").getScore(), is(0));
        assertThat(students.getCurrentPartner("이기택").getScore(), is(0));
    }

    @Test
    public void 동성우선_가중치_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, true,"임준형"));
        students.add(new Student(1, true,"유현서"));
        students.add(new Student(2, false,"장진원"));
        students.add(new Student(3, false,"이기택"));
        students.add(new Student(4, false,"유다용"));

        selector.run(students, SexOption.SAME);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("유다용"), is(nullValue()));

        assertThat(students.getCurrentPartner("임준형").getScore(), is(1));
        assertThat(students.getCurrentPartner("유현서").getScore(), is(1));
        assertThat(students.getCurrentPartner("장진원").getScore(), is(1));
        assertThat(students.getCurrentPartner("이기택").getScore(), is(1));
    }

    @Test
    public void 성별무관_가중치_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, true, "임준형"));
        students.add(new Student(1, false, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, false, "이기택"));
        students.add(new Student(4, true, "유다용"));

        selector.run(students, SexOption.OFF);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("유다용"), is(nullValue()));

        assertThat(students.getCurrentPartner("임준형").getScore(), is(1));
        assertThat(students.getCurrentPartner("유현서").getScore(), is(1));
        assertThat(students.getCurrentPartner("장진원").getScore(), is(1));
        assertThat(students.getCurrentPartner("이기택").getScore(), is(1));
    }

    @Test
    public void 과거의_짝_피하기_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, true, "이기택"));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("장진원"));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형").getName(), is("이기택"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("이기택").getName(), is("임준형"));
    }

    @Test
    public void 어쩔수_없이_동성_짝이_되어야_하는_경우() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "여A"));
        students.add(new Student(1, false, "여B"));
        students.add(new Student(2, false, "여C"));
        students.add(new Student(3, false, "여D"));
        students.add(new Student(4, false, "여E"));
        students.add(new Student(5, true, "남A"));
        students.add(new Student(6, false, "여F"));
        students.add(new Student(7, true, "남B"));
        students.add(new Student(8, false, "여G"));
        students.add(new Student(9, true, "남C"));

        {   //  1라운드
            selector.run(students, SexOption.DIFFERENT);
            students.print();

            assertThat(students.getCurrentPartner("여A").getName(), is("남A"));
            assertThat(students.getCurrentPartner("여B").getName(), is("남B"));
            assertThat(students.getCurrentPartner("여C").getName(), is("남C"));
            assertThat(students.getCurrentPartner("여D").getName(), is("여E"));
            assertThat(students.getCurrentPartner("여E").getName(), is("여D"));
            assertThat(students.getCurrentPartner("남A").getName(), is("여A"));
            assertThat(students.getCurrentPartner("여F").getName(), is("여G"));
            assertThat(students.getCurrentPartner("남B").getName(), is("여B"));
            assertThat(students.getCurrentPartner("여G").getName(), is("여F"));
            assertThat(students.getCurrentPartner("남C").getName(), is("여C"));

            assertThat(students.getStudent("여A").getScore(), is(1));
            assertThat(students.getStudent("여B").getScore(), is(1));
            assertThat(students.getStudent("여C").getScore(), is(1));
            assertThat(students.getStudent("여D").getScore(), is(0));
            assertThat(students.getStudent("여E").getScore(), is(0));
            assertThat(students.getStudent("남A").getScore(), is(1));
            assertThat(students.getStudent("여F").getScore(), is(0));
            assertThat(students.getStudent("남B").getScore(), is(1));
            assertThat(students.getStudent("여G").getScore(), is(0));
            assertThat(students.getStudent("남C").getScore(), is(1));
        }


        {   //  2라운드
            students.sort();
            students.print();
            selector.run(students, SexOption.DIFFERENT);
            students.print();

            assertThat(students.getCurrentPartner("여D").getName(), is("남A"));
            assertThat(students.getCurrentPartner("여E").getName(), is("남B"));
            assertThat(students.getCurrentPartner("여F").getName(), is("남C"));
            assertThat(students.getCurrentPartner("여G").getName(), is("여B"));
            assertThat(students.getCurrentPartner("여B").getName(), is("여G"));
            assertThat(students.getCurrentPartner("남A").getName(), is("여D"));
            assertThat(students.getCurrentPartner("여C").getName(), is("여A"));
            assertThat(students.getCurrentPartner("남B").getName(), is("여E"));
            assertThat(students.getCurrentPartner("여A").getName(), is("여C"));
            assertThat(students.getCurrentPartner("남C").getName(), is("여F"));

            assertThat(students.getStudent("여A").getScore(), is(1));
            assertThat(students.getStudent("여B").getScore(), is(1));
            assertThat(students.getStudent("여C").getScore(), is(1));
            assertThat(students.getStudent("여D").getScore(), is(1));
            assertThat(students.getStudent("여E").getScore(), is(1));
            assertThat(students.getStudent("남A").getScore(), is(2));
            assertThat(students.getStudent("여F").getScore(), is(1));
            assertThat(students.getStudent("남B").getScore(), is(2));
            assertThat(students.getStudent("여G").getScore(), is(0));
            assertThat(students.getStudent("남C").getScore(), is(2));
        }

        *//*
        "여G" 0
        "여E" 1
        "여F" 1
        "여D" 1
        "여B" 1
        "여C" 1
        "여A" 1
        "남B" 2
        "남A" 2
        "남C" 2
        *//*

        {   //  3라운드
            students.sort();
            students.print();
            selector.run(students, SexOption.DIFFERENT);
            students.print();

            assertThat(students.getCurrentPartner("여G").getName(), is("남B"));
            assertThat(students.getCurrentPartner("여E").getName(), is("남A"));
            assertThat(students.getCurrentPartner("여F").getName(), is("X"));
            assertThat(students.getCurrentPartner("여D").getName(), is("여A"));
            assertThat(students.getCurrentPartner("여B").getName(), is("여C"));
            assertThat(students.getCurrentPartner("여C").getName(), is("여B"));
            assertThat(students.getCurrentPartner("여A").getName(), is("여D"));
            assertThat(students.getCurrentPartner("남B").getName(), is("여G"));
            assertThat(students.getCurrentPartner("남A").getName(), is("여E"));
            assertThat(students.getCurrentPartner("남C").getName(), is("X"));

            assertThat(students.getStudent("여A").getScore(), is(1));
            assertThat(students.getStudent("여B").getScore(), is(1));
            assertThat(students.getStudent("여C").getScore(), is(1));
            assertThat(students.getStudent("여D").getScore(), is(1));
            assertThat(students.getStudent("여E").getScore(), is(1));
            assertThat(students.getStudent("남A").getScore(), is(2));
            assertThat(students.getStudent("여F").getScore(), is(1));
            assertThat(students.getStudent("남B").getScore(), is(2));
            assertThat(students.getStudent("여G").getScore(), is(0));
            assertThat(students.getStudent("남C").getScore(), is(2));
        }
    }

    @Ignore
    @Test
    public void 짝이_없는_경우_혼자_앉게_되는것_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형").getName(), is("유현서"));
        assertThat(students.getCurrentPartner("유현서").getName(), is("임준형"));
        assertThat(students.getCurrentPartner("장진원"), is(nullValue()));

        selector.run(students, SexOption.DIFFERENT);
        assertThat(students.getCurrentPartner("임준형"), is(nullValue()));
        assertThat(students.getCurrentPartner("유현서").getName(), is("장진원"));
        assertThat(students.getCurrentPartner("장진원").getName(), is("유현서"));
    }

    @Test
    public void 정렬_검증() {
        Students students = new Students();

        students.add(new Student(0, true));
        students.add(new Student(1, true));
        students.add(new Student(2, false));
        students.add(new Student(3, true));

        students.getStudent(0).setScore(3);
        students.getStudent(1).setScore(3);
        students.getStudent(2).setScore(4);
        students.getStudent(3).setScore(1);

        students.sort();
        assertThat(students.get(0).getScore(), is(1));
        assertThat(students.get(1).getScore(), is(3));
        assertThat(students.get(2).getScore(), is(3));
        assertThat(students.get(3).getScore(), is(4));
    }*/
}