package com.zjn.linked;

import java.util.Arrays;
import java.util.Scanner;

public class Lab2 {

    private static final String COMMA = ",";
    private static final String LEFT_BRACE = "{";
    private static final String RIGHT_BRACE = "}";

    //question 1 printArrayNonGen
    public static void printArrayNonGen(Object [] a) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < a.length; i++) {
            stringBuilder.append(a[i]);
            if (i != a.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    //question 2 printArray
    public static void printArray(Integer[] inputArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < inputArray.length; i++) {
            stringBuilder.append(inputArray[i]);
            if (i != inputArray.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    public static void printArray(Double[] inputArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < inputArray.length; i++) {
            stringBuilder.append(inputArray[i]);
            if (i != inputArray.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    public static void printArray(Character[] inputArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < inputArray.length; i++) {
            stringBuilder.append(inputArray[i]);
            if (i != inputArray.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    public static void printArray(String[] inputArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < inputArray.length; i++) {
            stringBuilder.append(inputArray[i]);
            if (i != inputArray.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    // question 3 printArrayGen()
    public static <E> void printArrayGen(E[] inputArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BRACE);
        for (int i = 0; i < inputArray.length; i++) {
            stringBuilder.append(inputArray[i]);
            if (i != inputArray.length - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(RIGHT_BRACE);
        System.out.println(stringBuilder.toString());
    }

    // question 4 getMax()
    public static Comparable getMax(Comparable[] anArray) {
        Arrays.sort(anArray);
        System.out.println(anArray[anArray.length - 1]);
        return anArray[anArray.length - 1];
    }

    // question 5 getMaxGen()
    public static <T extends Comparable<T>> T getMaxGen(T[] array) {
        Arrays.sort(array);
        System.out.println(array[array.length - 1]);
        return array[array.length - 1];
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter a line of integers which are separated by space, enter to process");
        String intLine = scan.nextLine();
        String[] intStrArray = intLine.split(" ");
        int intLength = intStrArray.length;
        Integer[] intArray = new Integer[intLength];
        for (int i = 0; i < intStrArray.length; i++) {
            intArray[i] = Integer.parseInt(intStrArray[i]);
        }
        System.out.println("int array:" + Arrays.toString(intArray));

        System.out.println("enter a line of doubles which are separated by space, enter to process");
        String doubleLine = scan.nextLine();
        String[] doubleStrArray = doubleLine.split(" ");
        int doubleLength = doubleStrArray.length;
        Double[] doubleArray = new Double[doubleLength];
        for (int i = 0; i < doubleStrArray.length; i++) {
            doubleArray[i] = Double.parseDouble(doubleStrArray[i]);
        }
        System.out.println("double array:" + Arrays.toString(doubleArray));

        System.out.println("enter a line of chars which are separated by space, enter to process");
        String charLine = scan.nextLine();
        String[] charStrArray = charLine.split(" ");
        int charLength = charStrArray.length;
        Character[] charArray = new Character[charLength];
        for (int i = 0; i < charStrArray.length; i++) {
            charArray[i] = charStrArray[i].charAt(0);
        }
        System.out.println("char array:" + Arrays.toString(charArray));

        System.out.println("enter a line of Strings which are separated by space, enter to process");
        String stringLine = scan.nextLine();
        String[] strArray = stringLine.split(" ");
        System.out.println("str array:" + Arrays.toString(strArray));

        printArrayNonGen(intArray);
        printArrayNonGen(doubleArray);
        printArrayNonGen(charArray);
        printArrayNonGen(strArray);

        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);
        printArray(strArray);

        printArrayGen(intArray);
        printArrayGen(doubleArray);
        printArrayGen(charArray);
        printArrayGen(strArray);

        getMax(intArray);
        getMax(doubleArray);
        getMax(charArray);
        getMax(strArray);

        getMaxGen(intArray);
        getMaxGen(doubleArray);
        getMaxGen(charArray);
        getMaxGen(strArray);
    }
}