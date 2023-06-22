package edu.algorithms.grokaem_algorithms;

import java.util.Arrays;

public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        Arrays.sort(array);
        int s = 0, e = array.length;

        while (s < e) {
            int m = (s + e) / 2;
            if (m < target) s = m + 1;
            else if (m > target) e = m - 1;
            else return m;
        }
        throw new IllegalArgumentException("value " + target + " is not exists");
    }
}
