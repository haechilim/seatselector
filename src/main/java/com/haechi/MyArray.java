package com.haechi;

public class MyArray {
    private int count = 0;
    private int[] numbers = new int[10];

    public void add(int number) {
        if(count < numbers.length) {
            numbers[count] = number;
            count++;
            return;
        }

        resize();

        if(validIndex(count)) numbers[count] = number;
        count++;
    }

    private void resize() {
        int[] temp = new int[numbers.length];

        for(int i = 0; i < temp.length; i++) {
            temp[i] = numbers[i];
        }

        numbers = new int[temp.length * 2];

        for(int i = 0; i < temp.length; i++) {
            numbers[i] = temp[i];
        }
    }

    public void insert(int index, int number) {
        count++;

        if(validIndex(index)) {
            for(int i = index; i < numbers.length; i++) {
                if(validIndex(i + 1)) {
                    numbers[i] = numbers[i + 1];
                }

                resize();

                if(validIndex(count)) numbers[count] = numbers[i];
            }
        }

        numbers[index] = number;
    }

    public void remove(int index) {
        count--;

        if(validIndex(index) && validIndex(index + 1)) {
            for(int i = (index + 1); i < numbers.length; i++) {
                numbers[i - 1] = numbers[i];
            }
        }
    }

    public int get(int index) {
        return validIndex(index) ? numbers[index] : -1;
    }

    public void set(int index, int number) {
        if(validIndex(index)) numbers[index] = number;
    }

    public int getLength() {
        return numbers.length;
    }

    public int count() {
        int count = 0;

        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] != 0) count++;
        }

        return count;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < numbers.length ? true : false;
    }
}