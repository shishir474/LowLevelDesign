package JavaCollections;

import java.util.Arrays;

public class LearnArraysClass {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

//        binary Search     -- obviously Binary Search only works on sorted array. BuiltIn funciton by Arrays Class
        int index = Arrays.binarySearch(arr, 4);
        System.out.println("The index of element 4 is " + index);

//        sorts the array
        Arrays.sort(arr);

//        fill the array
        Arrays.fill(arr, 12);

    }
}
