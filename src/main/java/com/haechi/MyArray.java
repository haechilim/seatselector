package com.haechi;

public class MyArray {
    private int count = 0;
    private int[] numbers = new int[10];

    public void add(int number) {
        if(count == numbers.length) resize();

        numbers[count++] = number;
    }

    public void insert(int index, int number) {
        if(!validIndex(index)) return;

        if(count == numbers.length) resize();

        for(int  i = count; i >= index; i--) {
            numbers[i + 1] = numbers[i];
        }

        numbers[index] = number;

        count++;
    }

    private void resize() {
        int[] temp = numbers;

        numbers = new int[temp.length * 2];

        for(int i = 0; i < temp.length; i++) {
            numbers[i] = temp[i];
        }
    }

    public void remove(int index) {
        if(!validIndex(index)) return;

        if (index < count - 1) {
            for (int i = (index + 1); i < numbers.length; i++) {
                numbers[i - 1] = numbers[i];
            }
        }

        count--;
    }

    public int get(int index) {
        return validIndex(index) ? numbers[index] : -1;
    }

    public void set(int index, int number) {
        if(validIndex(index)) numbers[index] = number;
    }

    public int getCount() {
        return count;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < numbers.length ? true : false;
    }
}