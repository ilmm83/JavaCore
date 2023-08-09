package edu.algorithms.grokaem_algorithms;

import java.util.Arrays;

public class BinarySearch {

    public static int bs(int[] arr, int target) {
        Arrays.sort(arr);

        int s = 0, e = arr.length - 1;

        while (s <= e) {
            var m = (s + e) / 2;

            if (arr[m] == target) return m;
            else if (arr[m] < target) s = m + 1;
            else e = m - 1;
        }

        return -1;
    }
}
