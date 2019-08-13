package com.haechi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MyArrayTest {
    @Test
    public void add() {
        MyArray myArray = new MyArray();

        myArray.add(2);
        myArray.add(1);
        myArray.add(3);
        myArray.add(4);
        myArray.add(6);
        myArray.add(5);
        myArray.add(9);
        myArray.add(8);
        myArray.add(7);
        myArray.add(10);
        myArray.add(11);
        myArray.add(12);
        myArray.add(13);
        myArray.add(14);
        myArray.add(15);

        assertThat(myArray.get(0), is(2));
        assertThat(myArray.get(1), is(1));
        assertThat(myArray.get(2), is(3));
        assertThat(myArray.get(3), is(4));
        assertThat(myArray.get(4), is(6));
        assertThat(myArray.get(5), is(5));
        assertThat(myArray.get(6), is(9));
        assertThat(myArray.get(7), is(8));
        assertThat(myArray.get(8), is(7));
        assertThat(myArray.get(9), is(10));
        assertThat(myArray.get(10), is(11));
        assertThat(myArray.get(11), is(12));
        assertThat(myArray.get(12), is(13));
        assertThat(myArray.get(13), is(14));
        assertThat(myArray.get(14), is(15));
    }

    @Test
    public void remove() {
        MyArray myArray = new MyArray();

        myArray.add(2);
        myArray.add(1);
        myArray.add(3);
        myArray.add(4);
        myArray.add(6);
        myArray.add(5);
        myArray.add(9);
        myArray.add(8);
        myArray.add(7);
        myArray.add(10);
        myArray.add(11);
        myArray.add(12);
        myArray.add(13);
        myArray.add(14);
        myArray.add(15);

        myArray.remove(3);
        assertThat(myArray.count(), is(14));

        assertThat(myArray.get(0), is(2));
        assertThat(myArray.get(1), is(1));
        assertThat(myArray.get(2), is(3));
        assertThat(myArray.get(3), is(6));
        assertThat(myArray.get(4), is(5));
        assertThat(myArray.get(5), is(9));
        assertThat(myArray.get(6), is(8));
        assertThat(myArray.get(7), is(7));
        assertThat(myArray.get(8), is(10));
        assertThat(myArray.get(9), is(11));
        assertThat(myArray.get(10), is(12));
        assertThat(myArray.get(11), is(13));
        assertThat(myArray.get(12), is(14));
        assertThat(myArray.get(13), is(15));
    }

    @Test
    public void count() {
        MyArray myArray = new MyArray();

        assertThat(myArray.count(), is(0));

        myArray.add(2);
        assertThat(myArray.count(), is(1));

        myArray.add(1);
        assertThat(myArray.count(), is(2));

        myArray.add(15);
        assertThat(myArray.count(), is(3));

        myArray.remove(2);
        assertThat(myArray.count(), is(2));

        myArray.remove(0);
        assertThat(myArray.count(), is(1));

        myArray.remove(0);
        assertThat(myArray.count(), is(0));
    }

    @Test
    public void set() {
        MyArray myArray = new MyArray();

        myArray.add(2);
        myArray.add(1);
        myArray.add(3);
        myArray.add(4);
        myArray.add(6);
        myArray.add(5);
        myArray.add(9);
        myArray.add(8);
        myArray.add(7);
        myArray.add(10);
        myArray.add(11);
        myArray.add(12);
        myArray.add(13);
        myArray.add(14);
        myArray.add(15);

        assertThat(myArray.get(5), is(5));
        myArray.set(5, 10);
        assertThat(myArray.get(5), is(10));
    }
}
