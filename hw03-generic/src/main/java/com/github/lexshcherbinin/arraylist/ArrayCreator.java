package com.github.lexshcherbinin.arraylist;

import org.apache.commons.lang3.RandomStringUtils;

public class ArrayCreator {
    public static Integer[] getRandomNumberArray(int size) {
        Integer[] arrayInt = new Integer[size];

        for (int i = 0; i < size; i++) {
            String numberString = RandomStringUtils.randomNumeric(5);
            arrayInt[i] = Integer.parseInt(numberString);
        }

        return arrayInt;
    }
}