package com.haechi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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

        students.get("임준형").addFavoritePartner(students.get("장진원"));
        students.get("임준형").addFavoritePartner(students.get("이기택"));
        students.get("임준형").addFavoritePartner(students.get("유현서"));

        students.get("유현서").addFavoritePartner(students.get("장진원"));
        students.get("유현서").addFavoritePartner(students.get("임준형"));
        students.get("유현서").addFavoritePartner(students.get("이기택"));

        students.get("장진원").addFavoritePartner(students.get("임준형"));
        students.get("장진원").addFavoritePartner(students.get("유현서"));
        students.get("장진원").addFavoritePartner(students.get("이기택"));

        students.get("이기택").addFavoritePartner(students.get("유현서"));
        students.get("이기택").addFavoritePartner(students.get("임준형"));
        students.get("이기택").addFavoritePartner(students.get("장진원"));

        if(selector.run(students)) {
            assertThat(students.get("임준형").getPartnerName(round), is("장진원"));
            assertThat(students.get("유현서").getPartnerName(round), is("이기택"));
            assertThat(students.get("장진원").getPartnerName(round), is("임준형"));
            assertThat(students.get("이기택").getPartnerName(round), is("유현서"));
            round++;
        }

        if(selector.run(students)) {
            assertThat(students.get("임준형").getPartnerName(round), is("이기택"));
            assertThat(students.get("유현서").getPartnerName(round), is("장진원"));
            assertThat(students.get("장진원").getPartnerName(round), is("유현서"));
            assertThat(students.get("이기택").getPartnerName(round), is("임준형"));
            round++;
        }

        if(selector.run(students)) {
            assertThat(students.get("임준형").getPartnerName(round), is("유현서"));
            assertThat(students.get("유현서").getPartnerName(round), is("임준형"));
            assertThat(students.get("장진원").getPartnerName(round), is("이기택"));
            assertThat(students.get("이기택").getPartnerName(round), is("장진원"));
            round++;
        }

        assertThat(selector.run(students), is(false));
    }

    @Test
    public void 만족도_가중치_검증() {
        Selector selector = new Selector();
        Students students = new Students();

        int round = 0;

        students.add(new Student(0, false, "임준형"));
        students.add(new Student(1, true, "유현서"));
        students.add(new Student(2, false, "장진원"));
        students.add(new Student(3, true, "이기택"));

        students.get("임준형").addFavoritePartner(students.get("장진원"));
        students.get("임준형").addFavoritePartner(students.get("이기택"));
        students.get("임준형").addFavoritePartner(students.get("유현서"));

        students.get("유현서").addFavoritePartner(students.get("장진원"));
        students.get("유현서").addFavoritePartner(students.get("임준형"));
        students.get("유현서").addFavoritePartner(students.get("이기택"));

        students.get("장진원").addFavoritePartner(students.get("임준형"));
        students.get("장진원").addFavoritePartner(students.get("유현서"));
        students.get("장진원").addFavoritePartner(students.get("이기택"));

        students.get("이기택").addFavoritePartner(students.get("유현서"));
        students.get("이기택").addFavoritePartner(students.get("임준형"));
        students.get("이기택").addFavoritePartner(students.get("장진원"));

        if(selector.run(students)) {
            assertThat(students.get("임준형").getPartnerName(round), is("장진원"));
            assertThat(students.get("유현서").getPartnerName(round), is("이기택"));
            assertThat(students.get("장진원").getPartnerName(round), is("임준형"));
            assertThat(students.get("이기택").getPartnerName(round), is("유현서"));

            assertThat(students.get("임준형").getScore(), is(0));
            assertThat(students.get("유현서").getScore(), is(2));
            assertThat(students.get("장진원").getScore(), is(0));
            assertThat(students.get("이기택").getScore(), is(0));
            round++;
        }

        if(selector.run(students)) {
            assertThat(students.get(0).getName(), is("유현서"));
            assertThat(students.get(1).getName(), is("임준형"));
            assertThat(students.get(2).getName(), is("장진원"));
            assertThat(students.get(3).getName(), is("이기택"));

            assertThat(students.get("유현서").getPartnerName(round), is("장진원"));
            assertThat(students.get("임준형").getPartnerName(round), is("이기택"));
            assertThat(students.get("장진원").getPartnerName(round), is("유현서"));
            assertThat(students.get("이기택").getPartnerName(round), is("임준형"));

            assertThat(students.get("유현서").getScore(), is(2));
            assertThat(students.get("임준형").getScore(), is(1));
            assertThat(students.get("장진원").getScore(), is(1));
            assertThat(students.get("이기택").getScore(), is(1));
            round++;
        }

        if(selector.run(students)) {
            assertThat(students.get(0).getName(), is("유현서"));
            assertThat(students.get(1).getName(), is("임준형"));
            assertThat(students.get(2).getName(), is("장진원"));
            assertThat(students.get(3).getName(), is("이기택"));

            assertThat(students.get("유현서").getPartnerName(round), is("임준형"));
            assertThat(students.get("임준형").getPartnerName(round), is("유현서"));
            assertThat(students.get("장진원").getPartnerName(round), is("이기택"));
            assertThat(students.get("이기택").getPartnerName(round), is("장진원"));

            assertThat(students.get("유현서").getScore(), is(3));
            assertThat(students.get("임준형").getScore(), is(3));
            assertThat(students.get("장진원").getScore(), is(3));
            assertThat(students.get("이기택").getScore(), is(3));
            round++;
        }
    }
}